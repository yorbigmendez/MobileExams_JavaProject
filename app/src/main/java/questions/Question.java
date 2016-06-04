package questions;

/**
 * Interface that is used to define the schema that the questions will have on the project.
 */
public interface Question {
    public String getQuestion();
    /**
     * Used to insert the questions information
     */
    public void insertInfo();

    /**
     * Used to eliminate the questions information
     */
    public void eliminateInfo();

    /**
     * Evaluates the question
     */
    public void evaluateQuestion();

    /**
     * Concatenates the question used to demonstrate what the question has and its possible selection, if any.
     * @return Concatenates question with its selections
     */
    public String toString();

}
