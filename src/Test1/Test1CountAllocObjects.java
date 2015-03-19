package Test1;
import MemoryEater.*;
/**
 * Created by Maciek on 2015-03-19.
 */
public class Test1CountAllocObjects implements Runnable {
    public static boolean flag = true;
    public static long count = 1;

    public static long count() throws InterruptedException {
        new Thread(new Test1CountAllocObjects()).start();
        Thread.sleep(10000);
        flag = false;
        System.out.println("<Test1CountAllocObjects> Amount of objects creating in 10s: " + count);
        return count;

    }

    @Override
    public void run() {
        while (flag) {
            MemoryEater.alloc(1);
            System.out.println(count);
            count++;
        }
    }
}
