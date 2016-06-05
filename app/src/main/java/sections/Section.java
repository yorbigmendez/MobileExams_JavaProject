package sections;

import java.util.ArrayList;

import questions.Question;
/**
 * Class used to save exam Sections
 *
 * @author Yorbi Méndez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class Section {
    /**
     * Id of the section
     */
    private int id_section;
    /**
     * Name of the section
     */
    private String name;
    /**
     * Description of the section
     */
    private String description;
    /**
     * Question list
     */
    private ArrayList<Question> questionList;
    /**
     * Id the section belongs to
     */
    private int id_exam;


    /**
     * Constructor of class
     * @param id id of section
     * @param n name of section
     * @param d description of section
     * @param id_exam exam the section belongs to
      */
    public Section(int id,String n, String d, int id_exam){
        this.id_section = id;
        this.name = n;
        this.description = d;
        this.questionList = new ArrayList<>();
        this.id_exam = id_exam;
    }

    /**
     * Second constructor
     * @param n name
     * @param d description
     * @param id_exam exam it belongs to
     */
    public Section(String n, String d, int id_exam){
        this.name = n;
        this.description = d;
        this.questionList = new ArrayList<>();
        this.id_exam = id_exam;
    }


    public void setId_section(int id_section) {
        this.id_section = id_section;
    }

    public int getId_exam() {
        return id_exam;
    }

    public void setId_exam(int id_exam) {
        this.id_exam = id_exam;
    }

    public void addQuestion(Question q){
        this.questionList.add(q);
    }

    public int getId_section() {
        return id_section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }
}
