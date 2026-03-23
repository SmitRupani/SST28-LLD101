public class Ink implements IPenType {
    @Override
    public String write(String text, String color) {
        return "Ink pen writing fluidly in " + color + ": " + text;
    }
}