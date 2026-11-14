import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {

        // Creating an ArrayList
        ArrayList<String> students = new ArrayList<>();

        // Adding elements
        students.add("Ayan");
        students.add("Akash");
        students.add("Saikat");
        students.add("Deepraj");

        // Displaying all elements
        System.out.println("Student List: " + students);

        // Accessing an element
        System.out.println("Student at index 1: " + students.get(1));

        // Updating an element
        students.set(2, "Saikat Mondal");
        System.out.println("After updating: " + students);

        // Removing an element
        students.remove("Akash");
        System.out.println("After removing Akash: " + students);

        // Checking whether an element exists
        System.out.println("Is Ayan present? " + students.contains("Ayan"));

        // Finding the size
        System.out.println("Total students: " + students.size());

        students.add("Akash");
        System.out.println("After re-adding Akash: " + students);

        // Traversing using a for-each loop
        System.out.println("\nFinal Student List:");

        for (String name : students) {
            System.out.println(name);
        }
    }
}