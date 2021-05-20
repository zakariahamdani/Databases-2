package de.hda.fbi.db2.stud.entity;

import java.util.List;

public class Question {
    private int id;
    private String question;
    private List<String> possibleAnswears;
    private int indexRightAnswear;
    private Category category;

    public Question(int id, String question, List<String> possibleAnswears, int indexRightAnswear){
        this.id = id;
        this.question = question;
        this.possibleAnswears = possibleAnswears;
        this.indexRightAnswear = indexRightAnswear;
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
