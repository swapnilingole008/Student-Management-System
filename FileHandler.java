import java.io.*;
import java.util.*;

public class FileHandler {

    private static final String FILE_NAME = "students.dat";

    public static void addStudent(Student s) throws Exception {

        File file = new File(FILE_NAME);
        ObjectOutputStream oos;

        if (file.exists()) {
            oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME, true)) {
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            };
        } else {
            oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        }

        oos.writeObject(s);
        oos.close();

        System.out.println("Student added successfully!");
    }

    public static void viewStudents() throws Exception {

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No data found!");
            return;
        }

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

        try {
            while (true) {
                Student s = (Student) ois.readObject();
                System.out.println(s);
            }
        } catch (EOFException e) {}

        ois.close();
    }

    public static void searchStudent(int id) throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));

        boolean found = false;

        try {
            while (true) {
                Student s = (Student) ois.readObject();

                if (s.getId() == id) {
                    System.out.println("Found: " + s);
                    found = true;
                    break;
                }
            }
        } catch (EOFException e) {}

        if (!found) {
            System.out.println("Student not found!");
        }

        ois.close();
    }

    public static void updateStudent(int id, String newName, double newMarks) throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.dat"));

        boolean found = false;

        try {
            while (true) {
                Student s = (Student) ois.readObject();

                if (s.getId() == id) {
                    s.setName(newName);
                    s.setMarks(newMarks);
                    found = true;
                }

                oos.writeObject(s);
            }
        } catch (EOFException e) {}

        ois.close();
        oos.close();

        File original = new File(FILE_NAME);
        File temp = new File("temp.dat");

        original.delete();
        temp.renameTo(original);

        if (found)
            System.out.println("Student updated successfully!");
        else
            System.out.println("Student not found!");
    }

    public static void deleteStudent(int id) throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.dat"));

        boolean found = false;

        try {
            while (true) {
                Student s = (Student) ois.readObject();

                if (s.getId() != id) {
                    oos.writeObject(s);
                } else {
                    found = true;
                }
            }
        } catch (EOFException e) {}

        ois.close();
        oos.close();

        File original = new File(FILE_NAME);
        File temp = new File("temp.dat");

        original.delete();
        temp.renameTo(original);

        if (found) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Helper Method
    public static ArrayList<Student> getAllStudents() throws Exception {
        ArrayList<Student> list = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) return list;

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

        try {
            while (true) {
                list.add((Student) ois.readObject());
            }
        } catch (EOFException e) {}

        ois.close();
        return list;
    }

    // Sort by Marks
    public static void sortByMarks() throws Exception {
        ArrayList<Student> list = getAllStudents();

        list.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));

        for (Student s : list) {
            System.out.println(s);
        }
    }

    // Show Topper
    public static void showTopper() throws Exception {
        ArrayList<Student> list = getAllStudents();

        if (list.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        Student topper = list.get(0);

        for (Student s : list) {
            if (s.getMarks() > topper.getMarks()) {
                topper = s;
            }
        }

        System.out.println("Topper: " + topper);
    }

    // Count Students
    public static void countStudents() throws Exception {
        ArrayList<Student> list = getAllStudents();
        System.out.println("Total Students: " + list.size());
    }

    // Export to Text File
    public static void exportToTextFile() throws Exception {
        ArrayList<Student> list = getAllStudents();

        BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"));

        for (Student s : list) {
            writer.write(s.toString());
            writer.newLine();
        }

        writer.close();

        System.out.println("Data exported to students.txt successfully!");
    }
}
