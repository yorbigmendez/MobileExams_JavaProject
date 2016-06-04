package cr.ac.itcr.examproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class QuestionListFragment extends Fragment {
    private ArrayList<Question> questionList;
    private int sectionIndex;
    private AdapterQuestion adapter;
    private DoubleSelectionRepository doubleSelectRepo;
    private SingleSelectionRepository singleSelectRepo;
    private TrueFalseRepository trueFalseRepo;
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
        questionList.addAll(new DoubleSelectionRepository(getContext().getApplicationContext()).GetAll(sectionIndex));
        questionList.addAll(new SingleSelectionRepository(getContext().getApplicationContext()).GetAll(sectionIndex));
        questionList.addAll(new TrueFalseRepository(getContext().getApplicationContext()).GetAll(sectionIndex));
        listViewQuestion = (ListView)v.findViewById(R.id.listViewQuestions);
        adapter = new AdapterQuestion(getContext().getApplicationContext(),questionList);
        listViewQuestion.setAdapter(adapter);
        listViewQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Item on Clicke action here
                //Fragment manager to manage a fragment transaction
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                //Fragment to replace
                Fragment f = getQuestionFragment(position);
                //Prepare bundle to send info the the other fragment
                Bundle bundle = new Bundle();
                //Send the position of the list item that has been selected
                bundle.putInt("questionIndex",position);
                f.setArguments(bundle);
                transaction.replace(R.id.content_dashboard, f);
                //On back then go back to ExamListFragment
                transaction.addToBackStack(null);
                //Commit transaction
                transaction.commit();
            }
        });

        return v;
    }

    public Fragment getQuestionFragment(int pos){
        Question q = questionList.get(pos);
        switch (q.getClass().getName()){
            case "SingleSelection":
                return new SingleSelectionDetailFragment();
            case "DoubleSelection":
                return new DoubleSelectionDetailFragment();
            case "TrueFalse":
                return new TrueFalseDetailFragment();
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
