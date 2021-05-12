package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private String name;
    private List<Question> questions = new ArrayList<Question>();


    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Categorie(String name){
        this.name = name;
    }
}
