import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HtmlMatcherTest {

    private HtmlMatcher uut;

    @Before
    public void init() {
        uut = new HtmlMatcher();
    }

    @Test
    public void test() {
        // Given
        String document = loadResourceContent();
        List<String> expected = Arrays.asList("http://sqlfiddle.com/", "https://context.reverso.net/%C3%BCbersetzung/englisch-deutsch/TEST+QUERY", "https://sqltest.net/", "https://www.testdome.com/tests/sql-online-test/12", "https://stackoverflow.com/questions/754527/best-way-to-test-sql-queries", "https://stackoverflow.com/questions/2433633/is-there-a-command-to-test-an-sql-query-without-executing-it-mysql-or-ansi-sq", "https://docs.oracle.com/cd/E13190_01/liquiddata/docs10/querybld/run.html", "https://package.elm-lang.org/packages/eeue56/elm-html-test/latest/Test-Html-Query", "https://www.ibm.com/support/knowledgecenter/en/SSMPHH_10.0.0/com.ibm.guardium.doc/assess/t_define_qbased.html");

        // When
        List<String> actual = uut.findSearchResults(document);

        // Then
        Assert.assertEquals(expected, actual);
    }

    private String loadResourceContent() {
        URL document = this.getClass().getResource("/ExampleGoogleResult.html");
        File file = new File(document.getFile());

        try (Scanner scanner = new Scanner(file)) {
            StringBuilder result = new StringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
