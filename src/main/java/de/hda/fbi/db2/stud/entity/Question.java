package de.hda.fbi.db2.stud.entity;

import java.util.List;

public class Question {
    private int id;
    private String question;
    private List<String> possibleAnswears;
    private int indexRightAnswear;
    private Categorie categorie;

    public List<String> getPossibleAnswears() {
        return possibleAnswears;
    }

    public int getId() {
        return id;
    }

    public int getIndexRightAnswear() {
        return indexRightAnswear;
    }

    public String getRightAnswear(){
        return possibleAnswears.get(indexRightAnswear-1);
    }

    public String getQuestion() {
        return question;
    }

    public Question(int id, String question, List<String> possibleAnswears, int indexRightAnswear, Categorie categorie){
        this.id = id;
        this.question = question;
        this.possibleAnswears = possibleAnswears;
        this.indexRightAnswear = indexRightAnswear;
        this.categorie = categorie;
    }
}
