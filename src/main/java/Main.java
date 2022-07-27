import com.se.async.HttpAsyncClient;
import com.se.async.HttpResponseHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Main {
    public static Log log = LogFactory.getLog("Main_Class");

    public static void main(String[] args) throws IOException, InterruptedException {
        new HttpAsyncClient().get("https://jsonplaceholder.typicode.com/users")
        .onSuccess(new HttpResponseHandler() {
            @Override
            public void handle(HttpEntity response) throws IOException {
                System.out.println("get response successfully from {1}" + response.toString());
            }
        })
        .onError(new HttpResponseHandler() {
            @Override
            public void handle(HttpEntity response) throws IOException {

            }
        })
        .call();

        new HttpAsyncClient().get("https://jsonplaceholder.typicode.com/users")
        .onSuccess(new HttpResponseHandler() {
            @Override
            public void handle(HttpEntity response) throws IOException {
                System.out.println("get response successfully from {2}" + response.toString());
            }
        })
        .onError(new HttpResponseHandler() {
            @Override
            public void handle(HttpEntity response) throws IOException {

            }
        }).call();

        System.out.println("Do something when calling api!");
        Thread.sleep(1000000);
    }
}
