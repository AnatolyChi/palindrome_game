package com.game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Leaderboard {

  private final PriorityQueue<User> topUsers;
  private final Map<String, User> userScores;

  public Leaderboard() {
    this.topUsers = new PriorityQueue<>(5, Comparator.comparingInt(User::getScore).reversed());
    this.userScores = new HashMap<>();
  }

  public void addScore(String userId, String userName, int score) {
    User user = userScores.getOrDefault(userId, new User(userId, userName));
    user.addScore(score);
    userScores.put(userId, user);
    topUsers.add(user);
    while (topUsers.size() > 5) {
      topUsers.poll();
    }
  }

  public List<User> getTopUsers() {
    return new ArrayList<>(topUsers);
  }
}
