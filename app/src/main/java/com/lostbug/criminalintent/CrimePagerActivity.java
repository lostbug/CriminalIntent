package com.lostbug.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private boolean mSubtitleVisible;
    private static final String EXTRA_CRIME_ID =
            "com.lostbug.criminalintent.crime_id";
    public static final String EXTRA_IS_SUBTITILE_VISIBLE =
            "com.lostbug.criminalintent.is_subtitle_visible";

    public static Intent newIntent(Context packageContext, UUID crimeId, boolean isSubtitleVisible) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        intent.putExtra(EXTRA_IS_SUBTITILE_VISIBLE, isSubtitleVisible);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mSubtitleVisible = (boolean) getIntent().getSerializableExtra(EXTRA_IS_SUBTITILE_VISIBLE);
        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);
        mCrimes = CrimeLab.get(this).getCrimeList();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        Intent intent = super.getParentActivityIntent();
        if (intent != null) {
            intent.putExtra(EXTRA_IS_SUBTITILE_VISIBLE, mSubtitleVisible);
        }
        return intent;
    }

}
