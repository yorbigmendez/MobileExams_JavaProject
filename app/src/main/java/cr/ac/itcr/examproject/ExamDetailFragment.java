package cr.ac.itcr.examproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExamDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ExamDetailFragment extends Fragment implements View.OnClickListener{
    private ExamRepository exam_repo;
    private ArrayList<Exam> exam_list;
    private Exam actual_exam;
    private EditText examName;
    private EditText examAuthor;
    private EditText examPoints;
    private ImageButton btnDelete;
    private ImageButton btnEdit;
    private ImageButton btnSection;
    private ImageButton btnSave;
    private KeyListener variable;//Save the keyListener of the edit text, here is true
    private OnFragmentInteractionListener mListener;

    public ExamDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exam_detail, container, false);
        //Link the widgets
        examName = (EditText) v.findViewById(R.id.txtExamName);
        examAuthor = (EditText) v.findViewById(R.id.txtExamAuthor);
        examPoints = (EditText) v.findViewById(R.id.txtExamPoints);
        btnSection = (ImageButton) v.findViewById(R.id.btnSection);
        btnSection.setOnClickListener(this);
        btnSave = (ImageButton)v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnDelete = (ImageButton) v.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnEdit = (ImageButton) v.findViewById(R.id.btnSettings);
        btnEdit.setOnClickListener(this);
        //Get repo
        exam_repo = new ExamRepository(getContext().getApplicationContext());
        Bundle b = getArguments();
        //Get the exam and save it as actual exam
        actual_exam = exam_repo.GetAll(0).get(b.getInt("examIndex"));
        Log.e("SELECTED INDEX IS: ", String.valueOf(actual_exam) + " JLKLKDFJ");
        // Set the text fields
        examName.setText(actual_exam.getName());
        examAuthor.setText(actual_exam.getAuthor());
        examPoints.setText(String.valueOf(actual_exam.getPoints()));
        variable = examName.getKeyListener();

        return v;
    }

    @Override
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
                //Fragment manager to manage a fragment transaction
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //Fragment to replace
                Fragment f = new SectionsFragment();
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
                break;
            case R.id.btnSave:
                //Exam to edit
                //Get the edited data
                actual_exam.setAuthor(examAuthor.getText().toString());
                actual_exam.setPoints(Float.parseFloat(examPoints.getText().toString()));
                actual_exam.setName(examName.getText().toString());
                //Update the database
                exam_repo.Update(actual_exam);
                Log.e("Exam updated", "Successfully");

                manageEditTexts(examAuthor, 2);//Set non editable
                manageEditTexts(examName,2);
                manageEditTexts(examPoints, 2);

                btnEdit.setVisibility(View.VISIBLE);
                btnSection.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.INVISIBLE);
                break;

        }
    }
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
    public void showDeleteAlert(){
        Log.e("ALert Dialog","This is going to test");
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        Log.e("Set Title","This is going to test");
        alertDialog.setTitle("Delete exam");
        Log.e("Set Message", "This is going to test");
        alertDialog.setMessage("Are you sure you want to delete this exam with all its sections questions?");
        Log.e("Set Buttons", "This is going to test");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Delete the exam
                exam_repo.Delete(actual_exam);
                getActivity().onBackPressed();
            }
        });
        Log.e("Set Butons 2", "This is going to test");
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        Log.e("Dialog show it", "This is going to test");
        // Showing Alert Message
        alertDialog.show();
        Log.e("SHOWED ITTT", "This is going to test");
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
