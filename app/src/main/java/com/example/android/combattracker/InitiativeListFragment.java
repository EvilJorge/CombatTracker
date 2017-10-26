package com.example.android.combattracker;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InitiativeListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InitiativeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InitiativeListFragment extends Fragment {
//    private OnFragmentInteractionListener mListener;

    // Initiative List
    private ArrayList<CharGroup> mInitiativeList = new ArrayList<>();

    // ListViews and RecyclerView
    private RecyclerView mInitiativeRecyclerView;

    // Data Adapter
    private InitiativeListAdapter mInitiativeListAdapter;

    // Adapter Listener
    private InitiativeListAdapter.InitiativeListListener mInitiativeListListener;

    // Popup Window
    public PopupWindow mEnterInitiativeWindow;

    public InitiativeListFragment() {
        // Required empty public constructor
    }

    /** Class Overrides **/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_initiative_list, container, false);

        mInitiativeListListener = new InitiativeListAdapter.InitiativeListListener() {
            @Override
            public void initiativeButtonOnClick(View v, int position) {
                String name = mInitiativeList.get(position).getName();

                Toast.makeText(getContext(), name + "'s initiative button pressed.", Toast.LENGTH_SHORT).show();
                showEnterInitiativePopup(v, position);
            }
        };

        // Attach lists to list Adapters
        mInitiativeListAdapter = new InitiativeListAdapter(mInitiativeList, mInitiativeListListener);
        mInitiativeRecyclerView = (RecyclerView) rootView.findViewById(R.id.initiative_recview);
        mInitiativeRecyclerView.setAdapter(mInitiativeListAdapter);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mInitiativeRecyclerView.setLayoutManager(mLayoutManager);

        // Populate the list with temp data
        populateInitList();
        return rootView;
    }

    /** Event Handlers **/

    // Button handler to return from Enter Initiative Popup.

    public void enterInitiative(View v, int position){
        EditText initEditText = (EditText) mEnterInitiativeWindow.getContentView().findViewById(R.id.enter_initiative_text);
        int initiative;

        // Get value from Enter Initiative EditText view in Popup Window
//        initiative = Integer.parseInt(
//                ((EditText) mEnterInitiativeWindow.getContentView().findViewById(R.id.enter_initiative_text)).getText().toString()
//        );

        // If EditText entry is valid, set initiative value and close window.
        // Else, show enter message.
        if (!initEditText.getText().toString().equals("")) {
            initiative = Integer.parseInt(initEditText.getText().toString());
            mInitiativeList.get(position).setInitiative(initiative);
            //mInitiativeListAdapter.notifyItemChanged(position);

            // Sort List and notify adapter of data change
            Collections.sort(mInitiativeList, new InitiativeComparator());
            Collections.reverse(mInitiativeList);
            mInitiativeListAdapter.notifyDataSetChanged();

            // Close Popup window
            mEnterInitiativeWindow.dismiss();
        } else {
            Toast.makeText(getContext(), "Enter an initiative value.", Toast.LENGTH_SHORT).show();
        }
    }

    /** Other Methods **/

    // Show popup for entering initiative.
    private void showEnterInitiativePopup(View v, final int position){
        // Inflate PopUp Window to enter initiative
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.popup_enter_initiative, null);

        // Initialize a new instance of popup window
        mEnterInitiativeWindow = new PopupWindow(popUpView,
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        // Set an elevation value for popup window if API is 21 or greater.
        if(Build.VERSION.SDK_INT >= 21){
            mEnterInitiativeWindow.setElevation(5.0f);
        }

        mEnterInitiativeWindow.setFocusable(true);
        mEnterInitiativeWindow.update();
        mEnterInitiativeWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        // Setup onClick for Enter Button
        View.OnClickListener enterClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterInitiative(view, position);
            }
        };
        Button enterInitiativeButton = (Button) popUpView.findViewById(R.id.enter_initiative_button);
        enterInitiativeButton.setOnClickListener(enterClickListener);
    }

    public void populateInitList(){
        CharGroup char1 = new CharGroup("Ashwold");
        CharGroup char2 = new CharGroup("Dante");
        CharGroup char3 = new CharGroup("Cyran");
        CharGroup char4 = new CharGroup("Brianna");
        CharGroup char5 = new CharGroup("Rashe");

        mInitiativeList.add(char1);
        mInitiativeList.add(char2);
        mInitiativeList.add(char3);
        mInitiativeList.add(char4);
        mInitiativeList.add(char5);
    }

    /** Additional Classes **/

    // Comparator used to sort by Initiative
    public class InitiativeComparator implements Comparator<CharGroup>{
        public int compare(CharGroup first, CharGroup second){
            int firstInit = first.getInitiative();
            int secondInit = second.getInitiative();

            if(firstInit == secondInit) {
                return 0;
            } else if(firstInit > secondInit){
                return 1;
            } else {
                return -1;
            }
        }
    }
}
