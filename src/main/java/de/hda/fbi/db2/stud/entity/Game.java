package de.hda.fbi.db2.stud.entity;

import java.sql.Timestamp;
import java.util.List;

public class Game {
  private String playerName;
  Timestamp start;
  Timestamp end;

  public String getPlayerName() {
    return playerName;
  }

  List<Question> questions;
  List<Category> categories;
}
