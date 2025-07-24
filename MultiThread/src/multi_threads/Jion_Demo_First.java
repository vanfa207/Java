package multi_threads;

class MyThread extends Thread {
    public void run(){
        for(int i=1; i<=5 ;i++){
            System.out.println("Child Thread:" +i);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println("Interrupted");
            }
        }
    }
}

public class Jion_Demo_First{
    public static void main(String[] args )throws InterruptedException{
        MyThread t = new MyThread();
        t.start();
        t.join();
        System.out.println("Main Thread finished after child thred");
    }
}