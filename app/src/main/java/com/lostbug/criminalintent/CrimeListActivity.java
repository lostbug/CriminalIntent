package com.lostbug.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

public class CrimeListActivity extends SingleFragmentActivity {
    private static final String TAG = "CrimeListActivity";
    boolean mSubtitleVisible;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                mSubtitleVisible = (boolean) intent.getSerializableExtra(CrimePagerActivity.EXTRA_IS_SUBTITILE_VISIBLE);
                Log.e(TAG, "mSubtitleVisible" + mSubtitleVisible);
            } catch (Exception e) {
                mSubtitleVisible = false;
                Log.e(TAG, "mSubtitleVisible", e);
            }

        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return CrimeListFragment.newInstance(mSubtitleVisible);
    }
}
