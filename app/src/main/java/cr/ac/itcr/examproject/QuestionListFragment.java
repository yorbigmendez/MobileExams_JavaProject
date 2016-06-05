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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import access_data.DoubleSelectionRepository;
import access_data.SingleSelectionRepository;
import access_data.TrueFalseRepository;
import adapter.AdapterQuestion;
import questions.Question;



/**
 * Fragment to cshow question list
 *
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class QuestionListFragment extends Fragment {
    /**
     * Question list
     */
    private ArrayList<Question> questionList;
    /**
     * Section index
     */
    private int sectionIndex;
    /**
     * Adapter of question
     */
    private AdapterQuestion adapter;
    /**
     * List view widget for questions
     */
    private ListView listViewQuestion;

    private OnFragmentInteractionListener mListener;

    public QuestionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Questions");
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_question_list, container, false);
        questionList = new ArrayList<>();
        Bundle b = getArguments();
        sectionIndex = b.getInt("sectionIndex");
        DoubleSelectionRepository dsr = new DoubleSelectionRepository(getContext().getApplicationContext());
        ArrayList<Question> questionArray = dsr.GetAll(sectionIndex);
        if(questionArray != null) {
            for (int i = 0; i < questionArray.size(); i++) {
                questionList.add((Question) questionArray.get(i));
            }
        }
        SingleSelectionRepository ssr = new SingleSelectionRepository(getContext().getApplicationContext());
        questionArray = ssr.GetAll(sectionIndex);
        if(questionArray!=null) {
            for (int i = 0; i < questionArray.size(); i++) {
                questionList.add((Question) questionArray.get(i));
            }
        }
        TrueFalseRepository tfr = new TrueFalseRepository(getContext().getApplicationContext());
        questionArray = tfr.GetAll(sectionIndex);
        if(questionArray!= null) {
            for (int i = 0; i < questionArray.size(); i++) {
                questionList.add((Question) questionArray.get(i));
            }
        }
        listViewQuestion = (ListView)v.findViewById(R.id.listViewQuestions);
        adapter = new AdapterQuestion(getContext().getApplicationContext(),questionList);
        listViewQuestion.setAdapter(adapter);
        listViewQuestion.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Question delete");
                alertDialog.setMessage("Are you sure you want to delete the question?");
                Log.e("Set Buttons", "This is going to test");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Delete question here
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                // Showing Alert Message
                alertDialog.show();
                return false;
            }
        });

        return v;
    }

    /**
     * Gets the fragment depending on the question
     * @param pos
     * @return
     */
    public Fragment getQuestionFragment(int pos){
        Question q = questionList.get(pos);
        switch (q.getClass().getName()){
            case "SingleSelection":
                return new SingleSelectionFragment();
            case "DoubleSelection":
                return new DoubleSelectionFragment();
            case "TrueFalse":
                return new TrueFalseFragment();
        }
        return null;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_section_fragment, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.menuAdd:
                //Fragment manager to manage a fragment transaction
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //Fragment to replaces
                Bundle bundle = new Bundle();
                //Send the position of the list item that has been selected
                bundle.putInt("sectionIndex", sectionIndex);
                Fragment f = new NewQuestion();
                f.setArguments(bundle);
                transaction.replace(R.id.content_dashboard, f);
                //On back then go back to ExamListFragment
                transaction.addToBackStack(null);
                //Commit transaction
                transaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
