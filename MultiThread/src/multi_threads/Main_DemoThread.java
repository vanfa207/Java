package multi_threads;

public class Main_DemoThread extends Thread {
   public static void main(String [] args) {
       Main_DemoThread thread = new Main_DemoThread();
       thread.start();
       System.out.print("This code is outside of the thred \n");
   } 
   
    public void run(){
        System.out.println("This code is running in a thread");
    }

}
