package com.example.model;

import java.util.ArrayList;
import java.util.List;

import com.example.presenters.IDatabaseHandler;
import com.example.presenters.TransactionHistory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper implements
        IDatabaseHandler {
    // DB version
    private static final int DATABASE_VERSION = 1;
    // DB name
    private static final String DATABASE_NAME = "userManager";
    // Table names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String TABLE_TRANSACTIONS = "transactions";
    // Column names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private static final String KEY_ACCOUNT_NAME = "account_name";
    private static final String KEY_BALANCE = "balance";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_INTEREST = "interest";

    private static final String KEY_ACCOUNT_ID = "account_id";
    private static final String KEY_DEPOSIT = "deposit_amount";
    private static final String KEY_WITHDRAWAL = "withdrawal_amount";
    private static final String KEY_DATE = "date";
    private static final String KEY_TRANSACTION_NAME = "transaction_name";

    private static final String CREATE_USERS_TABLE = "CREATE TABLE "
            + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_USERNAME + " TEXT," + KEY_PASSWORD + " TEXT" + ")";

    private static final String CREATE_ACCOUNTS_TABLE = "CREATE TABLE "
            + TABLE_ACCOUNTS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_ACCOUNT_NAME + " TEXT," + KEY_BALANCE + " REAL,"
            + KEY_USER_ID + " INTEGER," + KEY_INTEREST + " REAL)";

    private static final String CREATE_TRANSACTIONS_TABLE = "CREATE TABLE "
            + TABLE_TRANSACTIONS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TRANSACTION_NAME + " TEXT," + KEY_ACCOUNT_ID + " INTEGER,"
            + KEY_USER_ID + " INTEGER," + KEY_DEPOSIT + " REAL,"
            + KEY_WITHDRAWAL + " REAL," + KEY_DATE + " TEXT" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_TRANSACTIONS_TABLE);
        // Log.d("Database Creation", "Users and Accounts tables created");
    }

    public SQLiteDatabase getDB() {
        return this.getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        // create new table again
        onCreate(db);
    }

    public long createAccount(Account a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACCOUNT_NAME, a.getAccountName());
        values.put(KEY_BALANCE, a.getBalance());
        values.put(KEY_USER_ID, a.getUserID());
        values.put(KEY_INTEREST, a.getInterestRate());

        long account_id = db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
        return account_id;
    }

    // add user to db
    public long addUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // get user username and passowrd
        values.put(KEY_USERNAME, u.getUsername());
        values.put(KEY_PASSWORD, u.getPassword());

        // insert row
        long user_id = db.insert(TABLE_USERS, null, values);
        db.close(); // close db connection
        return user_id;
    }

    // add transaction
    public long addTransaction(Transaction t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TRANSACTION_NAME, t.getTransactionName());
        values.put(KEY_ACCOUNT_ID, t.getAccountID());
        values.put(KEY_USER_ID, t.getUserID());
        values.put(KEY_WITHDRAWAL, t.getWithdrawAmount());
        values.put(KEY_DEPOSIT, t.getDepositAmount());
        values.put(KEY_DATE, t.getDate());

        long transaction_id = db.insert(TABLE_TRANSACTIONS, null, values);
        db.close();
        // Log.d("DatabaseHandler", "Transaction: " + t.getTransactionName() +
        // " added to account: " + this.getAccount(t.getAccountID()));
        return transaction_id;
    }

    // USER ID CORRECTLY USED??
    public List<Transaction> getTransactionsByDates(long startDate,
            long endDate, long userID) {
        List<Transaction> result = new ArrayList<Transaction>();

        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE "
                + KEY_DATE + " >= " + startDate + " AND " + KEY_DATE + " <= "
                + endDate + " AND " + KEY_USER_ID + " = " + userID;
        String queryTwo = "SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE "
                + KEY_USER_ID + " = " + userID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(queryTwo, null);

        if (c.moveToFirst()) {
            do {
                Transaction trans = new Transaction();
                trans.setAccountID(c.getLong(c.getColumnIndex(KEY_ACCOUNT_ID)));
                trans.setTransactionName(c.getString(c
                        .getColumnIndex(KEY_TRANSACTION_NAME)));
                // trans.setAccountID(c.getInt(c.getColumnIndex(KEY_ACCOUNT_ID)));
                trans.setUserID(c.getInt(c.getColumnIndex(KEY_USER_ID)));
                trans.setWithdrawAmount(c.getDouble(c
                        .getColumnIndex(KEY_WITHDRAWAL)));
                trans.setDepositAmount(c.getDouble(c
                        .getColumnIndex(KEY_DEPOSIT)));
                trans.setDate(c.getLong(c.getColumnIndex(KEY_DATE)));

                result.add(trans);
            } while (c.moveToNext());
        }
        return result;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                KEY_USERNAME, KEY_PASSWORD }, KEY_USERNAME + "=?",
                new String[] { String.valueOf(username) }, null, null, null,
                null);
        db.close();
        if ((cursor.getCount() != 0) && (cursor.moveToFirst())) {
            return false;
        } else {
            return true;
        }
    }

    // get user from db by ID
    public User getUser(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                KEY_USERNAME, KEY_PASSWORD }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        User user = new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return user;
    }

    public Account getAccount(long account_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE "
                + KEY_ID + " = " + account_id;

        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();
        }
        Account account = new Account();
        account.setID(c.getInt(c.getColumnIndex(KEY_ID)));
        account.setAccountName(c.getString(c.getColumnIndex(KEY_ACCOUNT_NAME)));
        account.setBalance(c.getDouble(c.getColumnIndex(KEY_BALANCE)));
        account.setUserID(c.getInt(c.getColumnIndex(KEY_USER_ID)));
        account.setInterestRate(c.getDouble(c.getColumnIndex(KEY_INTEREST)));
        return account;
    }

    // get user from db by username and password
    public User getUserByUP(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        /*
         * String[] args = {username, password}; Cursor cursor =
         * db.rawQuery("SELECT * FROM users where KEY_USERNAME=? & KEY_PASSWORD=?"
         * , args); if (cursor.moveToFirst()){
         * 
         * User user = new User(Integer.parseInt(cursor.getString(0)),
         * cursor.getString(1), cursor.getString(2)); return user; } return
         * null;
         */
        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                KEY_USERNAME, KEY_PASSWORD }, KEY_USERNAME + "=? AND "
                + KEY_PASSWORD + "=?", new String[] { String.valueOf(username),
                String.valueOf(password) }, null, null, null, null);
        if (cursor.moveToFirst()) {// (cursor!=null){
            // cursor.moveToFirst();
            User user = new User(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
            return user;
        }
        return null;
    }

    // get arraylist of all users in db
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public List<Account> getAllAccountsByID(long id) {
        List<Account> accounts = new ArrayList<Account>();

        String selectQuery = "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE "
                + KEY_USER_ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // db.close();
        if (c.moveToFirst()) {
            do {
                Account account = new Account();
                account.setID(c.getLong(c.getColumnIndex(KEY_ID)));
                account.setAccountName(c.getString(c
                        .getColumnIndex(KEY_ACCOUNT_NAME)));
                account.setBalance(c.getDouble(c.getColumnIndex(KEY_BALANCE)));
                account.setUserID(c.getLong(c.getColumnIndex(KEY_USER_ID)));
                account.setInterestRate(c.getDouble(c
                        .getColumnIndex(KEY_INTEREST)));
                accounts.add(account);
            } while (c.moveToNext());
        }
        return accounts;
    }

    public TransactionHistory getAllTransactionsByID(long id) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE "
                + KEY_ACCOUNT_ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Transaction t = new Transaction();
                t.setID(c.getLong(c.getColumnIndex(KEY_ID)));

                // t.set...set everything like in accounts above
                transactions.add(t);
            } while (c.moveToNext());
        }
        return new TransactionHistory(transactions);
    }

    // get number of users in db
    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // update a user in the database
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());

        return db.update(TABLE_USERS, values, KEY_ID + " =?",
                new String[] { String.valueOf(user.getID()) });
    }

    // FIX PLEASE:
    public int updateAccount(Account a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACCOUNT_NAME, a.getAccountName());
        values.put(KEY_BALANCE, a.getBalance());
        values.put(KEY_USER_ID, a.getUserID());
        values.put(KEY_INTEREST, a.getInterestRate());

        return db.update(TABLE_ACCOUNTS, values, KEY_ID + " =?",
                new String[] { String.valueOf(a.getID()) });
    }

    // Delete a user from the database
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getID()) });
        db.close();
    }
}
