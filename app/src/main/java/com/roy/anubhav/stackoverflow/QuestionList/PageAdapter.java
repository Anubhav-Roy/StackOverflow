package com.roy.anubhav.stackoverflow.QuestionList;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.roy.anubhav.stackoverflow.QuestionList.hot.HotTab;
import com.roy.anubhav.stackoverflow.QuestionList.personalized.PersonalizedTab;
import com.roy.anubhav.stackoverflow.QuestionList.weekly.WeeklyTab;

public class PageAdapter extends FragmentStatePagerAdapter {

    //Fragments to be loaded in the tabs
    private HotTab hotTab=null;
    private WeeklyTab weeklyTab=null;
    private PersonalizedTab personalizedTab = null;

    //What tagged questions should be loaded in ( value is set by the calling activity)
    public String tag = "";

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //Setting the bundle with the tag , to be passed to the fragment
        Bundle args = new Bundle();
        args.putString("tag", tag);

        switch (position) {
            case 0:
                if(personalizedTab==null)       //Check if the fragment is already initialized or not
                    personalizedTab =  PersonalizedTab.newInstance();       //If not , initialize

                personalizedTab.setArguments(args);         //Set the arguments
                return personalizedTab;                     //Return the fragment
            case 1:
                if(hotTab==null)
                    hotTab = HotTab.newInstance();
                hotTab.setArguments(args);
                return hotTab;
            case 2:
                if(weeklyTab==null)
                    weeklyTab = WeeklyTab.newInstance();
                weeklyTab.setArguments(args);
                return weeklyTab;

            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        //Set the appropriate titles for the tabs
        switch (position) {
            case 0:
                return "Your #";
            case 1:
                return "Hot";
            case 2:
                return "Week";

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
