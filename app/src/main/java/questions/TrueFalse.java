package questions;

import android.support.v4.app.Fragment;

/**
 * Class for True False Questions
 *
 * @author Yorbi Méndez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class TrueFalse implements Question {
    /**
     * id of the question
     */
    private int id_question;
    /**
     * Question
     */
    private String question;
    /**
     * Answer to question
     */
    private String answer;
    /**
     * id of section question belongs to
     */
    private int id_section;

    /**
     * Constructor of the class
     * @param id_question int: id of the question
     * @param question String: The question being asked
     * @param answer String: The correct answer
     */
    public TrueFalse(int id_question, String question,String answer,int id_section) {
        this.id_question = id_question;
        this.question = question;
        this.answer = answer;
        this.id_section = id_section;
    }

    public TrueFalse(){}

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public int getId_section() {
        return id_section;
    }

    public void setId_section(int id_section) {
        this.id_section = id_section;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
        return null;
    }
}
