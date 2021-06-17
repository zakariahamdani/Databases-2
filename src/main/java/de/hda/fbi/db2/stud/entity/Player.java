package de.hda.fbi.db2.stud.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "Player.findByName", query = "select p from Player p where p.name =:name")
public class Player {
  @Id
  private String name;

  public Player(){};
  public Player(String name){
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
