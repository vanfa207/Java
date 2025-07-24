package multi_threads;

public class Main_Runnable implements Runnable {
    public static void main(String [] args){
        Main_Runnable obj = new Main_Runnable();
        Thread thread = new Thread(obj);
        thread.start();
        System.out.println("This code is outside of the thread");
    }
    public void run(){
        System.out.println("This code is running in a thread");
                
    }
}
