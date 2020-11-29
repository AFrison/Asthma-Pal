package com.AP.Asthma_Pal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    public static final String JOURNALHISTORY = "entries";
    public static final String ENTRYDATE = "Date";
    public static final String COUGH = "Coughing";
    public static final String WHEEZE = "Wheezing";
    public static final String CHEST = "Chest";
    public static final String SLEEP = "Sleep";
    public static final String EXERCISE = "Exercise";
    public static final String MEDS = "Medication";
    private static final String TAG = "Database helper";

    public DatabaseHelper(@Nullable Context context) {
        super(context, JOURNALHISTORY, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + JOURNALHISTORY + " (" +
                ENTRYDATE + " TEXT PRIMARY KEY, " +
                COUGH + " TEXT, " +
                WHEEZE +" TEXT, " +
                CHEST + " TEXT, " +
                SLEEP +" TEXT, " +
                EXERCISE +" TEXT, " +
                MEDS + " TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + JOURNALHISTORY);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String date, String cough, String wheeze, String chest, String sleep, String exercise, String meds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ENTRYDATE, date);
        contentValues.put(COUGH, cough);
        contentValues.put(WHEEZE, wheeze);
        contentValues.put(CHEST, chest);
        contentValues.put(SLEEP, sleep);
        contentValues.put(EXERCISE, exercise);
        contentValues.put(MEDS, meds);
        //date = "1";
        Log.d(TAG, "Adding data tp" + JOURNALHISTORY );
        //db.execSQL("INSERT INTO " + JOURNALHISTORY + " VALUES (" + date +", " + cough+", " + wheeze + ", " + chest + ", " + sleep +", " + exercise + ", " + meds + ")");

        long result = db.insert(JOURNALHISTORY, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + JOURNALHISTORY, null);
        return data;
    }
}