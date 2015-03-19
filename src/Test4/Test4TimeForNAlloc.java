package Test4;
import MemoryEater.*;
/**
 * Created by Maciek on 2015-03-19.
 */
public class Test4TimeForNAlloc implements Runnable {
    public static boolean flag = true;
    public static long count = 1;
    public static long time = 0;

    public static long count() throws InterruptedException {
        for (int i=0;i<4;i++){
            new Thread(new Test4TimeForNAlloc()).start();
        }
        Thread.sleep(10000);
        flag = false;
        System.out.println("<Test4TimeForNAlloc> Time for alloc 1000 obj: " + time);
        System.out.println("<Test4TimeForNAlloc> Time for alloc 1000 obj (in milisec): " + time/1000000.0);
        return count;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        for(int i=0;i<1000;i++){
            MemoryEater.alloc(1+(i/99));
            System.out.println(1+(i/99));
        }
        long endTime = System.nanoTime();
        time+=endTime-startTime;
    }
}
