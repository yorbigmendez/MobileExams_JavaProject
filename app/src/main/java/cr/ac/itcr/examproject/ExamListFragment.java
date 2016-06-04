package cr.ac.itcr.examproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import access_data.ExamRepository;
import adapter.AdapterExam;
import exams.Exam;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExamListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ExamListFragment extends Fragment {
    private ListView listViewExams;
    private static AdapterExam adapter;
    private ExamRepository repository;


    private OnFragmentInteractionListener mListener;

    public ExamListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Exams");
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_exams, container, false);
        listViewExams = (ListView)v.findViewById(R.id.listViewExams);
        listViewExams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Fragment manager to manage a fragment transaction
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //Fragment to replace
                Fragment f = new ExamDetailFragment();
                //Prepare bundle to send info the the other fragment
                Bundle bundle = new Bundle();
                //Send the position of the list item that has been selected
                bundle.putInt("examIndex",position);
                f.setArguments(bundle);
                transaction.replace(R.id.content_dashboard, f);
                //On back then go back to ExamListFragment
                transaction.addToBackStack(null);
                //Commit transaction
                transaction.commit();
            }
        });

        repository = new ExamRepository(getContext().getApplicationContext());
        ArrayList<Exam> e = repository.GetAll(0);
        //Empty list of exams
        if(e.isEmpty())
            showEmptyDialog();
        adapter = new AdapterExam(getActivity().getApplicationContext(),repository.GetAll(0));
        listViewExams.setAdapter(adapter);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void showEmptyDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("No exams");
        alertDialog.setMessage("There are no exams created, would you like to create one?");
        Log.e("Set Buttons", "This is going to test");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Delete the exam
                //Fragment manager to manage a fragment transaction
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //Fragment to replace
                Fragment f = new NewExam();
                transaction.replace(R.id.content_dashboard, f);
                //On back then go back to ExamListFragment
                transaction.addToBackStack(null);
                //Commit transaction
                transaction.commit();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Showing Alert Message
        alertDialog.show();
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
