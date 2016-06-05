package cr.ac.itcr.examproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;

import access_data.DoubleSelectionRepository;
import access_data.SectionRepository;
import access_data.SingleSelectionRepository;
import access_data.TrueFalseRepository;
import questions.Question;
import sections.Section;

/**
 * Activity for evaluating and exam
 *
 * This activity is used to go through the list sections of an exam and show the questions.
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class RealizeExam extends AppCompatActivity {
    /**
     * The id of the exam we are evaluating
     */
    private int exam_id;
    /**
     * The exams sections
     */
    private ArrayList<Section> section_list;
    /**
     * The section repository
     */
    private SectionRepository section_repo;
    /**
     * List of questions of sections
     */
    private ArrayList<Question> question_list;
    /**
     * Single selection repository
     */
    private SingleSelectionRepository single_select_repo;
    /**
     * Double Seletion Repository
     */
    private DoubleSelectionRepository double_select_repo;
    /**
     * True False repository
     */
    private TrueFalseRepository true_false_repo;

    /**
     * Initialize the activity
     * @param savedInstanceState Saved state of the app before calling here
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realize_exam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Get the extras, in this case exams index
        Bundle extras = getIntent().getExtras();
        exam_id = extras.getInt("examIndex");
        //Instantiate the repositories
        section_repo = new SectionRepository(getApplicationContext());
        section_list = section_repo.GetAll(exam_id);//Get exam section
        double_select_repo = new DoubleSelectionRepository(getApplicationContext());
        single_select_repo = new SingleSelectionRepository(getApplicationContext());
        true_false_repo= new TrueFalseRepository(getApplicationContext());
        doExam();
    }

    /**
     * Runs the list of sections of each exam and invoked the list of questions that it has
     */
    public void doExam(){
        for(int i = 0; i < section_list.size(); i++){
            //Get the question list based on the actual section
            question_list = getQuestions(section_list.get(i).getId_section());
            //Show the question
            showQuestions();
        }
    }

    /**
     * Shows in the fragment each question that the fragment has
     */
    public void showQuestions(){
        Question q;
        for(int i = 0; i<question_list.size(); i++){
            q = question_list.get(i);
            Fragment f = question_list.get(i).showQuestion();

            // Check that the activity is using the layout version with
            // the fragment_container FrameLayout
            if (findViewById(R.id.fragment_container) != null) {
                // Add the fragment to the 'fragment_container' FrameLayout
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, f).commit();
            }
        }
    }

    /**
     * Get all the questions from a section
     * @param id int: Id of the section we are getting the questions from
     * @return ArrayList<Question>: The array list of the questions from that section
     */
    public ArrayList<Question> getQuestions(int id){
        question_list = new ArrayList<>();
        question_list = double_select_repo.GetAll(id);
        if(question_list != null) {
            for (int i = 0; i < question_list.size(); i++) {
                question_list.add((Question) question_list.get(i));
            }
        }
        question_list = single_select_repo.GetAll(id);
        if(question_list!=null) {
            for (int i = 0; i < question_list.size(); i++) {
                question_list.add((Question) question_list.get(i));
            }
        }
        question_list = true_false_repo.GetAll(id);
        if(question_list!= null) {
            for (int i = 0; i < question_list.size(); i++) {
                question_list.add((Question) question_list.get(i));
            }
        }
        return question_list;
    }


}
