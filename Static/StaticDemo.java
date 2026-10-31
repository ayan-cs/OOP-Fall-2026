class Student {
    String name;                          // instance variable: one per object
    int myCounter = 0;                    // instance variable: one per object
    static String college = "IIT Jodhpur"; // static variable: ONE shared copy
    static int count = 0;                 // static variable: ONE shared copy

    Student(String name) {
        this.name = name;
        myCounter++;
        count++;
    }

    void show() {                         // instance method: needs an object
        System.out.println(name + " studies at " + college);
    }

    static int getCount() {               // static method: no object needed
        return count;
    }
}

class MathUtil {
    static int square(int x) {
        return x * x;
    }

    static double average(int a, int b) {
        return (a + b) / 2.0;
    }
}

public class StaticDemo {
    public static void main(String[] args) {
        System.out.println("Students before: " + Student.getCount());

        Student s1 = new Student("Asha");
        Student s2 = new Student("Vikram");
        Student s3 = new Student("Neha");

        System.out.println("Students after: " + Student.getCount());
        System.out.println("s3.myCounter = " + s3.myCounter);

        s1.show();
        s2.show();

        Student.college = "IIT Jodhpur, Karwad";   // change once
        s1.show();                                 // every object sees it
        s2.show();

        System.out.println("Square of 7 = " + MathUtil.square(7));
        System.out.println("Average of 4 and 7 = " + MathUtil.average(4, 7));
        System.out.println("Java's own static method Math.sqrt(49) = " + Math.sqrt(49));
    }
}
