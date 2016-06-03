package sections;

import java.util.ArrayList;

import questions.Question;

/**
 * Created by Mendez Soto on 5/26/2016.
 */
public class Section {
    private int id_section;
    private String name;
    private String description;
    private ArrayList<Question> questionList;
    private int id_exam;


    public Section(int id,String n, String d, int id_exam){
        this.id_section = id;
        this.name = n;
        this.description = d;
        this.questionList = new ArrayList<>();
        this.id_exam = id_exam;
    }

    public void eliminateQuestion(Question q){
        for(int i = 0; i < questionList.size(); i++){
            if(questionList.get(i).equals(q)){
                questionList.remove(i);
            }
        }
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
