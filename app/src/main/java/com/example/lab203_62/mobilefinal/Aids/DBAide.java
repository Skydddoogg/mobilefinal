package com.example.lab203_62.mobilefinal.Aids;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lab203_62.mobilefinal.Authentication.User;

import java.util.ArrayList;
import java.util.List;

public class DBAide extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;
    private String TAG = "DBAide";

    private static DBAide instance;

    private DBAide(Context context){
        super(context, "finalExam.db", null, 2);
    }

    public static DBAide getInstance(Context context){
        if (instance == null){
            instance = new DBAide(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_FRIEND_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                User.TABLE,
                User.Column.ID,
                User.Column.USER_ID,
                User.Column.PASSWORD,
                User.Column.NAME,
                User.Column.AGE);

        sqLiteDatabase.execSQL(CREATE_FRIEND_TABLE);
        Log.d(TAG, "THE DATABASE WAS CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + User.TABLE;

        sqLiteDatabase.execSQL(DROP_FRIEND_TABLE);

        onCreate(sqLiteDatabase);
    }

    public void add(User user){
        try {
            sqLiteDatabase = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(User.Column.USER_ID, user.getUserId());
            values.put(User.Column.PASSWORD, user.getPassword());
            values.put(User.Column.NAME, user.getName());
            values.put(User.Column.AGE, user.getAge());

            sqLiteDatabase.insert(User.TABLE, null, values);
            Log.d(TAG, "User inserted");
        } catch (SQLiteConstraintException e){
            Log.d(TAG, e.getLocalizedMessage());
        } catch (Exception e){
            Log.d(TAG, e.getLocalizedMessage());
        }

        sqLiteDatabase.close();

    }

    public ArrayList getPassword(String userId){
        String outPassword = "";
        String outName = "";
        List data = new ArrayList<String>();
        try {
            sqLiteDatabase = this.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from Users where userId = ?", new String[] {userId});
            while (cursor.moveToNext()){
                outPassword = cursor.getString(2);
                outName = cursor.getString(3);
            }

            data.add(outName);
            data.add(outPassword);

            cursor.close();
            sqLiteDatabase.close();
        } catch (Exception e){
            Log.d(TAG, e.getLocalizedMessage());
        }

        return (ArrayList) data;
    }
}
