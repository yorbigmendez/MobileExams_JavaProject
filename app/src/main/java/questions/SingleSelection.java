package questions;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import cr.ac.itcr.examproject.R;
import cr.ac.itcr.examproject.SingleSelectionFragment;

/**
 * Class for Single Selection Questions
 *
 * @author Yorbi Méndez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class SingleSelection implements Question {
    /**
     * Question id
     */
    private int id_question;
    /**
     * The question of the class
     */
    private String question;
    /**
     * Section the question belongs to
     */
    private int id_section;
    /**
     * Option 1 of question
     */
    private String opc1;
    /**
     * Option 2 of question
     */
    private String opc2;
    /**
     * Option 3 of question
     */
    private String opc3;
    /**
     * Option 4 of question
     */
    private String opc4;
    /**
     * Answer of question
     */
    private String answer;

    /**
     * Gets the id of the question
     * @return int: ID
     */
    public int getId_question() {
        return id_question;
    }

    /**
     * Sets the id of the question
     * @param id_question int: Question id
     */
    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    /**
     * Gets the question of the class
     * @return String: Question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the question of class
     * @param question String: Question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Gets the option 1
     * @return String: Questions option #1
     */
    public String getOpc1() {
        return opc1;
    }

    /**
     * Sets the option 1
     * @param opc1 String: Option 1
     */
    public void setOpc1(String opc1) {
        this.opc1 = opc1;
    }

    /**
     * Gets the option 2
     * @return String: Option 2
     */
    public String getOpc2() {
        return opc2;
    }

    /**
     * Sets the option 2
     * @param opc2 String: Option 2
     */
    public void setOpc2(String opc2) {
        this.opc2 = opc2;
    }

    /**
     * Gets the option 3
     * @return String: Option 3
     */
    public String getOpc3() {
        return opc3;
    }

    /**
     * Sets the option 3
     * @param opc3 String
     */
    public void setOpc3(String opc3) {
        this.opc3 = opc3;
    }

    /**
     * Gets the option 4
     * @return String: Option 4 of question
     */
    public String getOpc4() {
        return opc4;
    }

    /**
     * Sets the option 4 of the question
     * @param opc4 String:4th option
     */
    public void setOpc4(String opc4) {
        this.opc4 = opc4;
    }

    /**
     * Gets the answer
     * @return String: Answer of question
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the answers
     * @param answer String: Answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     *
     * @param id_question int: id of the question
     * @param question String: The question being asked
     * @param opc1 String: Option #1 of possible answers
     * @param opc2 String: Option #2 of possible answers
     * @param opc3 String: Option #3 of possible answers
     * @param opc4 String: Option #4 of possible answers
     * @param answer String: The correct answer
     * @param section_id int: The sectiobns id
     */
    public SingleSelection(int id_question , String question, String opc1, String opc2, String opc3, String opc4, String answer, int section_id){
        this.id_question = id_question;
        this.question = question;
        this.opc1 = opc1;
        this.opc2 = opc2;
        this.opc3 = opc3;
        this.opc4 = opc4;
        this.id_section = section_id;
        this.answer = answer;
    }

    /**
     * Empty constructor when capturing data from the database
     */
    public SingleSelection(){}

    /**
     * Gets sections id
     * @return int: Sections id
     */
    public int getId_section() {
        return id_section;
    }

    /**
     * Sets the sections id
     * @param id_section Section id
     */
    public void setId_section(int id_section) {
        this.id_section = id_section;
    }


    /**
     *
     */
    @Override
    public void evaluateQuestion() {

    }

    /**
     * Concatenates the question used to demonstrate what the question has and its possible selection, if any.
     *
     * @return Concatenates question with its selections
     */
    @Override
    public Fragment showQuestion() {
        Fragment f = new SingleSelectionFragment();
        View v= f.getView();
        TextView q = (TextView) v.findViewById(R.id.txtQuestion);
        q.setText(getQuestion());
        CheckBox cb1 = (CheckBox)v.findViewById(R.id.checkboxOpc1);
        cb1.setText(getOpc1());
        CheckBox cb2 = (CheckBox)v.findViewById(R.id.checkboxOpc2);
        cb2.setText(getOpc2());
        CheckBox cb3 = (CheckBox)v.findViewById(R.id.checkboxOpc3);
        cb3.setText(getOpc3());
        CheckBox cb4 = (CheckBox)v.findViewById(R.id.checkboxOpc4);
        cb4.setText(getOpc4());
        return null;
    }

    /**
     *
     * @return Null
     */
    @Override
    public String toString() {
        return null;
    }
}
