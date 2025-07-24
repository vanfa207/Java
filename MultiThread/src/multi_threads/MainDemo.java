package multi_threads;

class MyThread extends Thread {
    public void run(){
        System.out.println("Thread running: " + getName());
    }
}

public class MainDemo {
    public static void main(String[] args) {
        new MyThread().start();
    }
}
