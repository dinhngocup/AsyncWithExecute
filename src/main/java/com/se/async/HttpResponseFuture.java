package com.se.async;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpResponseFuture {
    private final Log log = LogFactory.getLog(this.getClass());

    private Future<HttpEntity> future;

    private ExecutorService responseExecutor = Executors.newFixedThreadPool(4);
    private HttpResponseHandler successHandler;
    private HttpResponseHandler errorHandler;

    public HttpResponseFuture(Future<HttpEntity> future){
        try {
            this.future = future;
        } catch (Exception exception) {
            log.error("error when getting value from future", exception);
        }
    }


    public HttpResponseFuture onSuccess(HttpResponseHandler handler) throws IOException {
        this.successHandler = handler;
        return this;
    }

    public HttpResponseFuture onError(HttpResponseHandler handler) throws IOException {
        this.errorHandler = handler;
        return this;
    }

    public void call() {
        responseExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpEntity response = future.get();
                    successHandler.handle(response);
                } catch (InterruptedException | ExecutionException | IOException e) {
                    log.error("has error when waiting response", e);
                }
            }
        });
    }
}
