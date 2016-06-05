package cr.ac.itcr.examproject;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import access_data.TrueFalseRepository;
import questions.TrueFalse;



/**
 * Fragment to create a new True False Question
 *
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class NewTrueFalse extends Fragment implements View.OnClickListener{
    /**
     * Section index
     */
    private int sectionIndex;
    /**
     * Radio button true
     */
    private RadioButton radioTrue;
    /**
     * False radio button
     */
    private RadioButton radioFalse;
    /**
     * Question widget
     */
    private EditText question;
    /**
     * True False question repository for DB access
     */
    private TrueFalseRepository true_false_repo;
    /**
     * Button create question widget
     */
    private Button btnCreate;

    private OnFragmentInteractionListener mListener;

    public NewTrueFalse() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("New True False");
        Bundle b = getArguments();
        sectionIndex = b.getInt("sectionIndex");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_true_false, container, false);
        question = (EditText)v.findViewById(R.id.txtQuestion);
        radioTrue = (RadioButton)v.findViewById(R.id.radio_true);
        radioFalse = (RadioButton)v.findViewById(R.id.radio_false);
        true_false_repo = new TrueFalseRepository(getContext().getApplicationContext());
        btnCreate = (Button)v.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(isReady()){
            TrueFalse tf = new TrueFalse();
            tf.setId_section(sectionIndex);
            tf.setQuestion(question.getText().toString());
            tf.setAnswer(getAnswer());
            true_false_repo.Save(tf);
            new AlertDialog.Builder(getContext()).setTitle("Success").setMessage("The question has been created").setIcon(android.R.drawable.ic_dialog_alert).show();

        }else{
            new AlertDialog.Builder(getContext()).setTitle("Warning").setMessage("You must fill spaces or select answers").setIcon(android.R.drawable.ic_dialog_alert).show();
            //Fragment transaction here
        }
    }

    /**
     * Gets the correct answer
     * @return String of true or false
     */
    public String getAnswer(){
        if(radioFalse.isChecked())
            return "False";
        if(radioTrue.isChecked())
            return "True";
        return  "";
    }

    /**
     * Checks to see if all the fields are not empty
     * @return boolean: true if fields and not empty, false if empty
     */
    public boolean isReady(){
        if(question.getText().equals("") || question.getText().equals("Question")  ){
            return false;
        }else if(!radioTrue.isChecked() && !radioFalse.isChecked()){
            return false;
        }
        return true;
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
