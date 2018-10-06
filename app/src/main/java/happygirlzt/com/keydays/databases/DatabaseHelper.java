package happygirlzt.com.keydays.databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import happygirlzt.com.keydays.models.User;

/**
 * Created on 4 Oct 2018 by happygirlzt
 * Fun1: addUser()
 * Fun2: alreadyExists()
 * Fun3: anthenticate()
 */

    public class DatabaseHelper extends SQLiteOpenHelper {

        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "usersinfo";

        // User table name
        private static final String TABLE_USER = "user";

        // User Table Columns names
        // user_id is primary key
        private static final String COLUMN_USER_ID = "user_id";
        private static final String COLUMN_USER_NAME = "user_name";
        private static final String COLUMN_USER_EMAIL = "user_email";
        private static final String COLUMN_USER_PASSWORD = "user_password";

        // create table sql query
        private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER
                + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT"
                + ")";

        // drop table sql query
        private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

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
            // Create table when onCreated() gets called
            db.execSQL(CREATE_USER_TABLE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            //Drop User Table if exist
            db.execSQL(DROP_USER_TABLE);

            // Create tables again
            onCreate(db);

        }

        /**
         * This method is to create user record
         *
         * @param user
         */
        public void addUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            // Created content values to insert
            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());

            // Inserting Row
            db.insert(TABLE_USER, null, values);
            db.close();
        }


        /**
         * This method to update user record
         *
         * @param user
         */
        public void updateUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());

            // updating row
            db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                    new String[]{String.valueOf(user.getId())});
            db.close();
        }


        /**
         * This method to check user exist or not
         *
         * @param email
         * @return true/false
         */
        public boolean checkUser(String email) {

            // array of columns to fetch
            String[] columns = {
                    COLUMN_USER_ID
            };
            SQLiteDatabase db = this.getReadableDatabase();

            // selection criteria
            String selection = COLUMN_USER_EMAIL + " = ?";

            // selection argument
            String[] selectionArgs = {email};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
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


        public boolean authenticate(User user) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_USER,
                    new String[]{COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_EMAIL, COLUMN_USER_PASSWORD},
                    COLUMN_USER_EMAIL + "=?",
                    new String[]{user.getEmail()},
                    null,
                    null,
                    null);

            if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
                User user1 = new User(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));

                return user.getPassword().equalsIgnoreCase(user1.getPassword());
            }

            return false;
        }
    }