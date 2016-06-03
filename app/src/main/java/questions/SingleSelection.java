package questions;

/**
 * A class that will contain a type of question that implements the Question interface
 */
public class SingleSelection implements Question {
    private int id_question;
    private String question;
    private String opc1;
    private String opc2;
    private String opc3;
    private String opc4;
    private String answer;

    /**
     *
     * @param id_question int: id of the question
     * @param question String: The question being asked
     * @param opc1 String: Option #1 of possible answers
     * @param opc2 String: Option #2 of possible answers
     * @param opc3 String: Option #3 of possible answers
     * @param opc4 String: Option #4 of possible answers
     * @param answer String: The correct answer
     */
    public SingleSelection(int id_question , String question, String opc1, String opc2, String opc3, String opc4, String answer){
        this.id_question = id_question;
        this.question = question;
        this.opc1 = opc1;
        this.opc2 = opc2;
        this.opc3 = opc3;
        this.opc4 = opc4;
        this.answer = answer;
    }

    /**
     *
     */
    @Override
    public void insertInfo() {

    }

    /**
     *
     */
    @Override
    public void eliminateInfo() {

    }

    /**
     *
     */
    @Override
    public void evaluateQuestion() {

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return null;
    }
}
