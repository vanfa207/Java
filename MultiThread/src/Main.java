public class Main {
    public static void main(String[] args) {
        // Create a company
        Company company = new Company();

        // Create employees from different departments
        Employee employee1 = new Employee("Alice", "HR", "Recruitment");
        Employee employee2 = new Employee("Bob", "IT", "System Maintenance");
        Employee employee3 = new Employee("Charlie", "Marketing", "Campaign Management");
        Employee employee4 = new Employee("David", "Sales", "Client Meeting");

        // Add employees to the company
        company.addEmployee(employee1);
        company.addEmployee(employee2);
        company.addEmployee(employee3);
        company.addEmployee(employee4);

        // Start working on tasks concurrently
        company.startWorking();
    }
}
