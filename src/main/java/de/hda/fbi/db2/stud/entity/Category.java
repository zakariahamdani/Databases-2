package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;

    @Column(unique = true)
    private String name;

    public int getcId() {
        return cId;
    }

    @OneToMany(mappedBy = "category")
    private List<Question> questions = new ArrayList<Question>();

    public Category(){}

    public Category(String name){
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return cId == category.cId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId);
    }

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
}
