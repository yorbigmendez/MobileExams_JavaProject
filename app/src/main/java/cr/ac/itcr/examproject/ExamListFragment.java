package cr.ac.itcr.examproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
* Fragment that lists the exams
*
*
* @author Yorbi Mendez Soto
* @version 06/04/2016
* @since 1.0
*/
public class ExamListFragment extends Fragment {
    private ListView listViewExams;
    private static AdapterExam adapter;
    private ExamRepository repository;
    private String action;

    /**
     * Empty constructor of class
     */
    public ExamListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * Initializes the widgets of the view
     * @param inflater Used to inflate te view
     * @param container Container we are going to inflate the view
     * @param savedInstanceState SavedState of the app
     * @return View: The view created
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Exams");
        setHasOptionsMenu(true);
        Bundle b = getArguments();
        action = b.getString("action");
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_exams, container, false);
        listViewExams = (ListView)v.findViewById(R.id.listViewExams);
        listViewExams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Fragment manager to manage a fragment transaction
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                if(action.equals("manage")){
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
                }else{//Start the exam
                    Intent intent = new Intent(getContext().getApplicationContext(),RealizeExam.class);
                    intent.putExtra("examIndex",position);
                    startActivity(intent);
                }
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

    /**
     * Show a dialog when the exams are empty
     */
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

    /**
     * Calls super class on fragment attached
     * @param context context of the app
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * Called when fragment is detached
     */
    @Override
    public void onDetach() {
        super.onDetach();
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
