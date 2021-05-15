package de.hda.fbi.db2.stud;

import de.hda.fbi.db2.api.Lab01Data;
import de.hda.fbi.db2.stud.entity.Categorie;
import de.hda.fbi.db2.stud.entity.Question;

import java.security.cert.CertificateRevokedException;
import java.util.*;
import java.util.stream.Collectors;

public class DataReader extends Lab01Data {

    private List<Question> questions = new ArrayList<Question>();
    private List<Categorie> categories = new ArrayList<Categorie>();


    public Categorie getCategorie(String name){
        return categories.stream()
                .filter(categorie -> name.equals(categorie.getName())).parallel()
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Question> getQuestions() {
        return this.questions;
    }

    @Override
    public List<Categorie> getCategories() {
        return this.categories;
    }

    @Override
    public void loadCsvFile(List<String[]> additionalCsvLines) {

        additionalCsvLines.remove(0);

        int id, rightAnswear;
        String question, categorie;


        for (String[] line : additionalCsvLines) {

            List<String> answears = new ArrayList<String>();

            id = Integer.parseInt(line[0]);

            question = line[1];

            answears.add(line[2]);
            answears.add(line[3]);
            answears.add(line[4]);
            answears.add(line[5]);

            rightAnswear = Integer.parseInt(line[6]);

            categorie = line[7];

            Categorie categorieExists = this.getCategorie(categorie);
            if (categorieExists == null) {
                Categorie tmpCategorie = new Categorie(categorie);
                Question tmpQuestion = new Question(id, question, answears, rightAnswear,tmpCategorie);
                tmpCategorie.getQuestions().add(tmpQuestion);
                questions.add(tmpQuestion);
                categories.add(tmpCategorie);
            }
            else
            {
                Question tmpQuestion = new Question(id, question, answears, rightAnswear, categorieExists);
                categorieExists.getQuestions().add(tmpQuestion);
                questions.add(tmpQuestion);
            }
        }

        for (Categorie c : categories) {
            System.out.println(c.toString());
        }

        System.out.println("number of categories: " + categories.size());
        System.out.println("number of Questions: " + questions.size());
    }
}
