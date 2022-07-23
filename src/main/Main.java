package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

public class Main {


  public static void main(String[] args) {
    int corePoolSize = 2;
    int maximumPoolSize = 2;
    long keepAliveTime = 500;
    TimeUnit unit = TimeUnit.SECONDS;

    ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);

    RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
        maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    List<String> urls = new ArrayList<>();
    urls.add("https://jsonplaceholder.typicode.com/users");
    urls.add("https://jsonplaceholder.typicode.com/posts");
    urls.add("https://jsonplaceholder.typicode.com/albums");

    int index = 0;
    for (String url : urls) {
      threadPoolExecutor.execute(new Api(index++, url));
    }
    System.out.println("Do something when calling api!");
  }
}

