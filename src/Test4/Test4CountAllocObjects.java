package Test4;

import MemoryEater.MemoryEater;

/**
 * Created by Maciek on 2015-03-19.
 */
public class Test4CountAllocObjects implements Runnable {
    public static boolean flag = true;
    public static long count = 1;

    public static long count() throws InterruptedException {
        count = 0;
        flag = true;
        for (int i = 0; i < 4; i++) {
            new Thread(new Test4CountAllocObjects()).start();
        }
        Thread.sleep(10000);
        flag = false;
        Thread.sleep(20);
        System.out.println("<Test4CountAllocObjects> Amount of objects creating in 10s:  " + count);
        return count;
    }

    public void run() {
        while (flag) {
            MemoryEater.alloc((int) count/99);
            System.out.println(count/99);
            count++;
        }
    }
}
