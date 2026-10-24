class Calculator {
    int base;

    Calculator(int base) {
        this.base = base;
    }

    int add(int x, int y) {
        return x + y + base;
    }
}

public class Demo {
    static int square(int n) {
        return n * n;
    }

    public static void main(String[] args) {
        Calculator c = new Calculator(10);
        int result = c.add(3, 4);

        System.out.println("Result = " + result);
        System.out.println("Square = " + square(result));
    }
}