package questions;

/**
 * Created by Mendez Soto on 5/26/2016.
 */
public class TrueFalse implements Question {
    private int id_question;
    private String question;
    private String answer;
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
     * Used to insert the questions information
     */
    @Override
    public void insertInfo() {

    }

    /**
     * Used to eliminate the questions information
     */
    @Override
    public void eliminateInfo() {

    }

    /**
     * Evaluates the question
     */
    @Override
    public void evaluateQuestion() {

    }
}
