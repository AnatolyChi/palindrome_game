package com.task2;

/**
 * A class representing an element that can be numbered. Each item has a unique ID and number that can be set.
 */
public class Element implements IElement {

  private static long nextId = 1;
  private final long id;
  private int number;

  public Element() {
    this.id = nextId++;
    this.number = -1;
  }

  @Override
  public long getId() {
    return id;
  }

  @Override
  public int getNumber() {
    return number;
  }

  @Override
  public void setupNumber(int number) {
    this.number = number;
  }
}
