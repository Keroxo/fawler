import java.io.IOException;
import java.net.URLEncoder;

class Google {

    private static final String GOOGLE_BASE_URL = "https://www.google.de/search?q=";

    private Http http;

    public Google() {
        http = new Http();
    }

    String search(String query) throws IOException {
        return http.get(GOOGLE_BASE_URL + URLEncoder.encode(query, "UTF-8"));
    }
}
