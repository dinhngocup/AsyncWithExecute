package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import task.HttpMethod;
import task.HttpTask;
import task.ITask;

public class Main {


  public static void main(String[] args)
      throws java.util.concurrent.ExecutionException, InterruptedException {
    ThreadPool threadPool = new ThreadPool();

    List<String> urls = new ArrayList<>();
    urls.add("https://jsonplaceholder.typicode.com/users");
    urls.add("https://jsonplaceholder.typicode.com/posts");
    urls.add("https://jsonplaceholder.typicode.com/albums");
    int index = 0;
    List<ITask> httpTasks = new ArrayList<>();
    for (String url : urls) {
      httpTasks.add(new HttpTask(index++, url, HttpMethod.GET));
    }

    List<Future<ITask>> futures = threadPool.handleTasks(httpTasks);

    System.out.println("Do something when calling api!");

    for (Future<ITask> future : futures) {
      System.out.println("future.get = " + future.get());
    }
  }
}


