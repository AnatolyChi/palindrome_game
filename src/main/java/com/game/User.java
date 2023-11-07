package com.game;

public class User {

  private final String id;
  private final String name;
  private int score;

  public User(String id, String name) {
    this.id = id;
    this.name = name;
    this.score = 0;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public void addScore(int score) {
    this.score += score;
  }
}
