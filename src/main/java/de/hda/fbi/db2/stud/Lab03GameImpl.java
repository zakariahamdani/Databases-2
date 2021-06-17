package de.hda.fbi.db2.stud;

import de.hda.fbi.db2.api.Lab03Game;
import de.hda.fbi.db2.stud.entity.Answer;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Game;
import de.hda.fbi.db2.stud.entity.Player;
import de.hda.fbi.db2.stud.entity.Question;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.persistence.NoResultException;

public class Lab03GameImpl extends Lab03Game {

  @Override
  public Object getOrCreatePlayer(String playerName) {
    lab02EntityManager.getEntityManager().getTransaction().begin();
    String queryNAme = "Player.findByName";
    Player tmpPlayer;
    try {
      tmpPlayer = (Player) lab02EntityManager.getEntityManager().createNamedQuery(queryNAme).setParameter("name", playerName).getSingleResult();
    }
    catch (NoResultException e){
      tmpPlayer = new Player(playerName);
    }
    lab02EntityManager.getEntityManager().getTransaction().commit();
    return tmpPlayer;
  }

  @Override
  public Object interactiveGetOrCreatePlayer() {

    String playerName;
    System.out.println("Enter your name:");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    try {
      playerName = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return getOrCreatePlayer(playerName);
  }

  @Override
  public List<?> getQuestions(List<?> categories, int amountOfQuestionsForCategory) {
    lab02EntityManager.getEntityManager().getTransaction().begin();
    List <Question> questions = new ArrayList<Question>();;
    for (Object c : categories) {

      Random rand = new Random();

      Category category = (Category) c;

      List result =  lab02EntityManager.getEntityManager().createNamedQuery("Question.findByCategoryId").setParameter("cid", category.getcId()).getResultList();


      if (result.size() < amountOfQuestionsForCategory){

        for (Iterator i =result.iterator(); i.hasNext();){
          Object[] element = (Object[]) i.next();
          questions.add((Question) element[0]);
        }
      }else{
        for (int i = 0; i < amountOfQuestionsForCategory; i++) {
          int randomIndex = rand.nextInt(result.size());
          Object[] element = (Object[]) result.get(randomIndex);
          questions.add((Question)element[0]);
          result.remove(randomIndex);
        }
      }
    }
    lab02EntityManager.getEntityManager().getTransaction().commit();
    if (questions.size() > 0){
      return questions;
    }else{
      return null;
    }
  }

  @Override
  public List<?> interactiveGetQuestions() {
    int i = 2;
    List<Category> categories = new ArrayList<Category>();
    String id;
    String amountOfQuestions;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    do{
      System.out.println("Choose a category:");
      System.out.println("--------------------------------------");

      for (Category c :
          lab01Data.getCategories()) {
        System.out.println(c.getcId() + ": " + c.getName());
      }

      try {
        System.out.println("Enter the category ID: ");
        id = reader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
      Integer categoryId = Integer.parseInt(id);

      if (categoryId > 51 || categoryId < 0){
        System.out.println("input not valide!");
      }else{
        categories.add(lab01Data.getCategories().stream()
            .filter(categorie -> categoryId.equals(categorie.getcId())).parallel()
            .findAny()
            .orElse(null));
        i--;
      }
    }while (i > 0);

    try {
      System.out.println("Enter amount of questions per category: ");
      amountOfQuestions = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    Integer amountOfQuestionsForCategory = Integer.parseInt(amountOfQuestions);

    return getQuestions(categories, amountOfQuestionsForCategory);
  }

  @Override
  public Object createGame(Object player, List<?> questions) {
    return new Game(player, questions);
  }

  @Override
  public void playGame(Object game) {

  }

  @Override
  public void interactivePlayGame(Object game) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    String answear="";
    Game actGame = (Game) game;
    actGame.setStart();
    for (Question q : actGame.getAnswers().keySet()) {
      DataReader lab01 = (DataReader) lab01Data;
      System.out.println(q.toStringWithoutAnswear());

      try {
        System.out.println("Enter your answer: ");
        answear = reader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }

      Integer answear_int = Integer.parseInt(answear);

      if (answear_int.equals(q.getIndexRightAnswear())){
        actGame.getAnswers().put(q, true);
        System.out.println("correct answer");
      }else{
        actGame.getAnswers().put(q, false);
        System.out.println("false answer");
      }
    }
    actGame.setEnd();
  }

  @Override
  public void persistGame(Object game) {
    lab02EntityManager.getEntityManager().getTransaction().begin();
    Game game1 = (Game) game;
    lab02EntityManager.getEntityManager().persist(game1);
    lab02EntityManager.getEntityManager().persist(game1.getPlayer());
    lab02EntityManager.getEntityManager().getTransaction().commit();
  }
}
