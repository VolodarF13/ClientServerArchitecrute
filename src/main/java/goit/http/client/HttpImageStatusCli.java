package goit.http.client;

import java.util.Scanner;

public class HttpImageStatusCli {
    void askStatus() {
        Scanner input = new Scanner(System.in);
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
        int intCode = 0;
        while (true) {
            System.out.println("Please enter the status of the image(example: 200) or exits enter (exit): ");
            String code = input.nextLine();
            if (code.equalsIgnoreCase("exit")) {
                break;
            }
            if (code.isBlank()) {
                System.out.println("Please enter the status of the image(example: 200): ");
                continue;
            }

            try {
                intCode = Integer.parseInt(code);
                downloader.downloadStatusImage(intCode);
                System.out.println("Image downloaded is successfully.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid number");
            } catch (IllegalArgumentException e) {
                System.out.println("There is not image for HTTP status " + code);
            } catch (Exception e) {
                System.out.println("There was an error downloading status image " + code);
            }
        }
    }
}
