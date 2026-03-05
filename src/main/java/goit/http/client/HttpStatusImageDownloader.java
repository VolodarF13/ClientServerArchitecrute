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
    private final HttpStatusChecker httpStatusChecker = new HttpStatusChecker();

    public void downloadStatusImage(int code) {

        try {
            Path filePath = Path.of("image", code + ".jpg");

            isDirectoryExists(filePath);

            String statusImage = httpStatusChecker.getStatusImage(code);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(statusImage))
                    .GET()
                    .build();

            HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to download status image", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to download status image", e);
        }

    }

    private static void isDirectoryExists(Path filePath) {
        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating directory: " + filePath.toString(), e);
        }
    }
}
