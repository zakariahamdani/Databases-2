package de.hda.fbi.db2.stud.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Answer {

  @OneToOne()
  @JoinColumn(name = "question_id")
  private Question question;
  private int answearIndex;
  private Boolean correct;


  @Override
  public String toString() {
    return
        "question=" + question.getQuestion() + " \nindex right answear: " + question.getIndexRightAnswear() +
        "\n answearIndex=" + answearIndex +
        "\n correct=" + correct + "\n";
  }

  public Answer(Question question, int answearIndex, Boolean correct) {
    this.question = question;
    this.answearIndex = answearIndex;
    this.correct = correct;
  }

  public Question getQuestion() {
    return question;
  }

  public int getAnswearIndex() {
    return answearIndex;
  }

  public Boolean getCorrect() {
    return correct;
  }
}
