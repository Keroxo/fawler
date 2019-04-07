import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultParser {

    private static final String RESULT_LINK_REGEX = "<div class=\"r\"><a href=\"(.+?)\"";

    List<String> findResultUrls(String document) {
        Pattern pattern = Pattern.compile(RESULT_LINK_REGEX);
        Matcher matcher = pattern.matcher(document);

        List<String> resultLinks = new ArrayList<>();
        while (matcher.find()) {
            resultLinks.add(matcher.group(1));
        }
        return resultLinks;
    }
}
