package goit.http.client;


/*запитати у юзера код статусу (наприклад, Enter HTTP status code)
юзер вводить в консоль код статусу (наприклад, 200)
програма перевіряє, чи є картинка для цього статусу на сайті https://http.cat, і якщо є - то скачує цю картинку.
Якщо ж картинки немає - виводить в консоль фразу There is not image for HTTP status <CODE>
(замість <CODE> підставляється код статусу, що ввів користувач)
якщо користувач вводить некоректне число (наприклад, test) - програма має вивести фразу Please enter valid number
Використай клас HttpStatusImageDownloader з попереднього завдання.*/

import java.io.IOException;
import java.util.Scanner;

public class HttpImageStatusCli {
    void askStatus(){
        Scanner input = new Scanner(System.in);
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
        int intCode = 0;

        System.out.println("Please enter the status of the image(example: 200): ");
        String code = input.nextLine();
        if(code.isBlank()){
            throw new IllegalArgumentException("Please enter the status of the image(example: 200): ");
        }

        try {
            intCode = Integer.parseInt(code);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Please enter valid number" + e.getMessage());
        }

        try {
            downloader.downloadStatusImage(intCode);
        } catch (IOException e) {
            throw new RuntimeException("cannot download status image" + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException("cannot download status image" + e.getMessage());
        }

    }
}
