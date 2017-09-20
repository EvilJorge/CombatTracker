package com.example.android.combattracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    // Initiative List
//    private ArrayList<Character> mInitiativeList = new ArrayList<>();
//
//    // ListViews and RecyclerView
//    private RecyclerView mInitiativeRecyclerView;
//
//    // Data Adapter
//    private InitiativeListAdapter mInitiativeListAdapter;

    // For mobile platforms, we'll implement the Initiative Tracker and Event Tracker
    // as two separate pages with a ViewPager

    // The number of pages in the ViewPager
    private static final int NUM_PAGES = 1;

    // The ViewPager widget, which handles animation and allows swiping horizontally
    // to access previous and next wizard steps.
    private ViewPager mTrackerPager;

    // The pager adapter, which provides the pages to the view pager widget.
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Attach lists to list Adapters
//        mInitiativeListAdapter = new InitiativeListAdapter(mInitiativeList);
//        mInitiativeRecyclerView = (RecyclerView) findViewById(R.id.initiative_recview);
//        mInitiativeRecyclerView.setAdapter(mInitiativeListAdapter);
//
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        mInitiativeRecyclerView.setLayoutManager(mLayoutManager);
//
//        // Populate the list with test data
//        populateInitList();

        // Instantiate the ViewPager and a PagerAdapter
        mTrackerPager = (ViewPager) findViewById(R.id.tracker_pager);
        mPagerAdapter = new TrackerPagerAdapter(getSupportFragmentManager());
        mTrackerPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed(){
        if (mTrackerPager.getCurrentItem() == 0){
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button.  This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mTrackerPager.setCurrentItem(mTrackerPager.getCurrentItem() - 1);
        }
    }

    // A simple pager adapter that handles 2 screens for the mobile platform
    private class TrackerPagerAdapter extends FragmentStatePagerAdapter {
        public TrackerPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            return new InitiativeListFragment();
        }

        @Override
        public int getCount(){
            return NUM_PAGES;
        }
    }

//    public void populateInitList(){
//        Character char1 = new Character("Ashwold");
//        Character char2 = new Character("Dante");
//        Character char3 = new Character("Cyran");
//        Character char4 = new Character("Brianna");
//        Character char5 = new Character("Rashe");
//
//        mInitiativeList.add(char1);
//        mInitiativeList.add(char2);
//        mInitiativeList.add(char3);
//        mInitiativeList.add(char4);
//        mInitiativeList.add(char5);
//    }
}
