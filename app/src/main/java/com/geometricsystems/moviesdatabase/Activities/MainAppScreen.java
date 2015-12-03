package com.geometricsystems.moviesdatabase.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.geometricsystems.moviesdatabase.Activities.Fragments.FRFavoritesList;
import com.geometricsystems.moviesdatabase.Activities.Fragments.FRMainList;
import com.geometricsystems.moviesdatabase.Activities.adapters.AdapterFragments;
import com.geometricsystems.moviesdatabase.R;

import java.util.ArrayList;

/**
 * Created by dmitriy on 02.12.15.
 */
public class MainAppScreen extends FragmentActivity{

    Activity activity;
    ViewPager vpMain;
    ActionBar.TabListener tabListner;
    AdapterFragments adapterVP;
    ActionBar.Tab tabMainList,tabFavorites;
    ActionBar actionBar;

    ArrayList<Fragment> frList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.main_screen);
        vpMain = (ViewPager)findViewById(R.id.vpMain);
        actionBar = activity.getActionBar();

        activity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        tabListner = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction){
                if (tab.equals(tabMainList)){
                    vpMain.setCurrentItem(0);

                }else{
                    vpMain.setCurrentItem(1);
                    ((FRFavoritesList)frList.get(1)).updateListState();
                    actionBar.setTitle("Избранные фильмы");
                }
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction){

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        };

        tabMainList = activity.getActionBar().newTab();
        tabMainList.setText("Популярные");
        tabMainList.setTabListener(tabListner);
        activity.getActionBar().addTab(tabMainList);

        tabFavorites = activity.getActionBar().newTab();
        tabFavorites.setText("Избранные");
        tabFavorites.setTabListener(tabListner);
        activity.getActionBar().addTab(tabFavorites);

        frList.add(FRMainList.newInstance());
        frList.add(FRFavoritesList.newInstance());
//
//
        adapterVP = new AdapterFragments(getSupportFragmentManager(),frList);
        vpMain.setAdapter(adapterVP);

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                activity.getActionBar().setSelectedNavigationItem(position);
                if(position==1){
                    ((FRFavoritesList)frList.get(1)).updateListState();
                }else{
                    actionBar.setTitle("Популярные фильмы");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }
}
