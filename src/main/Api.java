package main;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.HashMap;

public class Api implements Runnable {
  int id;
  String url;
  HashMap<Integer, String> resultMap;

  public Api(int id, String url) {
    this.id = id;
    this.url = url;
    this.resultMap = new java.util.HashMap<>();
  }

  // Call API GET to get post data.
  public void getAllData() {
    CloseableHttpClient httpClient = HttpClients
        .createDefault();
    HttpGet request = new HttpGet(this.url);
    String result = "";
    try {
      CloseableHttpResponse res = httpClient.execute(request);
      // Read the response body.
      HttpEntity entity = res.getEntity();
      result = EntityUtils.toString(entity);
      resultMap.put(this.id, result);
    } catch (java.io.IOException e) {
      e.printStackTrace();
    } finally {
      request.releaseConnection();
    }
  }

  @Override
  public void run() {
    System.out.println("Calling api: " + url + " - thread id = " + Thread.currentThread().getId());
    getAllData();
    System.out.println("Successfully.");
  }

}

