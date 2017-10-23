package com.lostbug.criminalintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lostbug.criminalintent.CrimeDbSchema.CrimeTab;

/**
 * Created by rocka on 2017/10/23.
 */

public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CrimeTab.NAME + "(" +
                "_id integer primary key autoincrement, " +
                CrimeTab.Cols.UUID + "," +
                CrimeTab.Cols.TITLE + "," +
                CrimeTab.Cols.DATE + "," +
                CrimeTab.Cols.SOLVED +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
