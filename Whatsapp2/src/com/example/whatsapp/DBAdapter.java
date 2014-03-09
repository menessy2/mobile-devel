package com.example.whatsapp;

import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	public static final String KEY_ROWID ="_id_mobileNumber";
	public static final String KEY_NAME ="name";
	public static final String KEY_TIMESTAMP ="date";
	private static final String TAG ="DBAdapter";
	
	private static final String DATABASE_NAME ="MyDB";
	private static final String DATABASE_TABLE ="conversations";
	private static final int DATABASE_Version =1;
	
	private static final String DATABASE_CREATE ="create table "+DATABASE_TABLE+" ("+KEY_ROWID+" integer primary key autoincrement, "+KEY_NAME+" "+
			"text not null , date text not null);";
	private final Context context ;
	private DatabaseHelper DBHelper;
	private  SQLiteDatabase db;
	
	public DBAdapter(Context ctx)
	{
		this.context=ctx;
		DBHelper= new DatabaseHelper(context);
	
	}
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_Version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			// TODO Auto-generated method stub
			Log.w(TAG,"Upgrading database from version"+oldVersion+ "to"+newVersion+", which will destroy all old data"); 
			db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
			onCreate(db);
			
		}
	}
	
	//DB commands to be executed
	//---open the database
	public DBAdapter open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	//---closes the database---
	public void close()
	{
		DBHelper.close();
	}
	
	//---insert a contact into the database---
	public long insertIM(String ID , String message)
	{
		ContentValues initialValues =new ContentValues();
		initialValues.put(KEY_NAME, ID);
		initialValues.put(KEY_ROWID, message);
		String timeStamp = Long.toString(System.currentTimeMillis());
		
		initialValues.put(KEY_TIMESTAMP, timeStamp);
		return db.insert(DATABASE_TABLE, null, initialValues);


		
	}
	}
	
	
	
	




	

