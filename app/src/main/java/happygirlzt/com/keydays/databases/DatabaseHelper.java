package happygirlzt.com.keydays.databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import happygirlzt.com.keydays.models.KeyDate;
import happygirlzt.com.keydays.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
     * Created on 4 Oct 2018 by happygirlzt
     * Fun1: createUser()
     * Fun2: deleteUser()
     * Fun3: checkUser()
     */

    public class DatabaseHelper extends SQLiteOpenHelper {

        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "userdate.db";

        // user and date table name
        private static final String TABLE_USER = "user";
        private static final String TABLE_KEYDATE = "keydate";

        // user Table Columns names
        // user_id is primary key
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_USER_NAME = "user_name";
        private static final String COLUMN_USER_EMAIL = "user_email";
        private static final String COLUMN_USER_PASSWORD = "user_password";

        // date Table Columns names
        // date_id is primary key
        private static final String COLUMN_KEYDATE_ID = "keydate_id";
        private static final String COLUMN_KEYDATE_NAME = "keydate_name";
        private static final String COLUMN_KEYDAYE_DATE = "keydate_date";
        private static final String COLUMN_USER_ID = "user_id";
        private static final String COLUMN_REMAINING_DAYS = "remaining_days";
        private static final String COLUMN_PAST_DAYS = "past_days";

        // create table user sql query
        private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT"
                + ")";

        // created table date sql query
        private String CREATE_KEYDATE_TABLE = "CREATE TABLE " + TABLE_KEYDATE
                + "("
                + COLUMN_KEYDATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_KEYDATE_NAME + " TEXT,"
                + COLUMN_KEYDAYE_DATE + " TEXT,"
                + COLUMN_USER_ID + " INTEGER NOT NULL CONSTRAINT user_id REFERENCES user(id) ON DELETE CASCADE,"
                + COLUMN_REMAINING_DAYS + " INTEGER,"
                + COLUMN_PAST_DAYS + " INTEGER"
                + ")";

        // drop table sql query
        private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
        private String DROP_KEYDATE_TABLE = "DROP TABLE IF EXISTS " + TABLE_KEYDATE;

        /**
         * Constructor
         *
         * @param context
         */
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USER_TABLE);
            db.execSQL(CREATE_KEYDATE_TABLE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_USER_TABLE);
            db.execSQL(DROP_KEYDATE_TABLE);

            onCreate(db);
        }

        /**
         * This method is to add a user record
         *
         */
        public void addUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            // Created content values to insert
            // contentValues很像map
            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());

            // 插入一行
            db.insert(TABLE_USER, null, values);
            db.close();
        }


        /**
         * 以后可以来实现修改密码
         *
         */
/*
        public void updateUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());

            // updating row
            db.update(TABLE_USER, values, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(user.getId())});
            db.close();
        }
*/


        /**
         * 暂时不需要
         *  This method to delete a user
         */
        /*public void deleteUser(int user_id) {
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_USER, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(user_id) });

            db.close();
        }*/


        /**
         * This method to check user exist or not
         *
         */
        public boolean checkUser(String email) {
            // 需要返回的columns
            String[] columns = {
                    COLUMN_USER_ID
            };
            SQLiteDatabase db = this.getReadableDatabase();

            String selection = COLUMN_USER_EMAIL + " = ?";

            // 根据email来判断user
            String[] selectionArgs = {email};

            Cursor cursor = db.query(TABLE_USER, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null);                      //The sort order
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();


            return cursorCount > 0;

        }

        /**
         * This method to check if user name and password match
         *
         */
        public boolean checkUser(String email, String password) {

            String[] columns = {
                    COLUMN_USER_ID
            };

            SQLiteDatabase db = this.getReadableDatabase();

            String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
            String[] selectionArgs = {email, password};

            Cursor cursor = db.query(TABLE_USER,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);

            int cursorCount = cursor.getCount();

            cursor.close();
            db.close();

            return cursorCount > 0;
        }

        /**
         * This method is to add a date
         */
        public void addKeyDate(KeyDate keyDate) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            //values.put(COLUMN_KEYDAYE_DATE, keyDate.getmDate());

            values.put(COLUMN_KEYDATE_NAME, keyDate.getmName());
            values.put(COLUMN_USER_ID, keyDate.getmUserId());
            // values.put(COLUMN_KEYDAYE_DATE, keyDate.getmDate());
            values.put(COLUMN_PAST_DAYS, keyDate.getmPastDays());
            values.put(COLUMN_REMAINING_DAYS, keyDate.getmRemainingDays());

            db.insertOrThrow(TABLE_KEYDATE, null, values);
            db.close();
        }

        /**
         * This method is to delete a date
         */
         public void deleteKeyDate(KeyDate keydate) {
             SQLiteDatabase db = this.getWritableDatabase();

             db.delete(TABLE_KEYDATE, COLUMN_ID + " = ?",
                     new String[] { String.valueOf(keydate.getmId())});

             db.close();
         }

         /**
          *  This method is to fetch the dates created by user_id
          */
         public List<KeyDate> fetchUserDates(int user_id) {
             SQLiteDatabase db = this.getReadableDatabase();
             List<KeyDate> res = new ArrayList<>();
             KeyDate mKeyDate = null;

             String section = "COLUMN_USER_ID=";
             // Select * from KeyDates where user_id = 'user_id'
             Cursor c = db.query(TABLE_KEYDATE,
                     null,  // null means return all columns
                     section + user_id,
                     null,
                     null,
                     null,
                     null);

             while (c.moveToNext()) {
                 mKeyDate = new KeyDate();
                 mKeyDate.setmId(c.getInt(c.getColumnIndex("COLUMN_KEYDATE_ID")));
                // mKeyDate.setmDate(c.getString(c.getColumnIndex("COLUMN_KEYDATE_DATE")));
                 mKeyDate.setmName(c.getString(c.getColumnIndex("COLUMN_KEYDATE_NAME")));
                 mKeyDate.setmPastDays(c.getInt(c.getColumnIndex("COLUMN_KEYDATE_PAST_DAYS")));
                 mKeyDate.setmRemainingDays(c.getInt(c.getColumnIndex("COLUMN_KEYDATE_REMAINING_DAYS")));
                 res.add(mKeyDate);
             }

             c.close();
             db.close();
             return res;
         }

    }