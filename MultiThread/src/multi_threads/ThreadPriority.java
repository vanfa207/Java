package multi_threads;

public class ThreadPriority {
    public static void main(String[] args){
        SumThread st =new SumThread();
        FactThread ft =new FactThread();
        Thread sumt =new Thread(st,"Sum thread");
        Thread factt =new Thread(ft,"Factorial thread");
        factt.setPriority(Thread.MIN_PRIORITY);
        sumt.setPriority(Thread.MAX_PRIORITY);
        
        System.out.println("\n The thread created is :" +sumt.getName());
        System.out.println("\n The thread created is :" +factt.getName());
        
        System.out.println("\n The priority value of "+sumt.getName() + "is =" +sumt.getPriority());
        System.out.println("\n The priority value of "+factt.getName() + "is =" +factt.getPriority());
        
        factt.start();
        sumt.start();
    }
}

class SumThread implements Runnable{
    int i, sum=0;
    public void run(){
        for(i=1;i<=5;i++) {
            sum +=i;
            System.out.println("\n Sum of numbers from 1 up to " + i+" = "+sum );
        }
    }
}

class FactThread implements Runnable{
    int i,n,fact=1;
    public void run(){
        for(i=1;i<=5;i++){
            fact *=i;
            System.out.println("\n Factorial of " + i+ "= " +fact);
        }
    }
}