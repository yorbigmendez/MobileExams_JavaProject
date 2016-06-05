package cr.ac.itcr.examproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import access_data.ExamRepository;
import exams.Exam;


/**
 * Fragment ot show detail of exam
 *
 * Show the detail of an exam.
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class ExamDetailFragment extends Fragment{
    /**
     * Exam repository
     */
    private ExamRepository exam_repo;
    /**
     * Actual exam
     */
    private Exam actual_exam;
    /**
     * Widget of examName
     */
    private EditText examName;
    /**
     * Widget of examAuthor
     */
    private EditText examAuthor;
    /**
     * Widget of examPoints
     */
    private EditText examPoints;
    /**
     * Widget of delete button
     */
    private ImageButton btnDelete;
    /**
     * Widget of edit button
     */
    private ImageButton btnEdit;
    /**
     * Widget of section button
     */
    private ImageButton btnSection;
    /**
     * Widget of save button
     */
    private ImageButton btnSave;
    /**
     * Key listener of EditText
     */
    private KeyListener variable;//Save the keyListener of the edit text, here is true

    /**
     * Empty Constructor
     */
    public ExamDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Examen Detail");
        View v = inflater.inflate(R.layout.fragment_exam_detail, container, false);
        //Link the widgets
        examName = (EditText) v.findViewById(R.id.txtExamName);
        examAuthor = (EditText) v.findViewById(R.id.txtExamAuthor);
        examPoints = (EditText) v.findViewById(R.id.txtExamPoints);
        btnSection = (ImageButton) v.findViewById(R.id.btnSection);
        btnSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment manager to manage a fragment transaction
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //Fragment to replace
                Fragment f = new SectionsListFragment();
                //Prepare bundle to send info the the other fragment
                Bundle bundle = new Bundle();
                //Send the position of the list item that has been selected
                bundle.putInt("examIndex", actual_exam.getId());
                f.setArguments(bundle);
                transaction.replace(R.id.content_dashboard, f);
                //On back then go back to ExamListFragment
                transaction.addToBackStack(null);
                //Commit transaction
                transaction.commit();
            }
        });
        btnSave = (ImageButton)v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnDelete = (ImageButton) v.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnEdit = (ImageButton) v.findViewById(R.id.btnSettings);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //Get repo
        exam_repo = new ExamRepository(getContext().getApplicationContext());
        Bundle b = getArguments();
        //Get the exam and save it as actual exam
        actual_exam = exam_repo.GetAll(0).get(b.getInt("examIndex"));
        // Set the text fields
        examName.setText(actual_exam.getName());
        examAuthor.setText(actual_exam.getAuthor());
        examPoints.setText(String.valueOf(actual_exam.getPoints()));
        variable = examName.getKeyListener();
        return v;
    }

    /**
     * Manages the button click events
     * @param v Button clicked
     */
    public void onClick(View v){
        // implements your things
        switch (v.getId()){
            case R.id.btnSettings:
                //0 is visible
                manageEditTexts(examAuthor, 1);//Set editable
                manageEditTexts(examName, 1);
                manageEditTexts(examPoints, 1);
                btnSave.setVisibility(View.VISIBLE);
                btnSection.setVisibility(View.INVISIBLE);
                btnEdit.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnDelete:
                showDeleteAlert();
                break;
            case R.id.btnSection:
                break;
            case R.id.btnSave:
                //Exam to edit
                //Get the edited data
                actual_exam.setAuthor(examAuthor.getText().toString());
                actual_exam.setPoints(Float.parseFloat(examPoints.getText().toString()));
                actual_exam.setName(examName.getText().toString());
                //Update the database
                exam_repo.Update(actual_exam);

                manageEditTexts(examAuthor, 2);//Set non editable
                manageEditTexts(examName,2);
                manageEditTexts(examPoints, 2);

                btnEdit.setVisibility(View.VISIBLE);
                btnSection.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.INVISIBLE);
                break;

        }
    }

    /**
     * Manages the editTexts, sets editable and what not
     * @param edit Edit text to editt
     * @param action 0: Show, 1: Hide
     */
    public void manageEditTexts(EditText edit,int action){

        switch (action) {
            case 1:
                edit.setKeyListener(variable);
                break;

            case 2:
                edit.setKeyListener(null);
                break;
        }
    }

    /**
     * Alert to show in case of deletion
     */
    public void showDeleteAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Delete exam");
        Log.e("Set Message", "This is going to test");
        alertDialog.setMessage("Are you sure you want to delete this exam with all its sections questions?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Delete the exam
                exam_repo.Delete(actual_exam);
                getActivity().onBackPressed();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Showing Alert Message
        alertDialog.show();
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
