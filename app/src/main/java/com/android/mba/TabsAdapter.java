package com.android.mba;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by tutlane on 19-12-2017.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                HomeFragment home = new HomeFragment();
                return home;
            case 1:
                TeamsFragment teams = new TeamsFragment();
                return teams;
            case 2:
                AboutFragment about = new AboutFragment();
                return about;
            case 3:
                ContactFragment contact = new ContactFragment();
                return contact;
            case 4:
                FixtureFragment fixture = new FixtureFragment();
                return fixture;
            default:
                return null;
        }
    }
}
