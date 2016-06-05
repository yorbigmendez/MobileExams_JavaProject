package cr.ac.itcr.examproject;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Fragment to create a new question
 *
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class NewQuestion extends Fragment{
    /**
     * Section index
     */
    private int sectionIndex;
    /**
     * Spinner
     */
    private Spinner spin;
    /**
     * Button create widget
     */
    private Button btnCreate;
    private OnFragmentInteractionListener mListener;

    public NewQuestion() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Add Question");
        Bundle b = getArguments();
        sectionIndex = b.getInt("sectionIndex");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_question, container, false);
        btnCreate = (Button)v.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinSelection = spin.getSelectedItem().toString();
                // An item was selected. Retrieve the selected item using
                switch (spinSelection){
                    case "Single Selection":
                        invokeChangeFragment(new NewSingleSelection());
                        Log.e("Single Selection","Single selection is selected");
                        break;
                    case "Double Selection":
                        Log.e("Double Selection","Double selection is selected");
                        invokeChangeFragment(new NewDoubleSelection());
                        break;
                    case "True False":
                        Log.e("True False", "New True false is selected");
                        invokeChangeFragment(new NewTrueFalse());
                        break;
                    case "":
                        new AlertDialog.Builder(getContext()).setTitle("Warning").setMessage("The selection is invalid").setIcon(android.R.drawable.ic_dialog_alert).show();
                        break;
                }
            }
        });
        spin = (Spinner) v.findViewById(R.id.spinType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext().getApplicationContext(),
                R.array.question_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spin.setAdapter(adapter);

        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * Switch fragments depending on the fragment parameter.
     * @param f Fragment: The fragment that we are going to invoke in the fragmnet transaction
     */
    public void invokeChangeFragment(Fragment f){
        //Item on Clicke action here
        //Fragment manager to manage a fragment transaction
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //Prepare bundle to send info the the other fragment
        Bundle bundle = new Bundle();
        //Send the position of the list item that has been selected
        bundle.putInt("sectionIndex",sectionIndex);
        f.setArguments(bundle);
        transaction.replace(R.id.content_dashboard, f);
        //Commit transaction
        transaction.commit();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
