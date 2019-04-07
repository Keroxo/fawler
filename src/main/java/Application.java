import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        Crawler crawler = new Crawler();
        ResultParser parser = new ResultParser();

        try {
            String resultPage = crawler.fetchGoogleSearchResult(String.join(" ", args));
            List<String> resultLinks = parser.findResultUrls(resultPage);

            System.out.println(resultLinks);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
