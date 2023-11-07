package com.task2;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    IElementNumberAssigner numberAssigner = Task2Impl.getInstance();

    List<IElement> elements = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      elements.add(new Element());
    }

    numberAssigner.assignNumbers(elements);

    for (IElement element : elements) {
      System.out.println("Element ID: " + element.getId() + ", Number: " + element.getNumber());
    }
  }
}
