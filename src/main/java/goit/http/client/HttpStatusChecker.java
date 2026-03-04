package goit.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpStatusChecker {
    private HttpClient client = HttpClient.newHttpClient();

    public String getStatusImage(int code) throws IOException, InterruptedException {
        final String URL = "https://http.cat/" + code + ".jpg";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .method("HEAD", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());

        if (response.statusCode() == 200) {
            return URL;
        } else if (response.statusCode() == 404) {
            throw new IllegalArgumentException("404 Not Found");
        } else {
            throw new RuntimeException("Unexpected status code " + response.statusCode());
        }
    }
}
