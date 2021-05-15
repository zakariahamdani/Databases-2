package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private String name;
    private List<Question> questions = new ArrayList<Question>();


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String output = "Categorie: " + name + '\n';
        for (Question question :
                questions) {
            output += question.toString();
        }
        output += "********************************************************\n";
        return output;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Categorie(String name){
        this.name = name;
    }
}
