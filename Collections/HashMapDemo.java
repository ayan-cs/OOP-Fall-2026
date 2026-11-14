import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {

        // Creating a HashMap
        HashMap<Integer, String> students = new HashMap<>();

        // Adding key-value pairs
        students.put(101, "Ayan");
        students.put(102, "Saikat");
        students.put(103, "Akash");
        students.put(104, "Deepraj");

        // Displaying the HashMap
        System.out.println("Student Records: " + students);

        // Retrieving a value using its key
        System.out.println("Student with roll no. 102: " + students.get(102));

        // Updating an existing record
        students.put(104, "Akash");
        System.out.println("After updating: " + students);

        // Checking whether a key exists
        System.out.println("Does roll no. 101 exist? " + students.containsKey(101));

        // Removing a record
        students.remove(104);
        System.out.println("After removing roll no. 104: " + students);

        // Displaying all records
        System.out.println("\nAll Student Records:");

        for (Integer rollNo : students.keySet()) {
            System.out.println(rollNo + " : " + students.get(rollNo));
        }

        // Displaying total records
        System.out.println("Total students: " + students.size());
    }
}