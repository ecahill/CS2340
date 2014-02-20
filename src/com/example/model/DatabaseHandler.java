package com.example.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	//DB version
	private static final int DATABASE_VERSION = 1;
	//DB name
	private static final String DATABASE_NAME = "userManager";
	//Table name
	private static final String TABLE_USERS = "users";
	//Column names
	private static final String KEY_ID = "id";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";

	public DatabaseHandler(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("+KEY_ID+ " INTEGER PRIMARY KEY," + KEY_USERNAME+" TEXT,"
				+KEY_PASSWORD+" TEXT"+")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//drop old table
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
		//create new table again
		onCreate(db);
	}
	
	
	//add user to db
	public void addUser(User u){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//get user username and passowrd
		values.put(KEY_USERNAME, u.getUsername());
		values.put(KEY_PASSWORD, u.getPassword());
		
		//insert row
		db.insert(TABLE_USERS, null, values);
		db.close(); // close db connection
	}
	
	
	//get user from db by ID
	public User getUser(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_USERS, new String[] {KEY_ID, KEY_USERNAME, KEY_PASSWORD}, KEY_ID+"=?", new String[]
				{String.valueOf(id)}, null, null, null, null);
		if (cursor!=null){
			cursor.moveToFirst();
		}
		User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
		return user;
	}
	
	//get user from db by username and password
	public User getUserByUP(String username, String password){
		SQLiteDatabase db = this.getReadableDatabase();
		Log.d("Cursor Results", "getting cursor");
		Cursor cursor = db.query(TABLE_USERS, new String[] {KEY_ID, KEY_USERNAME, KEY_PASSWORD}, KEY_USERNAME+"=? AND "+
		KEY_PASSWORD+"=?", new String[] {String.valueOf(username), String.valueOf(password)}, null, null, null, null);
		Log.d("Cursor Results", "Cursor is: "+cursor);
		if (cursor.moveToFirst()){//(cursor!=null){
			//cursor.moveToFirst();
			User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
			return user;
		}
		return null;
	}
	
	
	//get arraylist of all users in db
	public List<User> getAllUsers(){
		List<User> userList = new ArrayList<User>();
		String selectQuery = "SELECT * FROM "+TABLE_USERS;
	
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()){
			do{
				User user = new User();
				user.setID(Integer.parseInt(cursor.getString(0)));
				user.setUsername(cursor.getString(1));
				user.setPassword(cursor.getString(2));
				userList.add(user);
			}while(cursor.moveToNext());
		}
		return userList;
	}
	
	
	//get number of users in db
	public int getUsersCount(){
		String countQuery = "SELECT * FROM " + TABLE_USERS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		
		return cursor.getCount();
	}
	
	
	//update a user in the database
	public int updateUser(User user){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, user.getUsername());
		values.put(KEY_PASSWORD, user.getPassword());
		
		return db.update(TABLE_USERS, values, KEY_ID+ " =?", new String[] {String.valueOf(user.getID())});
	}
	
	
	//Delete a user from the database
	public void deleteUser(User user){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USERS, KEY_ID+" = ?", new String[] {String.valueOf(user.getID())});
		db.close();
	}
	
	
	
}
