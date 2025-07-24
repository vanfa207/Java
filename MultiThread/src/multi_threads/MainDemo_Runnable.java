package multi_threads;

class MyRunnable implements Runnable{
    public void run(){
        System.out.println("Runnable thread running");
    }
}

public class MainDemo_Runnable {
    public static void main(String[] args){
        Thread t= new Thread((Runnable) new MyRunnable());
        t.start();
    }
}
