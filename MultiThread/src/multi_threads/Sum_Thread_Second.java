package multi_threads;

public class Sum_Thread_Second extends Thread {
    int i, sum=0;
    public void run(){
        for(i=0;i<=5;i++){
            sum +=i;
            System.out.println("\n Sum of number from 1 up to " + i +"=" +sum);
        }
    }


    public static void main(String[] args) {
        Sum_Thread_Second st = new Sum_Thread_Second();
        Thread th= new Thread(st,"My thread");
        System.out.println("\n The Thread created is: " +th.getName());

        th.start();
    }
}