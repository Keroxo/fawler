import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    private static Google google;
    private static HtmlMatcher matcher;
    private static Http http;
    private static FrameworkNameReducer reducer;

    public static void main(String[] args) {
        init();
        String query = String.join(" ", args);
        System.out.println("Searching for Frameworks in this query:\n" + query);
        try {

            List<String> resultLinks = search(query);
            Map<String, Integer> frameworks = findFrameworks(resultLinks);
            prettyPrintResult(frameworks);

        } catch (IOException e) {
            System.out.println("An Error occurred :(");
            e.printStackTrace();
        }
    }

    private static void init() {
        google = new Google();
        matcher = new HtmlMatcher();
        http = new Http();
        reducer = new FrameworkNameReducer();
    }

    private static List<String> search(String query) throws IOException {
        String resultPage = google.search(query);
        return matcher.findSearchResults(resultPage);
    }

    private static Map<String, Integer> findFrameworks(List<String> resultLinks) throws IOException {
        Map<String, Integer> frameworks = new HashMap<>();
        for (String link : resultLinks) {
            findAndAddFrameworks(frameworks, link);
        }
        return frameworks;
    }

    private static void findAndAddFrameworks(Map<String, Integer> frameworks, String link) throws IOException {
        String pageContent = http.get(link);
        List<String> pageFrameworks = matcher.findFrameworks(pageContent);
        pageFrameworks.forEach(framework -> {
            framework = reducer.reduce(framework);
            if (frameworks.containsKey(framework)) {
                frameworks.put(framework, frameworks.get(framework) + 1);
            } else {
                frameworks.put(framework, 1);
            }
        });
    }

    private static void prettyPrintResult(Map<String, Integer> frameworks) {
        System.out.println("Amount | Framework");
        System.out.println("--------------------------------");

        while (!frameworks.isEmpty()) {
            String max = "";
            int amount = 0;
            for (Map.Entry<String, Integer> entry : frameworks.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (value > amount) {
                    max = key;
                    amount = value;
                }
            }
            System.out.println(amount + " - " + max);
            frameworks.remove(max);
        }
    }
}
