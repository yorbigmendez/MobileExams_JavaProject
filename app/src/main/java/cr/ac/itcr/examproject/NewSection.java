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

import access_data.SectionRepository;
import sections.Section;


/**
 * Fragment to create new section
 *
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class NewSection extends Fragment {
    /**
     * Section name widget
     */
    private EditText txtName;
    /**
     * Text description widget
     */
    private EditText txtDescription;
    /**
     * Create section button widget
     */
    private Button btnCreateSection;
    /**
     * Exam index
     */
    private int examIndex;
    /**
     * Section repo to manage sections
     */
    private SectionRepository section_repo;


    private OnFragmentInteractionListener mListener;

    public NewSection() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("New Section");
        Bundle b = getArguments();
        examIndex = b.getInt("examIndex");
        section_repo = new SectionRepository(getContext().getApplicationContext());
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_section_new, container, false);
        txtDescription = (EditText)v.findViewById(R.id.txtDescription);
        txtName = (EditText)v.findViewById(R.id.txtSectionName);
        btnCreateSection = (Button)v.findViewById(R.id.btnCreateSection);
        btnCreateSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On button click here
                if(!txtName.getText().toString().equals("") && !txtName.getText().toString().equals("Section Name") && !txtDescription.getText().toString().equals("") && !txtDescription.getText().toString().equals("Section Description")){
                    section_repo.Save(new Section(txtName.getText().toString(),txtDescription.getText().toString(),examIndex));
                    new AlertDialog.Builder(getContext()).setTitle("Success").setMessage("Section has been created ").setIcon(android.R.drawable.ic_dialog_alert).show();
                }else{
                    new AlertDialog.Builder(getContext()).setTitle("Holy Guacamole!").setMessage("All the fields must be filled.").setIcon(android.R.drawable.ic_dialog_alert).show();
                }
            }
        });
        return v;
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
