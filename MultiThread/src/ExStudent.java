public class ExStudent {
    private String name;
    private int age;
    private String graduationYear;
    private String course;

    public ExStudent(String name, int age, String graduationYear, String course) {
        this.name = name;
        this.age = age;
        this.graduationYear = graduationYear;
        this.course = course;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public String getCourse() {
        return course;
    }

    // Display student information
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Graduation Year: " + graduationYear + ", Course: " + course;
    }
}
