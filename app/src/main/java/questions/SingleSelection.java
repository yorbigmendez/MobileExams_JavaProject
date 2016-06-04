package questions;

/**
 * A class that will contain a type of question that implements the Question interface
 */
public class SingleSelection implements Question {
    private int id_question;
    private String question;
    private int id_section;
    private String opc1;
    private String opc2;
    private String opc3;
    private String opc4;
    private String answer;

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpc1() {
        return opc1;
    }

    public void setOpc1(String opc1) {
        this.opc1 = opc1;
    }

    public String getOpc2() {
        return opc2;
    }

    public void setOpc2(String opc2) {
        this.opc2 = opc2;
    }

    public String getOpc3() {
        return opc3;
    }

    public void setOpc3(String opc3) {
        this.opc3 = opc3;
    }

    public String getOpc4() {
        return opc4;
    }

    public void setOpc4(String opc4) {
        this.opc4 = opc4;
    }

    public String getAnswer() {
        return answer;
    }

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

    public int getId_section() {
        return id_section;
    }

    public void setId_section(int id_section) {
        this.id_section = id_section;
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
