package cr.ac.itcr.examproject;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import access_data.SectionRepository;
import access_data.SingleSelectionRepository;
import questions.SingleSelection;


/**
 * Fragment to create a new Single Selection question
 *
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class NewSingleSelection extends Fragment implements View.OnClickListener{
    /**
     * Section idex
     */
    private int sectionIndex;
    /**
     * Question widget
     */
    private EditText question;
    /**
     * Option 1 widget
     */
    private EditText opc1;
    /**
     * Option 2 widget
     */
    private EditText opc2;
    /**
     * Option 3 widget
     */
    private EditText opc3;
    /**
     * Option 4 widget
     */
    private EditText opc4;
    /**
     * Check box 1
     */
    private CheckBox cb1;
    /**
     * Check box 2
     */
    private CheckBox cb2;
    /**
     * Check box 3
     */
    private CheckBox cb3;
    /**
     * Check box 4
     */
    private CheckBox cb4;
    /**
     * Button to create the question
     */
    private Button btnCreate;
    /**
     * Listener
     */
    private OnFragmentInteractionListener mListener;
    /**
     * Single Selection repository for management
     */
    private SingleSelectionRepository question_repo;
    public NewSingleSelection() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("New single selection");
        Bundle b = getArguments();
        sectionIndex = b.getInt("sectionIndex");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_single_selection, container, false);
        //Link editTexts
        question = (EditText)v.findViewById(R.id.txtQuestion);
        opc1 = (EditText)v.findViewById(R.id.txtOption1);
        opc2 = (EditText)v.findViewById(R.id.txtOption2);
        opc3 = (EditText)v.findViewById(R.id.txtOption3);
        opc4 = (EditText)v.findViewById(R.id.txtOption4);
        //Link checkboxes
        cb1 = (CheckBox)v.findViewById(R.id.checkboxOpc1);
        cb2 = (CheckBox)v.findViewById(R.id.checkboxOpc2);
        cb3 = (CheckBox)v.findViewById(R.id.checkboxOpc3);
        cb4 = (CheckBox)v.findViewById(R.id.checkboxOpc4);
        //Link the create button
        btnCreate = (Button)v.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        question_repo = new SingleSelectionRepository(getContext().getApplicationContext());
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
            SingleSelection ss = new SingleSelection();
            ss.setQuestion(question.getText().toString());
            ss.setOpc1(opc1.getText().toString());
            ss.setOpc2(opc2.getText().toString());
            ss.setOpc3(opc3.getText().toString());
            ss.setOpc4(opc4.getText().toString());
            ss.setId_section(sectionIndex);
            ss.setAnswer(getSelectedAnswer());
            question_repo.Save(ss);
            new AlertDialog.Builder(getContext()).setTitle("Success").setMessage("The question has been created").setIcon(android.R.drawable.ic_dialog_alert).show();

        }else{
            new AlertDialog.Builder(getContext()).setTitle("Warning").setMessage("You must fill spaces or select answers").setIcon(android.R.drawable.ic_dialog_alert).show();
            //Fragment transaction here
        }
    }

    /**
     *  Gets the selected answer
     * @return String: The checked box text (opc1, opc2, opc3, opc4)
     */
    public String getSelectedAnswer(){
        if(cb1.isChecked())
            return cb1.getText().toString();
        if(cb3.isChecked())
            return cb2.getText().toString();
        if(cb3.isChecked())
            return cb3.getText().toString();
        if(cb4.isChecked())
            return cb4.getText().toString();
        return "";
    }

    /**
     * Checks to see if all the fields are not empty
     * @return boolean: true if fields and not empty, false if empty
     */
    public boolean isReady(){
        if(question.getText().toString().equals("") || question.getText().toString().equals("Question")  ){
            return false;
        }
        if(opc1.getText().toString().equals("") || opc1.getText().toString().equals("Option 1")){
            return false;
        }
        if(opc2.getText().toString().equals("") || opc1.getText().toString().equals("Option 2")){
            return false;
        }
        if(opc3.getText().toString().equals("") || opc1.getText().toString().equals("Option 3")){
            return false;
        }
        if(opc1.getText().toString().equals("") || opc1.getText().toString().equals("Option 1")){
            return false;
        }
        if(!checkReady()){
            return false;
        }
        return true;
    }

    /**
     * Checks to see of one of the checkboxes are checked
     * @return boolean: True if a combobox is checked, false otherwise
     */
    public boolean checkReady(){
        if(cb1.isChecked() && !cb2.isChecked() && !cb3.isChecked() && !cb4.isChecked())
            return true;
        if(!cb3.isChecked() && !cb4.isChecked()  && !cb1.isChecked() && cb2.isChecked())
            return true;
        if(!cb1.isChecked() && !cb4.isChecked() && !cb2.isChecked() && cb3.isChecked())
            return true;
        if(!cb2.isChecked() && cb4.isChecked() && !cb1.isChecked() && !cb3.isChecked())
            return true;
        return false;
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


    /**
     * Action to do if a checkbox has been clicked
     * @param view The Checkbox that was clicked
     */
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            // Check which checkbox was clicked
            switch (view.getId()) {
                case R.id.checkboxOpc1:
                    ((CheckBox) view).setChecked(true);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                    break;
                case R.id.checkboxOpc2:
                    cb2.setChecked(true);
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                    break;
                case R.id.checkboxOpc3:
                    cb3.setChecked(true);
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb4.setChecked(false);
                    break;
                case R.id.checkboxOpc4:
                    cb4.setChecked(true);
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    break;
            }
        }
    }

}
