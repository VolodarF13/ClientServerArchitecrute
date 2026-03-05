package goit.http.client;

public class Test {
    public static void main(String[] args) {
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
        HttpStatusChecker checker = new HttpStatusChecker();
        HttpImageStatusCli cli = new HttpImageStatusCli();
        try {
            System.out.println("Status 200: " + checker.getStatusImage(200)); // Має вивести URL
            downloader.downloadStatusImage(403);
            System.out.println("Downloaded successfully");
            // System.out.println("Status 10000: " + checker.getStatusImage(10000)); // Має викинути Exception

            cli.askStatus();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
