package cr.ac.itcr.examproject;

import android.app.AlertDialog;
import android.content.Context;
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

import access_data.SectionRepository;
import sections.Section;


/**
 * Fragment to show section detail
 *
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class SectionDetailFragment extends Fragment implements View.OnClickListener {
    /**
     * Actual section object
     */
    private Section actual_section;
    /**
     * Section repository
     */
    private SectionRepository section_repo;
    /**
     * Section name widget
     */
    private EditText sectionName;
    /**
     * Section description widget
     */
    private EditText sectionDescription;
    /**
     * Button delete widget
     */
    private ImageButton btnDelete;
    /**
     * Button edit widget
     */
    private ImageButton btnEdit;
    /**
     * Button save widget
     */
    private ImageButton btnSave;
    /**
     * Button question widget
     */
    private ImageButton btnQuestion;
    /**
     * keylistener of edittext
     */
    private KeyListener variable;//Save the keyListener of the edit text, here is true

    private OnFragmentInteractionListener mListener;

    public SectionDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Section Detail");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_section_detail, container, false);
        //Link the widgets
        sectionName = (EditText) v.findViewById(R.id.txtSectionName);
        sectionDescription = (EditText) v.findViewById(R.id.txtSectionDescription);
        btnQuestion = (ImageButton) v.findViewById(R.id.btnQuestion);
        btnQuestion.setOnClickListener(this);
        btnSave = (ImageButton)v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnDelete = (ImageButton) v.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnEdit = (ImageButton) v.findViewById(R.id.btnSettings);
        btnEdit.setOnClickListener(this);
        //Get repo
        section_repo = new SectionRepository(getContext().getApplicationContext());
        Bundle b = getArguments();
        //Get the exam and save it as actual exam
        actual_section = section_repo.GetAll(b.getInt("examIndex")).get(b.getInt("sectionIndex"));
        // Set the text fields
        sectionName.setText(actual_section.getName());
        sectionDescription.setText(actual_section.getDescription());
        variable = sectionName.getKeyListener();
        return v;
    }

    /**
     * Manages on ImageButtonClick
     * @param v Image button clicked
     */
    @Override
    public void onClick(View v){
        // implements your things
        switch (v.getId()){
            case R.id.btnSettings:
                //0 is visible
                manageEditTexts(sectionDescription, 1);//Set editable
                manageEditTexts(sectionName, 1);
                btnSave.setVisibility(View.VISIBLE);
                btnQuestion.setVisibility(View.INVISIBLE);
                btnEdit.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnDelete:
                showDeleteAlert();
                break;

            case R.id.btnQuestion:
                //Fragment manager to manage a fragment transaction
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //Fragment to replace
                Fragment f = new QuestionListFragment();
                //Prepare bundle to send info the the other fragment
                Bundle bundle = new Bundle();
                //Send the position of the list item that has been selected
                bundle.putInt("sectionIndex", actual_section.getId_section());
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
                actual_section.setName(sectionName.getText().toString());
                actual_section.setName(sectionDescription.getText().toString());
                //Update the database
                section_repo.Update(actual_section);

                manageEditTexts(sectionName, 2);//Set non editable
                manageEditTexts(sectionDescription,2);

                btnEdit.setVisibility(View.VISIBLE);
                btnQuestion.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.INVISIBLE);
                break;

        }
    }

    /**
     * Sets editable or no the editext widgets
     * @param edit EditText to change
     * @param action action to invoke
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
     * Shows a delete alert just to make sure
     */
    public void showDeleteAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Delete section");
        alertDialog.setMessage("Are you sure you want to delete this section and its questions?");
        Log.e("Set Buttons", "This is going to test");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Delete the exam
                section_repo.Delete(actual_section);
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
