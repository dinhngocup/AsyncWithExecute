package main;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import task.ITask;

public class ThreadPool {
  private final int CORE_POOL_SIZE = 2;
  private final int MAX_POOL_SIZE = 2;
  private final int ALIVE_TIME = 500;
  private final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
  private final int WORK_QUEUE_CAPACITY = 100;

  private final ArrayBlockingQueue<Runnable> workQueue;
  private final RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
  ThreadPoolExecutor threadPoolExecutor;

  public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime,
      TimeUnit unit, int workQueueCapacity) {
    this.workQueue = new ArrayBlockingQueue<>(workQueueCapacity);
    this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
        maximumPoolSize, keepAliveTime, unit, workQueue, handler);
  }

  public ThreadPool() {
    this.workQueue = new ArrayBlockingQueue<>(WORK_QUEUE_CAPACITY);
    this.threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
        MAX_POOL_SIZE, ALIVE_TIME, TIME_UNIT, workQueue, handler);
  }

  public void handleTasks(List<ITask> tasks) {
    for (ITask task: tasks) {
      threadPoolExecutor.execute(task);
    }
  }
}
