package happygirlzt.com.keydays.databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import happygirlzt.com.keydays.models.KeyDate;
import happygirlzt.com.keydays.models.User;

import java.util.ArrayList;
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

        // user, keydate, record table name
        private static final String TABLE_USER = "user";
        private static final String TABLE_KEYDATE = "keydate";
        private static final String TABLE_RECORD = "record";

        // User Table Columns names
        // user_id is primary key
        private static final String COLUMN_USER_ID = "user_id";
        private static final String COLUMN_USER_NAME = "user_name";
        private static final String COLUMN_USER_EMAIL = "user_email";
        private static final String COLUMN_USER_PASSWORD = "user_password";

        // KeyDate Table Columns names
        // date_id is primary key
        private static final String COLUMN_KEYDATE_ID = "keydate_id";
        private static final String COLUMN_KEYDATE_TITLE = "keydate_title";
        private static final String COLUMN_KEYDAYE_DATE = "keydate_date";


        // Record Table Columns names
        // UserId and DateId are primary keys
        private static final String COLUMN_RECORD_USER = "record_user";
        private static final String COLUMN_RECORD_DATE = "record_date";

        // create table user sql query
        private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER
                + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT"
                + ")";

        // create table date sql query
        private String CREATE_KEYDATE_TABLE = "CREATE TABLE " + TABLE_KEYDATE
                + "("
                + COLUMN_KEYDATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_KEYDATE_TITLE + " TEXT,"
                + COLUMN_KEYDAYE_DATE + " TEXT"
                + ")";

        // create table record sql query
        private String CREATE_RECORD_TABLE = "CREATE TABLE " + TABLE_RECORD
                + "("
                + COLUMN_RECORD_USER + " INTEGER NOT NULL CONSTRAINT UserId REFERENCES user(id) ON DELETE CASCADE,"
                + COLUMN_RECORD_DATE + " INTEGER NOT NULL CONSTRAINT KeydateId REFERENCES keydate(keydate_id) ON DELETE CASCADE"
                + ")";

        // drop table sql query
        private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
        private String DROP_KEYDATE_TABLE = "DROP TABLE IF EXISTS " + TABLE_KEYDATE;
        private String DROP_RECORD_TABLE = "DROP TABLE IF EXISTS " + TABLE_RECORD;

        /**
         * Constructor
         *
         */
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USER_TABLE);
            db.execSQL(CREATE_KEYDATE_TABLE);
            db.execSQL(CREATE_RECORD_TABLE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_USER_TABLE);
            db.execSQL(DROP_KEYDATE_TABLE);
            db.execSQL(DROP_RECORD_TABLE);

            onCreate(db);
        }

        /**
         * Add a user record
         */
        public void addUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getmName());
            values.put(COLUMN_USER_EMAIL, user.getmEmail());
            values.put(COLUMN_USER_PASSWORD, user.getmPassword());

            // 插入一行
            db.insert(TABLE_USER, null, values);
            db.close();
        }


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

            values.put(COLUMN_KEYDATE_TITLE, keyDate.getmTitle());
            values.put(COLUMN_KEYDAYE_DATE, keyDate.getmDate());

            db.insertOrThrow(TABLE_KEYDATE, null, values);
            db.close();
        }

        /**
         * This method is to delete a date
         */
         public void deleteKeyDate(KeyDate keydate) {
             SQLiteDatabase db = this.getWritableDatabase();

             db.delete(TABLE_KEYDATE, COLUMN_KEYDATE_ID + " = ?",
                     new String[] { String.valueOf(keydate.getmId())});

             db.close();
         }

         /**
          *  This method is to fetch the dates created by user_id
          *  Select * From keydates kd, record r
          *  where r.record_user = user_id AND
          *  kd.keydates.id = r.record_date
          */
         public List<KeyDate> fetchUserDates(int user_id) {
             SQLiteDatabase db = this.getReadableDatabase();

             List<KeyDate> res = new ArrayList<>();
             KeyDate mKeyDate = null;

             String sql = "SELECT * FROM "
                     + TABLE_RECORD + " r, "
                     + TABLE_KEYDATE + " kd WHERE r."
                     + COLUMN_RECORD_USER + " = '" + user_id + "'"
                     + " AND r." + TABLE_RECORD + " = " + "kd." + COLUMN_KEYDATE_ID;

             Cursor c =db.rawQuery(sql, null);

             while (c.moveToNext()) {

                 mKeyDate = new KeyDate();
                // mKeyDate.setmDate(c.getString(c.getColumnIndex("COLUMN_KEYDATE_DATE")));
                 mKeyDate.setmTitle(c.getString(c.getColumnIndex("COLUMN_KEYDATE_NAME")));
                 res.add(mKeyDate);
             }

             c.close();
             db.close();
             return res;
         }

    }