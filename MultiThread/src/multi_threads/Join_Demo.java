package multi_threads;

public class Join_Demo {
    public static void main(String[] args) {
        NewThread ob3 = new NewThread("3");
        NewThread ob1 = new NewThread("1");
        NewThread ob2 = new NewThread("2");

        System.out.println("Thread 1 is alive: " + ob1.t.isAlive());
        System.out.println("Thread 2 is alive: " + ob2.t.isAlive());
        System.out.println("Thread 3 is alive: " + ob3.t.isAlive());

        try {
            System.out.println("Waiting for threads to finish.");
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("Thread 1 is alive: " + ob1.t.isAlive());
        System.out.println("Thread 2 is alive: " + ob2.t.isAlive());
        System.out.println("Thread 3 is alive: " + ob3.t.isAlive());
    }
}

class NewThread implements Runnable {
    String name;
    Thread t;

    public NewThread(String name) {
        this.name = name;
        t = new Thread(this, name);
        System.out.println("Initial Thread: " + t.getName());
        t.start();
    }

    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000); 
                System.out.println("Thread " + name + ": " + i);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + name + " interrupted.");
        }
        System.out.println("Thread " + name + " exiting.");
    }
}
