package com.se.async;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.concurrent.*;

public class HttpAsyncClient {
    private final Log log = LogFactory.getLog(this.getClass());

    private ExecutorService executors = Executors.newFixedThreadPool(4);

    /**
     * How to use:
     * //        new HttpAsyncClient().get("").onSuccess(new HttpResponseHandler() {
     * //            @Override
     * //            public void handle() {
     * //
     * //            }
     * //        }).onError(new HttpResponseHandler() {
     * //            @Override
     * //            public void handle() {
     * //
     * //            }
     * //        }).call();
     *
     * @param url
     * @return
     */
    public HttpResponseFuture get(String url) {
        HttpGet getRequest = new HttpGet(url);
        Future<HttpEntity> future = executors.submit(new Callable<HttpEntity>() {
            @Override
            public HttpEntity call() throws IOException, InterruptedException {
                return doExecute(getRequest);
            }
        });

        return new HttpResponseFuture(future);
    }

    private HttpEntity doExecute(HttpRequestBase baseRequest) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse res = httpClient.execute(baseRequest);
        baseRequest.releaseConnection();

        return res.getEntity();
    }
}

