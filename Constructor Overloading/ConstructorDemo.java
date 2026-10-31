class Rectangle {
    double length;
    double width;

    // Constructor 1: no arguments
    Rectangle() {
        length = 1;
        width = 1;
        System.out.println("Called Rectangle()");
    }

    // Constructor 2: one argument (a square)
    Rectangle(double side) {
        length = side;
        width = side;
        System.out.println("Called Rectangle(double side)");
    }

    // Constructor 3: two arguments
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
        System.out.println("Called Rectangle(double length, double width)");
    }

    double area() {
        return length * width;
    }

    void show() {
        System.out.println("Rectangle " + length + " x " + width + ", area = " + area());
    }
}

public class ConstructorDemo {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();
        r1.show();

        Rectangle r2 = new Rectangle(5);
        r2.show();

        Rectangle r3 = new Rectangle(4, 6);
        r3.show();
    }
}
