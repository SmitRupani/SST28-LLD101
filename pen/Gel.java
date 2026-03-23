public class Gel implements IPenType {
    @Override
    public String write(String text, String color) {
        return "Gel pen writing smoothly in " + color + ": " + text;
    }
}