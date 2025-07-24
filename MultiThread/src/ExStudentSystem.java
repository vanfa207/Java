import java.util.Scanner;

public class ExStudentSystem {
    public static void main(String[] args) {
        ExStudentManager manager = new ExStudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Ex-Student Management System ---");
            System.out.println("1. Add Ex-Student");
            System.out.println("2. Remove Ex-Student");
            System.out.println("3. Display All Ex-Students");
            System.out.println("4. Find Ex-Student by Name");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // To consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Graduation Year: ");
                    String gradYear = scanner.nextLine();
                    System.out.print("Enter Course: ");
                    String course = scanner.nextLine();
                    manager.addExStudent(new ExStudent(name, age, gradYear, course));
                    break;

                case 2:
                    System.out.print("Enter Name of Ex-Student to Remove: ");
                    String removeName = scanner.nextLine();
                    manager.removeExStudent(removeName);
                    break;

                case 3:
                    manager.displayExStudents();
                    break;

                case 4:
                    System.out.print("Enter Name to Search: ");
                    String searchName = scanner.nextLine();
                    ExStudent student = manager.findExStudentByName(searchName);
                    if (student != null) {
                        System.out.println("Found: " + student);
                    } else {
                        System.out.println("Ex-Student not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
