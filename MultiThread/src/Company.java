import java.util.ArrayList;
import java.util.List;

public class Company {
    private List<Employee> employees;

    public Company() {
        this.employees = new ArrayList<>();
    }

    // Add an employee to the company
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Start all employees' tasks in parallel using threads
    public void startWorking() {
        List<Thread> threads = new ArrayList<>();

        for (Employee employee : employees) {
            Thread thread = new Thread(employee);
            threads.add(thread);
            thread.start();  // Start the employee's task in a separate thread
        }

        // Optionally, wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();  // Ensure main thread waits for all employee tasks to complete
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }
        }

        System.out.println("All tasks completed.");
    }
}
