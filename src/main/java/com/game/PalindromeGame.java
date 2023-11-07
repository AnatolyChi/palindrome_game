package com.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PalindromeGame {

  private final Leaderboard leaderboard;
  private final Map<String, Set<String>> userEntries;

  public PalindromeGame() {
    this.leaderboard = new Leaderboard();
    this.userEntries = new HashMap<>();
  }

  public boolean submitWord(String userId, String userName, String word) {
    if (word == null || userId == null || userName == null) {
      return false;
    }

    word = word.toLowerCase().replaceAll("\\s+", "");
    if (PalindromeChecker.isPalindrome(word)) {
      if (!userEntries.containsKey(userId)) {
        userEntries.put(userId, new HashSet<>());
      }
      if (userEntries.get(userId).add(word)) {
        int score = word.length();
        leaderboard.addScore(userId, userName, score);
        return true;
      }
    }

    return false;
  }

  public List<User> getLeaderboard() {
    return leaderboard.getTopUsers();
  }

  public static void main(String[] args) {
    PalindromeGame game = new PalindromeGame();
    Scanner scanner = new Scanner(System.in);

    System.out.println("Welcome to the Palindrome Game!");
    String userId;
    String userName;
    String input;

    while (true) {
      System.out.println("\nEnter your user ID:");
      userId = scanner.nextLine();
      System.out.println("Enter your name:");
      userName = scanner.nextLine();
      System.out.println("Enter a word or phrase to check (or type 'exit' to quit):");
      input = scanner.nextLine();

      if ("exit".equalsIgnoreCase(input)) {
        System.out.println("Exiting game...");
        break;
      }

      boolean isPalindrome = game.submitWord(userId, userName, input);
      if (isPalindrome) {
        System.out.println("Congratulations! \"" + input + "\" is a palindrome! You earn points.");
      } else {
        System.out.println("Sorry, \"" + input + "\" is not a palindrome.");
      }

      System.out.println("\nCurrent Leaderboard:");
      List<User> leaders = game.getLeaderboard();
      for (User user : leaders) {
        System.out.println(user.getName() + ": " + user.getScore());
      }
    }

    scanner.close();
  }
}
