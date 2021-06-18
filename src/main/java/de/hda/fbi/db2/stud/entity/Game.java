package de.hda.fbi.db2.stud.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

  @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
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

  public void setStartedAt(long p_startedAt) {
    this.startedAt = new Timestamp(p_startedAt);
  }

  public void setEndedAt(long p_endedAt) {
    this.endedAt = new Timestamp(p_endedAt);
  }

  public Game() {
  }

  public void setStart() { this.startedAt = new Timestamp(System.currentTimeMillis()); }

  public void setEnd() { this.endedAt = new Timestamp(System.currentTimeMillis()); }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Game game = (Game) o;
    return gId == game.gId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(gId);
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
