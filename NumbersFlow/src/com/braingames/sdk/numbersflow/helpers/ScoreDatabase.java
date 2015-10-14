package com.braingames.sdk.numbersflow.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoreDatabase extends SQLiteOpenHelper {
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "scoresDb";

	// Contacts table name
	private static final String TABLE_CONTACTS = "scores";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_SCORE = "score";
	private static final String KEY_MODE = "mode";

	public ScoreDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_SCORE + " TEXT," + KEY_MODE + " TEXT" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		// Create tables again
		onCreate(db);
	}
	
	
	

	public List<Score> getFirstFiveScores(GameModesEnum mode) {
		InnerList contactList = new InnerList();
		// Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_CONTACTS + " WHERE mode=?" ;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[]{mode.toString()});
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Score contact = new Score();
				contact._id = Integer.parseInt(cursor.getString(0));
				contact._score = cursor.getString(1);
				contact._mode = cursor.getString(2);
				// Adding contact to list

				if (!contactList.containsScore(contact)) {
					contactList.add(contact);
				}
			} while (cursor.moveToNext());
		}
		
		Collections.sort(contactList);
		
		// return contact list
		if (contactList.size() >= 5) {
			return contactList.subList(0, 5);
		}else{
			return contactList.subList(0, contactList.size());
		}
	}

	public void addContact(Score score) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_SCORE, score._score); // Contact Name
		values.put(KEY_MODE, score._mode); // Contact Phone Number

		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}
	
	
	protected class InnerList extends ArrayList<Score>{
		   public boolean containsScore(Score value){
		        for(Score item : this){
		            if (item.equalsScore(value)){
		                return true;
		            }
		        }
		        return false; 
		    }
	}
}
