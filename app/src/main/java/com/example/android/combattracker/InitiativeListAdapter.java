package com.example.android.combattracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cpalomares on 9/11/2017.
 */

public class InitiativeListAdapter extends RecyclerView.Adapter<InitiativeListAdapter.CharacterViewHolder> {
    // Store a member variable for the initiative list
    private ArrayList<Character> mInitiativeList;

    // Store the context for easy access
    private Context mContext;

    // Provide a reference to the views for each data item
    // In this case, we'll have a TextView for the name,
    // with customized radio buttons for the TurnStatus
    public class CharacterViewHolder extends RecyclerView.ViewHolder{
        public TextView name;

        public CharacterViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.character_name_textview);
        }
    }

    // Provide a suitable constructor
    public InitiativeListAdapter(ArrayList<Character> initList){
        mInitiativeList = initList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_character, parent, false);

        return new CharacterViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position){
        Character character = mInitiativeList.get(position);
        holder.name.setText(character.getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount(){
        return mInitiativeList.size();
    }
}
