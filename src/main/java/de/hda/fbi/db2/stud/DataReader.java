package de.hda.fbi.db2.stud;

import de.hda.fbi.db2.api.Lab01Data;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Question;

import java.util.*;

public class DataReader extends Lab01Data {

    private List<Question> questions = new ArrayList<Question>();
    private Map<String, Category> categories = new TreeMap<String, Category>();


    @Override
    public List<Question> getQuestions() {
        return this.questions;
    }

    @Override
    public List<Category> getCategories() {
        return new ArrayList<>(categories.values());
    }

    @Override
    public void loadCsvFile(List<String[]> additionalCsvLines) {

        additionalCsvLines.remove(0);

        int id, rightAnswear;
        String question, category;

        for (String[] line : additionalCsvLines) {

            List<String> answears = new ArrayList<String>();

            id = Integer.parseInt(line[0]);

            question = line[1];

            answears.add(line[2]);
            answears.add(line[3]);
            answears.add(line[4]);
            answears.add(line[5]);

            rightAnswear = Integer.parseInt(line[6]);

            category = line[7];

            Category categoryExists = this.categories.get(category);
            if (categoryExists == null) {

                Category tmpCategory = new Category(category);
                Question tmpQuestion = new Question(id, question, answears, rightAnswear);

                tmpCategory.addQuestion(tmpQuestion);
                tmpQuestion.setCategory(tmpCategory);

                questions.add(tmpQuestion);
                categories.put(category, tmpCategory);
            }
            else
            {
                Question tmpQuestion = new Question(id, question, answears, rightAnswear);

                categoryExists.addQuestion(tmpQuestion);
                tmpQuestion.setCategory(categoryExists);

                questions.add(tmpQuestion);
            }
        }

        for (Category c : categories.values()) {
            System.out.println(c);
        }

        System.out.println("number of categories: " + categories.size());
        System.out.println("number of Questions: " + questions.size());
    }
}
