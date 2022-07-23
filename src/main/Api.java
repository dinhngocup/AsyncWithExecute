package main;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Api implements Callable {

  int id;
  String url;

  public Api(int id, String url) {
    this.id = id;
    this.url = url;
  }

  // Call API GET to get post data.
  public ConcurrentHashMap<Integer, String> getAllData() {
    CloseableHttpClient httpClient = HttpClients
        .createDefault();
    HttpGet request = new HttpGet(this.url);
    String result = "";
    try {
      CloseableHttpResponse res = httpClient.execute(request);
      // Read the response body.
      HttpEntity entity = res.getEntity();
      result = EntityUtils.toString(entity);
      ConcurrentHashMap<Integer, String> resultMap = new java.util.concurrent.ConcurrentHashMap<>();
      resultMap.put(this.id, result);
      System.out.println("Calling api: " + url + " successfully!");

      return resultMap;
    } catch (java.io.IOException e) {
      e.printStackTrace();
    } finally {
      request.releaseConnection();
    }
    return null;
  }

  @Override
  public ConcurrentHashMap<Integer, String> call() throws Exception {
    long threadId = Thread.currentThread().getId();
    System.out.println("Calling api: " + url + " - thread id = " + threadId);
    return getAllData();
  }
}


