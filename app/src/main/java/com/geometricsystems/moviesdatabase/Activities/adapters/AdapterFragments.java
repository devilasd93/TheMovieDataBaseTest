package com.geometricsystems.moviesdatabase.Activities.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by dmitriy on 02.12.15.
 */
public class AdapterFragments extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    public AdapterFragments(FragmentManager fm, ArrayList<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position){
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
