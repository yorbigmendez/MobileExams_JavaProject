package cr.ac.itcr.examproject;

import android.app.AlertDialog;
import android.content.ClipboardManager;
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

import access_data.DoubleSelectionRepository;
import access_data.SingleSelectionRepository;
import questions.DoubleSelection;
import questions.SingleSelection;


/**
* Fragment to create a new Double Selection question
*
*
* @author Yorbi Mendez Soto
* @version 06/04/2016
* @since 1.0
*/
public class NewDoubleSelection extends Fragment implements View.OnClickListener{
    /**
     * Section index
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
     * Combo box option1
     */
    private CheckBox cb1;
    /**
     * Combo box option2
     */
    private CheckBox cb2;
    /**
     * Combo box option2
     */
    private CheckBox cb3;
    /**
     * Combo box option3
     */
    private CheckBox cb4;
    /**
     * Button create widget
     */
    private Button btnCreate;
    /**
     * Double Selection Repo to insert data
     */
    private DoubleSelectionRepository question_repo;
    private OnFragmentInteractionListener mListener;

    public NewDoubleSelection() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("New double selection");
        Bundle b = getArguments();
        sectionIndex = b.getInt("sectionIndex");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_double_selection, container, false);
        //Link editTexts
        question = (EditText)v.findViewById(R.id.txtQuestion);
        opc1 = (EditText)v.findViewById(R.id.txtOption1);
        opc2 = (EditText)v.findViewById(R.id.txtOption2);
        opc3 = (EditText)v.findViewById(R.id.txtOption3);
        opc4 = (EditText)v.findViewById(R.id.txtOption4);
        //Link checkboxes
        cb1 = (CheckBox)v.findViewById(R.id.checkboxOpc1);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(cb1.isChecked()){
                    System.out.println("CB1 Checked");
                }else{
                    System.out.println("CB1 Un-Checked");
                }
            }
        });
        cb2 = (CheckBox)v.findViewById(R.id.checkboxOpc2);
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(cb2.isChecked()){
                    System.out.println("CB2 Checked");
                }else{
                    System.out.println("CB2 Un-Checked");
                }
            }
        });
        cb3 = (CheckBox)v.findViewById(R.id.checkboxOpc3);
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb3.isChecked()){
                    System.out.println("CB3 Checked");
                }else{
                    System.out.println("CB3 Un-Checked");
                }
            }
        });
        cb4 = (CheckBox)v.findViewById(R.id.checkboxOpc4);
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb3.isChecked()){
                    System.out.println("CB4 Checked");
                }else{
                    System.out.println("CB4 Un-Checked");
                }
            }
        });
        //Link the create button
        btnCreate = (Button)v.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        question_repo = new DoubleSelectionRepository(getContext().getApplicationContext());
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
        }else {
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
            DoubleSelection ss = new DoubleSelection();
            ss.setQuestion(question.getText().toString());
            ss.setOpc1(opc1.getText().toString());
            ss.setOpc2(opc2.getText().toString());
            ss.setOpc3(opc3.getText().toString());
            ss.setOpc4(opc4.getText().toString());
            ss.setId_section(sectionIndex);
            ss = setAnswers(ss);
            question_repo.Save(ss);
            new AlertDialog.Builder(getContext()).setTitle("Success").setMessage("The question has been created").setIcon(android.R.drawable.ic_dialog_alert).show();

        }else{
            new AlertDialog.Builder(getContext()).setTitle("Warning").setMessage("You must fill spaces and selected 2 answers only").setIcon(android.R.drawable.ic_dialog_alert).show();
            //Fragment transaction here
        }
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
        }else
            return true;
    }

    /**
     * Set the questions answers based on the checkboxes
     * @return DoubleSelection: The DoubleSelection question with the correct answers
     */
    public DoubleSelection setAnswers(DoubleSelection ds) {
        if (cb1.isChecked() && cb2.isChecked()){
            ds.setAnswer1(cb1.getText().toString());
            ds.setAnswer2(cb2.getText().toString());
        }
        else if(cb3.isChecked() && cb4.isChecked()) {
            ds.setAnswer1(cb3.getText().toString());
            ds.setAnswer2(cb4.getText().toString());
        }
        else if(cb1.isChecked() && cb4.isChecked()){
            ds.setAnswer1(cb1.getText().toString());
            ds.setAnswer2(cb4.getText().toString());
        }
        else if(cb2.isChecked() && cb4.isChecked()) {
            ds.setAnswer1(cb2.getText().toString());
            ds.setAnswer2(cb4.getText().toString());
        }else if(cb2.isChecked() && cb3.isChecked()) {
            ds.setAnswer1(cb2.getText().toString());
            ds.setAnswer2(cb3.getText().toString());
        }else if(cb1.isChecked() && cb3.isChecked()) {
            ds.setAnswer1(cb1.getText().toString());
            ds.setAnswer2(cb3.getText().toString());
        }
        return ds;
    }

    /**
     * Checks to see of one of the checkboxes are checked
     * @return boolean: True if a checkbox is checked, false otherwise
     */
    public boolean checkReady(){
        if(cb1.isChecked() && cb2.isChecked() && !cb3.isChecked() && !cb4.isChecked())
            return true;
        if(cb3.isChecked() && cb4.isChecked()  && !cb1.isChecked() && !cb2.isChecked())
            return true;
        if(cb1.isChecked() && cb4.isChecked() && !cb2.isChecked() && !cb3.isChecked())
            return true;
        if(cb2.isChecked() && cb4.isChecked() && !cb1.isChecked() && !cb3.isChecked())
            return true;
        if(cb2.isChecked() && cb3.isChecked() && (!cb1.isChecked()) && !cb4.isChecked())
            return true;
        if(cb1.isChecked() && cb3.isChecked() && !cb2.isChecked() && !cb4.isChecked())
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
}
