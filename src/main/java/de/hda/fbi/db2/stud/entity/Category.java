package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Question> questions = new ArrayList<Question>();


    public String getName() {
        return name;
    }

    public void addQuestion(Question question){
        this.getQuestions().add(question);
    }

    @Override
    public String toString() {
        String output = "Category: " + name + '\n';
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

    public Category(String name){
        this.name = name;
    }
}
