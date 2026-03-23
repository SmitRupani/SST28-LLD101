public class Ballpoint implements IPenType {
    @Override
    public String write(String text, String color) {
        return "Ballpoint writing in " + color + ": " + text;
    }
}