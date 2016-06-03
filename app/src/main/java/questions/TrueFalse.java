package questions;

/**
 * Created by Mendez Soto on 5/26/2016.
 */
public class TrueFalse implements Question {
    private int id_question;
    private String question;
    private String answer;

    /**
     * Constructor of the class
     * @param id_question int: id of the question
     * @param question String: The question being asked
     * @param answer String: The correct answer
     */
    public TrueFalse(int id_question, String question,String answer){
        this.id_question = id_question;
        this.question = question;
        this.answer = answer;
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
