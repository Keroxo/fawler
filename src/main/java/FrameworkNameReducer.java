public class FrameworkNameReducer {

    public String reduce(String input) {
        input = input.replaceAll("\\.min", "");
        input = input.replaceAll("\\.js", "");
        input = input.replaceAll("\\.en", "");
        input = input.replaceAll("\\.base64", "");
        input = input.replaceAll(".+/", "");
        return input;
    }
}
