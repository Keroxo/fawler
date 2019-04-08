import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlMatcher {

    private static final String RESULT_LINK_REGEX = "<div class=\"r\"><a href=\"(.+?)\"";
    private static final String SCRIPT_TAG_REGEX = "['|\"]([0-9|A-Z|a-z|_|\\-|\\.|\\/]+?\\.js)['|\"]";

    List<String> findSearchResults(String document) {
        return match(document, RESULT_LINK_REGEX);
    }

    List<String> findFrameworks(String document) {
        return match(document, SCRIPT_TAG_REGEX);
    }

    private List<String> match(String document, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(document);

        List<String> resultLinks = new ArrayList<>();
        while (matcher.find()) {
            resultLinks.add(matcher.group(1));
        }
        return resultLinks;
    }
}
