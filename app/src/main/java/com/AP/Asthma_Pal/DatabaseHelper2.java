package com.AP.Asthma_Pal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    public static final String JOURNALHISTORY = "PeakFlow";
    public static final String ENTRYDATE = "Date";
    public static final String PEAK = "Peak";

    public DatabaseHelper2(@Nullable Context context) {
        super(context, JOURNALHISTORY, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + JOURNALHISTORY + " (" +
                ENTRYDATE + " real PRIMARY KEY, " +
                PEAK + " real )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + JOURNALHISTORY);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(double date, double peak) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ENTRYDATE, date);
        contentValues.put(PEAK, peak);

        long result = db.insert(JOURNALHISTORY, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + JOURNALHISTORY, null);
        return data;
    }
}