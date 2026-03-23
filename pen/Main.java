public class Main {
    public static void main(String[] args) {

        Pen pen = PenFactory.createPen("gel", "click", "cartridge", "blue");

        System.out.println(pen.write("Hello")); // closed

        pen.open();
        System.out.println(pen.write("Hello World"));

        pen.refill("black");
        System.out.println(pen.write("After refill"));

        pen.close();
        System.out.println(pen.write("Try again"));
    }
}