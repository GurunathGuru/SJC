package com.integro.sjc.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.integro.sjc.fragments.HomeFragment;
import com.integro.sjc.fragments.NewsFragment;
import com.integro.sjc.fragments.NotificationFragment;
import com.integro.sjc.fragments.WebFragment;

public class ViewPageAdapter extends FragmentPagerAdapter {
    int mNoOfTabs;

    public ViewPageAdapter(@NonNull FragmentManager fm, int mNoOfTabs) {
        super(fm);
        this.mNoOfTabs = mNoOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0) {
            fragment = new HomeFragment();
        }
        if (position == 1) {
            fragment = new NewsFragment();
        }
        if (position == 2) {
            fragment = new NotificationFragment();
        }
        if (position == 3) {
            fragment = new WebFragment();
        }
        return fragment;
    }

    @Override
    public int getCount()
    {
        return 4;
    }
}
