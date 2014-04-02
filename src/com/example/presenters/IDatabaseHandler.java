package com.example.presenters;

import java.util.List;

import com.example.model.Account;
import com.example.model.Transaction;
import com.example.model.User;

import android.database.sqlite.SQLiteDatabase;

public interface IDatabaseHandler {

    public void onCreate(SQLiteDatabase db);

    // public SQLiteDatabase getDB();
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    public long createAccount(Account a);

    public long addUser(User u);

    public boolean checkUsername(String username);

    public User getUser(long id);

    public Account getAccount(long account_id);

    public User getUserByUP(String username, String password);

    public List<User> getAllUsers();

    public List<Account> getAllAccountsByID(long id);

    public TransactionHistory getAllTransactionsByID(long id);

    public List<Transaction> getTransactionsByDates(long startDate,
            long endDate, long userID);

    public int getUsersCount();

    public int updateUser(User user);

    public int updateAccount(Account a);

    public void deleteUser(User user);

    public long addTransaction(Transaction t);

}
