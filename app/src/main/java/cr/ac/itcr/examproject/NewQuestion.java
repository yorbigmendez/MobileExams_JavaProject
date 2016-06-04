package cr.ac.itcr.examproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import questions.Question;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewQuestion.OnFragmentInteractionListener} interface
 * to handle interaction events
 */
public class NewQuestion extends Fragment implements AdapterView.OnItemSelectedListener{
    private int sectionIndex;
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
        Spinner spinner = (Spinner) v.findViewById(R.id.spinType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext().getApplicationContext(),
                R.array.question_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. Retrieve the selected item using
        switch (parent.getItemAtPosition(pos).toString()){
            case "SingleSelection":
                invokeChangeFragment(new NewSingleSelection());
            case "DoubleSelection":
                invokeChangeFragment(new NewDoubleSelection());
            case "TrueFalse":
                invokeChangeFragment(new NewTrueFalse());
        }
    }

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
        //On back then go back to ExamListFragment
        transaction.addToBackStack(null);
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
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
