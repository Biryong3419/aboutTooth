package com.example.kimwoochul.abouttooth.Tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kimwoochul.abouttooth.Tabs.Schedule.Data_Schedule;

import java.util.ArrayList;

/**
 * Created by woocheol on 2016. 11. 6..
 */

public class DbOpenHelper {
    private static final String DATABASE_NAME = "scheduleDB.db";
    private static final String TABLE_NAME = "scheduleDB";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private static DbOpenHelper instance;

    public static DbOpenHelper getInstance(){
        if(instance == null)
        {
            instance = new DbOpenHelper(MyApplication.getsContext());
            instance.open();
        }
        return instance;
    }

    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE scheduleDB (idx INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "day, memo, time);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS scheduleDB;");
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLiteException{
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();

        return this;
    }

    public void close(){
        mDB.close();
    }

    public void resetDb(){
        mDB = mDBHelper.getWritableDatabase();

        mDB.execSQL("DROP TABLE IF EXISTS scheduleDB;");
        mDB.execSQL("CREATE TABLE scheduleDB (idx INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "day, memo, time);");
    }

    public void insertDb(Data_Schedule info){
        ContentValues values = new ContentValues();

        values.put("day", info.getDay());
        values.put("memo", info.getMemo());
        values.put("time", info.getTime());

        mDB.insert(TABLE_NAME, null, values);
    }

    public void deleteItem(int position){
        mDB = mDBHelper.getWritableDatabase();
        mDB.delete("scheduleDB", "idx = " + position, null);

        String sql = "SELECT * FROM scheduleDB;";
        Cursor cursor = mDB.rawQuery(sql, null);
        int count = cursor.getCount();
        for(int i = 0; i < count; i++) {
            sql = "UPDATE scheduleDB SET idx = " + (position + i) + " WHERE idx = " + (position + i + 1);
            mDB.execSQL(sql);
        }
        cursor.close();
    }

    public ArrayList<Data_Schedule> loadDb(){
        ArrayList<Data_Schedule> list = new ArrayList<Data_Schedule>();

        mDB = mDBHelper.getReadableDatabase();
        Cursor mCursor = mDB.query(TABLE_NAME, null, null, null, null, null, null, null);

        while (mCursor.moveToNext()){
            String day = mCursor.getString(mCursor.getColumnIndex("day"));
            String memo = mCursor.getString(mCursor.getColumnIndex("memo"));
            String time = mCursor.getString(mCursor.getColumnIndex("time"));

            list.add(new Data_Schedule(day, memo, time));
        }
        return list;
    }
}
