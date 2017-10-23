package com.lostbug.criminalintent;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.lostbug.criminalintent.CrimeDbSchema.CrimeTab.Cols;

import java.util.Date;
import java.util.UUID;

/**
 * Created by rocka on 2017/10/23.
 */

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String title = getString(getColumnIndex(Cols.TITLE));
        long date = getLong(getColumnIndex(Cols.DATE));
        int isSolved = getInt(getColumnIndex(Cols.SOLVED));
        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        return crime;
    }
}
