package main.java.task;

import java.util.HashMap;

public abstract class ITask implements Runnable{
  int id;
  HashMap<Integer, String> resultMap;

  public ITask(int id) {
    this.id = id;
    this.resultMap = new java.util.HashMap<>();
  }

  public abstract void handlingTask();

  @Override
  public void run() {
    System.out.println("Handling main.java.task with thread id = " + Thread.currentThread().getId());
    handlingTask();
    System.out.println("Successfully.");
  }
}
