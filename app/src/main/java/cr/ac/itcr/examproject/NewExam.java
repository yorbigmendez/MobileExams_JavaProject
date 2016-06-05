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
import android.widget.EditText;

import access_data.ExamRepository;
import exams.Exam;


/**
 * Fragment to create new exam
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class NewExam extends Fragment {

    /**
     * Button to add exam
     */
    private Button btnAddExam;
    /**
     * Exam name widget
     */
    private EditText examName;
    /**
     * Exam Authoer wiget
     */
    private EditText examAuthor;
    /**
     * Exam points widget
     */
    private EditText examPoints;

    private OnFragmentInteractionListener mListener;

    public NewExam() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("New Exam");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exam_new, container, false);
        btnAddExam = (Button) v.findViewById(R.id.btnCreate);
        examAuthor = (EditText)v.findViewById(R.id.txtAuthor);
        examName = (EditText)v.findViewById(R.id.txtExamName);
        examPoints = (EditText)v.findViewById(R.id.txtTotalPoints);

        btnAddExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Action to do when on click happens
                if (!examAuthor.getText().toString().equals("") && !examAuthor.getText().toString().equals("Exam´s Name") && !examName.getText().toString().equals("") && !examName.getText().toString().equals("Author´s Name") && !examPoints.getText().toString().equals("") && !examPoints.getText().toString().equals("Total Points")) {
                    ExamRepository er = new ExamRepository(getContext().getApplicationContext());
                    try {
                        Exam e = captureData();
                        er.Save(e);
                        new AlertDialog.Builder(getContext()).setTitle("Success").setMessage("Exam has been created ").setIcon(android.R.drawable.ic_dialog_alert).show();
                    }catch(NumberFormatException e){
                        new AlertDialog.Builder(getContext()).setTitle("Warning").setMessage("Points must be text").setIcon(android.R.drawable.ic_dialog_alert).show();
                    }
                }else{
                    new AlertDialog.Builder(getContext()).setTitle("Warning").setMessage("Empty spaces exist").setIcon(android.R.drawable.ic_dialog_alert).show();
                }
            }
        });

        return v;
    }


    /**
     * Captures the exams data and creates and Object to be inserted in the database
     * @return e Exam: The Exam object created
     */
    public Exam captureData(){
        Exam e = new Exam();
        e.setAuthor(examAuthor.getText().toString());
        e.setName(examName.getText().toString());
        examPoints.getText().toString();
        e.setPoints(Integer.parseInt(examPoints.getText().toString()));
        return e;
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
