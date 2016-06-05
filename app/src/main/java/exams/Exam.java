package exams;

import java.util.ArrayList;

import sections.Section;
/**
 * Exam class
 *
 *
 * @author Yorbi Mendez Soto
 * @version 06/04/2016
 * @since 1.0
 */
public class Exam {
    /**
     * Identification of exam
     */
    private int id;
    /**
     * Exam name
     */
    private String name;
    /**
     * Exam total points
     */
    private double points;
    /**
     * Exams author
     */
    private String author;
    /**
     * Exams grade
     */
    private float grade;
    /**
     * Arraylist of exams sections
     */
    private ArrayList<Section> sectionList;

    /**
     * Constructor of class
     * @param i id
     * @param n name
     * @param p points
     * @param a author
     */
    public Exam(int i,String n, int  p, String a){
        this.id = i;
        this.name = n;
        this.points = p;
        this.author = a;
        this.grade = (float)0.00;
        this.sectionList = new ArrayList<>();
    }

    //Constructor vacio
    public Exam(){
    }

    public void addSection(Section s){
        this.sectionList.add(s);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public int getId() {
        return id;
    }


    public ArrayList<Section> getSectionList() {
        return sectionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPoints() {
        return points;
    }


    public String getAuthor() {
        return author;
    }

}
