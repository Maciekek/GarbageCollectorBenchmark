/**
 * Created by Maciej on 2015-03-19.
 */
public class Test4 implements Runnable {
    public static boolean flag = true;
    public static long count = 1;

    public static void testInTime() throws InterruptedException {
        long startTime = System.nanoTime();
        for(int i=0;i<4;i++){
            new Thread(new Test4()).start();
        }
        long endTime = System.nanoTime();
        System.out.println("Time for alloc 1000 obj: " + (endTime - startTime));

    }

    public long  Test4() throws InterruptedException {
        count=0;
        flag = true;
        for (int i=0;i<4;i++){
            new Thread(new Test4()).start();
        }
        Thread.sleep(1000);
        flag = false;
        Thread.sleep(20);
        System.out.println("Wynik: " + count);
        return count;
    }
//    @Override
//    public void run() {
//
//        for(int i=0;i<250;i++){
//            MemoryEater.alloc(1+(i/1000));
//        }
//    }

    public void run() {

        int i = 1;
        while (flag) {
            MemoryEater.alloc(i+=0.1);
//            System.out.println(count);
            count++;
        }
    }

}
