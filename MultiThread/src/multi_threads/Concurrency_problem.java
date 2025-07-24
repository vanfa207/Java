package multi_threads;

public class Concurrency_problem extends Thread{
    public static int amount = 0;
    public static void main(String[] args){
        Concurrency_problem thread = new Concurrency_problem();
        thread.start();
        System.out.println(amount);
        amount++;
        System.out.println(amount);
    }
    
    public void run(){
        amount++;
        System.out.println("Main Thread :"+amount);
    }
}

//public class Thread_Isalive extends Thread {
//    public static int amount =0;
//    public static void main (String [] args){
//        Thread_Isalive thread = new Thread_Isalive();
//        thread.start();
//            while(thread.isAlive()){
//                System.out.println("Waiting...");
//            }
//            System.out.println("Main :"+ amount );
//            amount++;
//            System.out.println("Main :"+ amount );
//        }
//
//        public void run(){
//            amount++;
//        }
//}