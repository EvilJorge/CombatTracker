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
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InitiativeListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InitiativeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InitiativeListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InitiativeListFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static InitiativeListFragment newInstance(String param1, String param2) {
//        InitiativeListFragment fragment = new InitiativeListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

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
                showEnterInitiativePopup(v);
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

    public void enterInitiative(View v){
        int initiative;

//        // Get value from Enter Initiative EditText view in Popup Window
//        initiative = Integer.parseInt(
//                ((EditText) mEnterInitiativeWindow.getContentView().findViewById(R.id.enter_initiative_text)).getText().toString()
//        );

        // Close Popup window
        mEnterInitiativeWindow.dismiss();
    }

    /** Other Methods **/

    // Show popup for entering initiative.
    private void showEnterInitiativePopup(View v){
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

        // Setup onClick for Entery Button
        View.OnClickListener enterClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterInitiative(view);
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

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
