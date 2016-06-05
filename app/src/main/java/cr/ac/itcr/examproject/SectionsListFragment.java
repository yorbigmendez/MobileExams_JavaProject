package cr.ac.itcr.examproject;

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

import access_data.SectionRepository;
import adapter.SectionAdapter;
import sections.Section;


/**
 * Fragment to show section list
 *
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class SectionsListFragment extends Fragment{
    /**
     * Exam index
     */
    private int examIndex;
    /**
     * List View of sections, widget
     */
    private ListView listViewSections;
    /**
     * Section repository
     */
    private SectionRepository section_repo;
    /**
     * Exam sections
     */
    private ArrayList<Section> examSections;
    /**
     * List view adapter
     */
    private SectionAdapter adapter;


    public SectionsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    /**
     * Handles the action bar click. The action bar will automatically handle clicks on the Home/Up button,
     * so long as you specify a parent activity in AndroidManifest.xml
     * @param item The menu item that has been selected.
     * @return Returns the action upon the on item selection.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfyoutuStatement
        if (id == R.id.menuDelete) {
            return true;
        }
        if (id == R.id.menuAdd) {
            //Fragment manager to manage a fragment transaction
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            //Fragment to replace
            Fragment f = new NewSection();
            //Prepare bundle to send info the the other fragment
            Bundle bundle = new Bundle();
            //Send the position of the list item that has been selected
            bundle.putInt("examIndex",examIndex);
            f.setArguments(bundle);
            transaction.replace(R.id.content_dashboard, f);
            //On back then go back to ExamListFragment
            transaction.addToBackStack(null);
            //Commit transaction
            transaction.commit();
        }
        if (id == R.id.menuSettings) {
            return true;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getActivity().setTitle("Exam Sections");
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v =  inflater.inflate(R.layout.fragment_sections, container, false);
        Bundle b = getArguments();
        examIndex = b.getInt("examIndex");
        section_repo = new SectionRepository(getContext().getApplicationContext());
        examSections = section_repo.GetAll(examIndex);
        listViewSections = (ListView)v.findViewById(R.id.listViewSections);
        adapter = new SectionAdapter(getActivity().getApplicationContext(),examSections);
        listViewSections.setAdapter(adapter);

        listViewSections.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Fragment manager to manage a fragment transaction
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //Fragment to replace
                Fragment f = new SectionDetailFragment();
                //Prepare bundle to send info the the other fragment
                Bundle bundle = new Bundle();
                //Send the position of the list item that has been selected
                bundle.putInt("examIndex",examIndex);
                bundle.putInt("sectionIndex",position);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_section_fragment, menu);
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
