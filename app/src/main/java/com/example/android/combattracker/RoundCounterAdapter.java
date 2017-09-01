package com.example.android.combattracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by cpalomares on 8/29/2017.
 */

public class RoundCounterAdapter extends RecyclerView.Adapter<RoundCounterAdapter.ViewHolder>{
    // Number of rounds
    private int mRounds;

    // Simple constructor
    public RoundCounterAdapter(int rounds){
        mRounds = rounds;
    }

    // Simple ViewHolder for the RoundCounter
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView roundTextView;

        // Constructor for the ViewHolder
        public ViewHolder(View itemView){
            super(itemView);

            roundTextView = (TextView) itemView.findViewById(R.id.round_number_textview);
        }
    }

    @Override
    public RoundCounterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Inflate the custom layout
        View roundView = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_round_counter, parent, false);

        return new ViewHolder(roundView);
    }

    @Override
    public void onBindViewHolder(RoundCounterAdapter.ViewHolder viewHolder, int position){
        String roundString = Integer.toString(position);

        // Set the round to equal the current position
        TextView textView = viewHolder.roundTextView;
        textView.setText(roundString);
    }

    @Override
    public int getItemCount(){
        return mRounds;
    }
}
