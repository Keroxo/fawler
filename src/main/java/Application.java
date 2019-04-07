import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        Crawler crawler = new Crawler();
        try {
            System.out.println(crawler.fetchSearchResults(String.join(" ", args)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
