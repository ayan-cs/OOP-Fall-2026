class Rectangle {
    double length;
    double width;

    Rectangle() {
        this(1, 1);                       // calls the 2-argument constructor
        System.out.println("  ...via Rectangle()");
    }

    Rectangle(double side) {
        this(side, side);                 // calls the 2-argument constructor
        System.out.println("  ...via Rectangle(double side)");
    }

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
        System.out.println("Main constructor sets " + length + " x " + width);
    }

    double area() {
        return length * width;
    }
}

public class ConstructorChaining {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();
        System.out.println("Area = " + r1.area());

        Rectangle r2 = new Rectangle(5);
        System.out.println("Area = " + r2.area());

        Rectangle r3 = new Rectangle(4, 6);
        System.out.println("Area = " + r3.area());
    }
}
