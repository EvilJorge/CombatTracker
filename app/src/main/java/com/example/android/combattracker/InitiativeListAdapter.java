package com.example.android.combattracker;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cpalomares on 9/11/2017.
 */

public class InitiativeListAdapter extends RecyclerView.Adapter<InitiativeListAdapter.CharacterViewHolder> {
    // Store a member variable for the initiative list
    private ArrayList<CharGroup> mInitiativeList;

    // Store the context for easy access
    private Context mContext;

    // Define ClickListener interface
    public InitiativeListListener onClickListener;

    public interface InitiativeListListener{

        void initiativeButtonOnClick(View v, int position);
    }

    // Provide a reference to the views for each data item
    // In this case, we'll have a TextView for the name,
    // with customized radio buttons for the TurnStatus
    public class CharacterViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;
        public Button initiativeButton;
        public AppCompatSeekBar statusSwitch;

        public CharacterViewHolder(View view){
            super(view);

            nameTextView = (TextView) view.findViewById(R.id.character_name_textview);
            initiativeButton = (Button) view.findViewById(R.id.roll_initiative_button);
            statusSwitch = (AppCompatSeekBar) view.findViewById(R.id.status_switch);

            // Set up onClickListener for the initiative button
            initiativeButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    onClickListener.initiativeButtonOnClick(v, getAdapterPosition());
                }
            });
        }
    }

    // Provide a suitable constructor
    public InitiativeListAdapter(ArrayList<CharGroup> initList, InitiativeListListener listener){
        mInitiativeList = initList;
        onClickListener = listener;
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
        CharGroup character = mInitiativeList.get(position);
        int initiative = character.getInitiative();

        holder.nameTextView.setText(character.getName());

        if(initiative > 0){
            holder.initiativeButton.setText(String.format("%d", initiative));
            holder.initiativeButton.setBackgroundResource(0);
        } else {
            holder.initiativeButton.setText("");
            holder.initiativeButton.setBackgroundResource(R.drawable.init_die);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount(){
        return mInitiativeList.size();
    }
}
