package questions;

import android.support.v4.app.Fragment;

/**
 * Interface for all the questions
 *
 * @author Yorbi Méndez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public interface Question {
    public String getQuestion();
    /**
     * Evaluates the question
     */
    public void evaluateQuestion();

    /**
     * Concatenates the question used to demonstrate what the question has and its possible selection, if any.
     * @return Concatenates question with its selections
     */
    public Fragment showQuestion();

}
