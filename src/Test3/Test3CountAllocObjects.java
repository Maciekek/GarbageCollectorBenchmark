package Test3;
import MemoryEater.*;
/**
 * Created by Maciek on 2015-03-19.
 */
public class Test3CountAllocObjects implements Runnable{
    public static boolean flag = true;
    public static long count = 1;

    public static long count() throws InterruptedException {
        new Thread(new Test3CountAllocObjects()).start();
        Thread.sleep(10000);
        flag = false;
        System.out.println("<Test3CountAllocObjects> Amount of objects (changing size) creating in 10s: " + count);
        return count;

    }

    @Override
    public void run() {
        while (flag) {
            MemoryEater.alloc((int) count/99);
            count++;
        }
    }
}
