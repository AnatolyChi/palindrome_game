package com.task2;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Set;
import java.util.HashSet;

/**
 * Implementation of the IElementNumberAssigner interface. This class is designed as a singleton to ensure that only one
 * instance is used across the application, even when accessed from multiple threads.
 */
public class Task2Impl implements IElementNumberAssigner {

  /**
   * The singleton instance of the class, volatile to ensure thread-safe lazy initialization.
   */
  private static volatile IElementNumberAssigner INSTANCE;

  /**
   * Private constructor to prevent instantiation from outside the class.
   */
  private Task2Impl() {
  }

  /**
   * Method to get the singleton instance using double-checked locking.
   *
   * @return The singleton instance of Task2Impl.
   */
  public static IElementNumberAssigner getInstance() {
    if (INSTANCE == null) {
      synchronized (Task2Impl.class) {
        if (INSTANCE == null) {
          INSTANCE = new Task2Impl();
        }
      }
    }
    return INSTANCE;
  }

  /**
   * Assigns unique numbers to a list of elements in the order they appear in the list. It uses a HashSet to keep track
   * of the assigned numbers for lookup and ensures that the expensive setupNumber method is called as few times as
   * possible.
   *
   * @param elements The list of elements to assign numbers to.
   */
  @Override
  public void assignNumbers(final List<IElement> elements) {
    if (elements == null || elements.isEmpty()) {
      return;
    }

    AtomicInteger nextNumber = new AtomicInteger(1);
    Set<Integer> assignedNumbers = new HashSet<>();

    for (IElement element : elements) {
      if (!assignedNumbers.contains(element.getNumber())) {
        assignedNumbers.add(element.getNumber());
      } else {
        while (assignedNumbers.contains(nextNumber.get())) {
          nextNumber.incrementAndGet();
        }
        int uniqueNumber = nextNumber.getAndIncrement();
        element.setupNumber(uniqueNumber);
        assignedNumbers.add(uniqueNumber);
      }
    }
  }
}
