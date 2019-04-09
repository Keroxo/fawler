import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FrameworkNameReducerTest {


    private FrameworkNameReducer uut;

    @Before
    public void init() {
        uut = new FrameworkNameReducer();
    }

    @Test
    public void parseStringWithPrefixCorrectly() {
        // Given
        String input = "/discussions/assets/libs/css3-mediaqueries.js";
        String expected = "css3-mediaqueries";

        // When
        String actual = uut.reduce(input);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseStringWithEnCorrectly() {
        // Given
        String input = "js/explore-qlist.en.js";
        String expected = "explore-qlist";

        // When
        String actual = uut.reduce(input);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseStringWithMinCorrectly() {
        // Given
        String input = "js/bootstrap.min.js";
        String expected = "bootstrap";

        // When
        String actual = uut.reduce(input);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseStringWithMultipleEnCorrectly() {
        // Given
        String input = "js/begin-edit-event.en.js";
        String expected = "begin-edit-event";

        // When
        String actual = uut.reduce(input);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseStringWithBase64Correctly() {
        // Given
        String input = "jquery.base64.min.js";
        String expected = "jquery";

        // When
        String actual = uut.reduce(input);

        // Then
        Assert.assertEquals(expected, actual);
    }
}
