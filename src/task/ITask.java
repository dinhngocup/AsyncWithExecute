package task;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ITask implements Callable {
  int id;
  HashMap<Integer, String> resultMap;

  public ITask(int id) {
    this.id = id;
    this.resultMap = new java.util.HashMap<>();
  }

  public abstract ConcurrentHashMap<Integer, String> handlingTask();

  @Override
  public ConcurrentHashMap<Integer, String> call() throws Exception {
    long threadId = Thread.currentThread().getId();
    System.out.println("Calling api with thread id = " + threadId);
    return handlingTask();
  }
}
