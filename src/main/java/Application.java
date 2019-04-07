import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        Google google = new Google();
        ResultParser parser = new ResultParser();
        String query = String.join(" ", args);

        try {
            String resultPage = google.search(query);
            List<String> resultLinks = parser.findResultUrls(resultPage);

            System.out.println(resultLinks);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
