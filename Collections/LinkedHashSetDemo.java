import java.util.LinkedHashSet;

public class LinkedHashSetDemo {
    public static void main(String[] args) {

        LinkedHashSet<String> students = new LinkedHashSet<>();

        students.add("Ayan");
        students.add("Akash");
        students.add("Saikat");
        students.add("Deepraj");
        students.add("Amlan");
        students.add("Alik");

        // Displaying the set
        System.out.println("Initial Students: " + students);

        // Adding duplicate entries
        students.add("Ayan");
        students.add("Akash");

        // Displaying the set
        System.out.println("After adding duplicates: " + students);

        // Checking for an element
        System.out.println("Is Ayan registered? " + students.contains("Ayan"));

        // Removing an element
        students.remove("Alik");
        System.out.println("After removing Alik: " + students);

        // Displaying total unique students
        System.out.println("Total unique students: " + students.size());

        // Re-adding an element
        students.add("Alik");
        System.out.println("After re-adding Alik: " + students);

        // Iterating through the set
        System.out.println("\nAll registered students:");

        for (String name : students) {
            System.out.println(name);
        }
    }
}