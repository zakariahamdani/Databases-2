package de.hda.fbi.db2.stud;
import de.hda.fbi.db2.api.Lab02EntityManager;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Question;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Lab02EntityManagerImpl extends Lab02EntityManager{

  private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("fbi-postgresPU");
  private static EntityManager em = emf.createEntityManager();
  @Override
  public void persistData() {
      em.getTransaction().begin();

    for (Category category : this.lab01Data.getCategories()){
      em.persist(category);
    }

      for(Question question : this.lab01Data.getQuestions()){
        em.persist(question);
      }

      em.getTransaction().commit();
      em.close();
  }

  @Override
  public EntityManager getEntityManager() {
    return em;
  }
}
