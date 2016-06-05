package questions;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import cr.ac.itcr.examproject.*;

/**
 * DoubleSelection class for questions
 *
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class DoubleSelection implements Question {
    /**
     * Id of the question
     */
    private int id_question;
    /**
     * Question
     */
    private String question;
    /**
     * Possible answer #1
     */
    private String opc1;
    /**
     * Possible answer #2
     */
    private String opc2;
    /**
     * Possible answer #3
     */
    private String opc3;
    /**
     * Possible answer #4
     */
    private String opc4;
    /**
     * Correct Answer #1
     */
    private String answer1;
    /**
     * Correct Answer #2
     */
    private String answer2;
    /**
     * Section of question
     */
    private int id_section;

    /**
     *
     * @param id_question int: id of the question
     * @param question String: The question being asked
     * @param opc1 String: Option #1 of possible answers
     * @param opc2 String: Option #2 of possible answers
     * @param opc3 String: Option #3 of possible answers
     * @param opc4 String: Option #4 of possible answers
     * @param answer1 String: One of the correct answers
     * @param answer2 String: One of the correct answers
     */
    public DoubleSelection(int id_question, String question,String opc1, String opc2, String opc3, String opc4, String answer1, String answer2, int id_section){
        this.question = question;
        this.opc1 = opc1;
        this.opc2 = opc2;
        this.opc3 = opc3;
        this.opc4 = opc4;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.id_section = id_section;
    }

    public DoubleSelection(){}

    public int getId_section() {
        return id_section;
    }

    public void setId_section(int id_section) {
        this.id_section = id_section;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getOpc4() {
        return opc4;
    }

    public void setOpc4(String opc4) {
        this.opc4 = opc4;
    }

    public String getOpc3() {
        return opc3;
    }

    public void setOpc3(String opc3) {
        this.opc3 = opc3;
    }

    public String getOpc2() {
        return opc2;
    }

    public void setOpc2(String opc2) {
        this.opc2 = opc2;
    }

    public String getOpc1() {
        return opc1;
    }

    public void setOpc1(String opc1) {
        this.opc1 = opc1;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }



    /**
     * Evaluates the question
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
        Fragment f = new DoubleSelectionFragment();
        View v = f.getView();
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
        return f;
    }
}
