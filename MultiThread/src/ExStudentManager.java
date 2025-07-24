import java.util.ArrayList;
import java.util.List;

public class ExStudentManager {
    private List<ExStudent> exStudents;

    public ExStudentManager() {
        this.exStudents = new ArrayList<>();
    }

    // Add an ex-student
    public void addExStudent(ExStudent student) {
        exStudents.add(student);
    }

    // Remove an ex-student by name
    public void removeExStudent(String name) {
        exStudents.removeIf(student -> student.getName().equalsIgnoreCase(name));
    }

    // Display all ex-students
    public void displayExStudents() {
        if (exStudents.isEmpty()) {
            System.out.println("No ex-students available.");
        } else {
            for (ExStudent student : exStudents) {
                System.out.println(student);
            }
        }
    }

    // Find an ex-student by name
    public ExStudent findExStudentByName(String name) {
        for (ExStudent student : exStudents) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
}
