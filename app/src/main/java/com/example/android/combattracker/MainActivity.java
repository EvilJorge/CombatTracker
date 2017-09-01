package com.example.android.combattracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private int mRounds = 10;

    // Round Counter
    private RecyclerView mRoundCounter;
    private RoundCounterAdapter mRoundCounterAdapter;
    private LinearLayoutManager mRoundCounterLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRoundCounter = (RecyclerView) findViewById(R.id.round_list_recview);
        mRoundCounter.setHasFixedSize(true);
        mRoundCounterAdapter = new RoundCounterAdapter(mRounds);
        mRoundCounter.setAdapter(mRoundCounterAdapter);

        // Set LayoutManager
        mRoundCounterLayoutManager = new LinearLayoutManager(this);
        mRoundCounterLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRoundCounter.setLayoutManager(mRoundCounterLayoutManager);
    }
}
