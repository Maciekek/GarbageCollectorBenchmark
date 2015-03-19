/**
 * Created by Maciej on 2015-03-19.
 */
public class Test3 implements Runnable{
    public static boolean flag = true;
    public static long count = 1;

    public static long Test1() {
        long startTime = System.nanoTime();
        for(int i=0;i<1000;i++){
            MemoryEater.alloc(1+(i/1000));
        }
        long endTime = System.nanoTime();
        System.out.println("Time for alloc 1000 obj: " + (endTime - startTime));
        return endTime-startTime;
    }

    public static long test1InTime() throws InterruptedException {
        new Thread(new Test3()).start();
        Thread.sleep(1000);
        flag = false;
        System.out.println("Wynik: " + count);
        return count;

    }

    @Override
    public void run() {
        int i = 1;
        while (flag) {
            MemoryEater.alloc(i+=0.1);
            System.out.println(count);
            count++;
        }
    }

}
