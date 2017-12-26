package com.lostbug.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lostbug.criminalintent.CrimeDbSchema.CrimeTab;
import com.lostbug.criminalintent.CrimeDbSchema.CrimeTab.Cols;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by rocka on 2017/9/25.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    //    private List<Crime> mCrimeList;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
//        mCrimeList = new ArrayList<>();
    }

    public void addCrime(Crime c) {
//        mCrimeList.add(c);
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeTab.NAME, null, values);
    }

    public List<Crime> getCrimeList() {
//        return mCrimeList;
//        return new ArrayList<>();
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } catch (Exception e) {
            Log.e("", "", e);
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                Cols.UUID + "=?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();

        } finally {
            cursor.close();
        }
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mDatabase.update(CrimeTab.NAME, values, Cols.UUID + "=?", new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTab.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(Cols.UUID, crime.getId().toString());
        values.put(Cols.DATE, crime.getDate().toString());
        values.put(Cols.TITLE, crime.getTitle());
        values.put(Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(Cols.SUSPECT,crime.getSuspect());
        return values;
    }


}
