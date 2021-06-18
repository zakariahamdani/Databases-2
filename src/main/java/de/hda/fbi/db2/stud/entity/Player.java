package de.hda.fbi.db2.stud.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@NamedQuery(name = "Player.findByName", query = "select p from Player p where p.name =:name")
public class Player {
  @Id
  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return name.equals(player.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  public Player(){};
  public Player(String name){
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
