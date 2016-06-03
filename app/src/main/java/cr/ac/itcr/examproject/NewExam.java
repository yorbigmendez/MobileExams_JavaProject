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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewExam.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewExam#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewExam extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //Attributes of the class
    private Button btnAddExam;
    private EditText examName;
    private EditText examAuthor;
    private EditText examPoints;

    private OnFragmentInteractionListener mListener;

    public NewExam() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewExam.
     */
    // TODO: Rename and change types and number of parameters
    public static NewExam newInstance(String param1, String param2) {
        NewExam fragment = new NewExam();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_exam, container, false);
        btnAddExam = (Button) v.findViewById(R.id.btnCreate);
        examAuthor = (EditText)v.findViewById(R.id.txtAuthor);
        examName = (EditText)v.findViewById(R.id.txtExamName);
        examPoints = (EditText)v.findViewById(R.id.txtTotalPoints);

        btnAddExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Action to do when on click happens
                if (examAuthor.getText().toString() != "" && examName.getText().toString() != "" && examPoints.getText().toString() != "") {
                    ExamRepository er = new ExamRepository(getContext().getApplicationContext());
                    Exam e = captureData();
                    er.Save(e);
                    new AlertDialog.Builder(getContext()).setTitle("Success").setMessage("Exam has been created ").setIcon(android.R.drawable.ic_dialog_alert).show();
                }
            }
        });

        return v;
    }

    //Captures the data
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
