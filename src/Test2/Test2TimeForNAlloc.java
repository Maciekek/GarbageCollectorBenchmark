package Test2;
import MemoryEater.*;
/**
 * Created by Maciek on 2015-03-19.
 */
public class Test2TimeForNAlloc implements Runnable {
    public static long count = 1;
    public static long time = 0;
    public static void count() throws InterruptedException {
        for(int i=0;i<4;i++){
            new Thread(new Test2TimeForNAlloc()).start();
        }
        Thread.sleep(5000);
        System.out.println("<Test2TimeForNAlloc> Time for alloc 1000 obj: " + time);
        System.out.println("<Test2TimeForNAlloc> Time for alloc 1000 obj (in milisec): " + time/1000000.0);
    }
    @Override
    public void run() {
        long startTime = System.nanoTime();
        for(int i=0;i<250;i++){
            MemoryEater.alloc(1);
        }
        long endTime = System.nanoTime();
        time+=endTime-startTime;
    }

}
