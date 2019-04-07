import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class Crawler {

    private static final String GOOGLE_BASE_URL = "https://www.google.de/search?q=";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";


    String fetchGoogleSearchResult(String query) throws IOException {
        HttpURLConnection con = sendRequest(query);
        if (con.getResponseCode() == 200) {
            StringBuffer content = getResponseContent(con);
            return content.toString();
        }
        throw new IOException("Unexpected Error. Status Code: " +con.getResponseCode());
    }

    private HttpURLConnection sendRequest(String query) throws IOException {
        URL url = new URL(GOOGLE_BASE_URL + URLEncoder.encode(query, "UTF-8"));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestMethod("GET");
        return con;
    }

    private StringBuffer getResponseContent(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return content;
    }
}
