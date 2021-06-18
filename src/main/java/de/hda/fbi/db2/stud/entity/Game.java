package de.hda.fbi.db2.stud.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;

@Entity
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name= "gid")
  private int gId;

  @ManyToOne(fetch= FetchType.EAGER, cascade= CascadeType.ALL)
  private Player player;

  @ElementCollection
  @CollectionTable(name = "answers", joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "gid")})
  @MapKeyColumn(name = "question_id")
  @Column(name = "correct")
  Map<Question, Boolean> answers = new HashMap<Question, Boolean>();

  public Map<Question, Boolean> getAnswers() {
    return answers;
  }

  public void setAnswers(Map<Question, Boolean> answers) {
    this.answers = answers;
  }

  @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Timestamp startedAt;


  @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Timestamp endedAt;

  public Game() {
  }

  public void setStart() {
    this.startedAt = new Timestamp(System.currentTimeMillis());
  }

  public void setEnd() {
    this.endedAt = new Timestamp(System.currentTimeMillis());
  }


  public Game(Object player, List<?> questions) {
    List <Question> tmp = (List <Question>) questions;
    this.player = (Player) player;
    for (Question q : tmp){
      answers.put(q, false);
    }
  }

  public Player getPlayer() {
    return player;
  }

}
