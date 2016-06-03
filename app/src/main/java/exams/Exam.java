package exams;

import java.util.ArrayList;

import sections.Section;

/**
 * Created by Mendez Soto on 5/26/2016.
 */
public class Exam {
    private int id;
    private String name;
    private double points;
    private String author;
    private float grade;
    private ArrayList<Section> sectionList;

    public Exam(int i,String n, int  p, String a){
        this.id = i;
        this.name = n;
        this.points = p;
        this.author = a;
        this.grade = (float)3.13;
        this.sectionList = new ArrayList<>();
    }

    //Constructor vacio
    public Exam(){
    }

    public void addSection(Section s){
        this.sectionList.add(s);
    }


    public void removeSection(Section s){
        for(int x = 0; x < sectionList.size() ; x++){
            if(sectionList.get(x).equals(s)){
                sectionList.remove(s);
            }
        }
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
