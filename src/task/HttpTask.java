package task;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpTask extends ITask {
  String url;
  String requestBody;
  String header;
  HttpMethod method;

  public HttpTask(int id, String url, String requestBody, String header, HttpMethod method) {
    super(id);
    this.url = url;
    this.requestBody = requestBody;
    this.header = header;
    this.method = method;
  }
  public HttpTask(int id, String url, HttpMethod method) {
    super(id);
    this.url = url;
    this.method = method;
  }

  @Override
  public void handlingTask() {
    HttpRequestBase request = prepareHttpRequest();
    CloseableHttpClient httpClient = HttpClients
        .createDefault();
    String result = "";
    try {
      CloseableHttpResponse res = httpClient.execute(request);
      // Read the response body.
      HttpEntity entity = res.getEntity();
      result = EntityUtils.toString(entity);
//      System.out.println("Result: " + result);
      resultMap.put(this.id, result);
    } catch (java.io.IOException e) {
      e.printStackTrace();
    } finally {
      request.releaseConnection();
    }
  }

  public HttpRequestBase prepareHttpRequest() {
    switch (method) {
      case GET:
        return prepareGetHttpRequest();
      default:
        return null;
    }
  }

  public HttpRequestBase prepareGetHttpRequest() {
    return new HttpGet(this.url);
  }
}
