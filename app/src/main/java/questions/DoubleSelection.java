package questions;

/**
 * Created by Mendez Soto on 5/26/2016.
 */
public class DoubleSelection implements Question {
    private int id_question;
    private String question;
    private String opc1;
    private String opc2;
    private String opc3;
    private String opc4;
    private String answer1;
    private String answer2;

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
    public DoubleSelection(int id_question, String question,String opc1, String opc2, String opc3, String opc4, String answer1, String answer2){
        this.question = question;
        this.opc1 = opc1;
        this.opc2 = opc2;
        this.opc3 = opc3;
        this.opc4 = opc4;
        this.answer1 = answer1;
        this.answer2 = answer2;
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
