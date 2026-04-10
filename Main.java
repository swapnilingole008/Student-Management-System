import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort by Marks");
            System.out.println("7. Show Topper");
            System.out.println("8. Count Students");
            System.out.println("9. Export to Text File");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    sc.nextLine(); // FIX

                    if (marks < 0 || marks > 100 || name.isEmpty()) {
                        System.out.println("Invalid input!");
                        break;
                    }

                    FileHandler.addStudent(new Student(id, name, marks));
                    break;

                case 2:
                    FileHandler.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = sc.nextInt();
                    sc.nextLine(); // FIX
                    FileHandler.searchStudent(searchId);
                    break;

                case 4:
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter new Marks: ");
                    double newMarks = sc.nextDouble();
                    sc.nextLine(); // FIX

                    FileHandler.updateStudent(updateId, newName, newMarks);
                    break;

                case 5:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine(); // FIX
                    FileHandler.deleteStudent(deleteId);
                    break;

                case 6:
                    FileHandler.sortByMarks();
                    break;

                case 7:
                    FileHandler.showTopper();
                    break;

                case 8:
                    FileHandler.countStudents();
                    break;

                case 9:
                    FileHandler.exportToTextFile();
                    break;

                case 10:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
