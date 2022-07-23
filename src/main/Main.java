package main;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

public class Main {


  public static void main(String[] args)
      throws java.util.concurrent.ExecutionException, InterruptedException {
    // initial num of thread
    int corePoolSize = 2;
    // max num of thread
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
    Set<Callable<Api>> callables = new HashSet<>();

    int index = 0;
    for (String url : urls) {
      callables.add(new Api(index++, url));
    }
    List<Future<Api>> futures = threadPoolExecutor.invokeAll(callables);
    System.out.println("Do something when calling api!");

    for(Future<Api> future : futures){
      System.out.println("future.get = " + future.get());
    }
    threadPoolExecutor.shutdown();
  }
}


