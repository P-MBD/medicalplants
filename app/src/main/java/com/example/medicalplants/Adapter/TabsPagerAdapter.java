package com.example.medicalplants.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.medicalplants.Fragment.DescriptionFragment;
import com.example.medicalplants.Fragment.MapFragments;
import com.example.medicalplants.Fragment.MovesFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return  new DescriptionFragment();
            case 1:
                return new MapFragments();
            case  2:
                return  new MovesFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
