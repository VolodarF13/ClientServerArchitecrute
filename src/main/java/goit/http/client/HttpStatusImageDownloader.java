package goit.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpStatusImageDownloader {

    private final HttpClient client = HttpClient.newHttpClient();

    public void downloadStatusImage(int code) throws IOException, InterruptedException {

        Path filePath = Path.of("image",code + ".jpg");

        isDirectoryExists(filePath);

        HttpStatusChecker httpStatusChecker = new HttpStatusChecker();
        String statusImage = httpStatusChecker.getStatusImage(code);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(statusImage))
                .GET()
                .build();

        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(filePath));

    }

    private static void isDirectoryExists(Path filePath) throws IOException {
        if(!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
    }
}
