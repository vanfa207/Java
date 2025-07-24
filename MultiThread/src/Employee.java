public class Employee implements Runnable {
    private String name;
    private String department;
    private String task;

    public Employee(String name, String department, String task) {
        this.name = name;
        this.department = department;
        this.task = task;
    }

    @Override
    public void run() {
        // Simulate performing the task
        System.out.println(name + " from " + department + " is working on: " + task);
        try {
            // Simulate time taken to perform the task (e.g., 2 seconds)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(name + " was interrupted while working.");
        }
        System.out.println(name + " from " + department + " finished the task: " + task);
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getTask() {
        return task;
    }
}
