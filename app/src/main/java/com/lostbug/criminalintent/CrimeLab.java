package com.lostbug.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by rocka on 2017/9/25.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimeList;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimeList = new ArrayList<>();
        for(int i=0;i<100;i++){
            Crime crime=new Crime();
            crime.setTitle("Crime#"+i);
            crime.setSolved(i%2==0);
            mCrimeList.add(crime);
        }
    }

    public List<Crime> getCrimeList() {
        return mCrimeList;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimeList) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

}
