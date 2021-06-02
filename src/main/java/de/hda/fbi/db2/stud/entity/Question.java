package de.hda.fbi.db2.stud.entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;

@Entity
public class Question {
    @Id
    private int id;
    private String question;

    @ElementCollection
    @OrderColumn(name = "sequencee")
    private List<String> possibleAnswears;

    private int indexRightAnswear;

    @ManyToOne
    private Category category;

    public Question(){

    }

    public Question(int id, String question, List<String> possibleAnswears, int indexRightAnswear){
        this.id = id;
        this.question = question;
        this.possibleAnswears = possibleAnswears;
        this.indexRightAnswear = indexRightAnswear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        return getId() == question.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

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

    public Category getCategorie() {
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    @Override
    public String toString() {
        String output;
        output ="-----------------------------------\n";
        output +="QUESTION: '" + id + "' " + question + "\n" + "CHOISES: \n";
        for (String pAnswear:
             possibleAnswears) {
            output += "# " +pAnswear + "\n";
        }
        output += "=> RIGHT ANSWEAR: '" + indexRightAnswear + "' " + this.getRightAnswear() +"\n";
        return output;
    }
}
