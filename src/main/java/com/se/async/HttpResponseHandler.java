package com.se.async;

import org.apache.http.HttpEntity;

import java.io.IOException;

public interface HttpResponseHandler {
    void handle(HttpEntity response) throws IOException;
}
