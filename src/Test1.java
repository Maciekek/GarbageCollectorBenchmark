/**
 * Created by Maciej on 2015-03-19.
 */
public class Test1 implements Runnable{
    public static boolean flag = true;
    public static long count = 1;

    public static long Test1() {
        long startTime = System.nanoTime();
        for(int i=0;i<1000;i++){
            MemoryEater.alloc(1);
        }
        long endTime = System.nanoTime();
        System.out.println("Time for alloc 1000 obj: " + (endTime - startTime));
        return endTime-startTime;
   }

    public static long test1InTime() throws InterruptedException {
        new Thread(new Test1()).start();
        Thread.sleep(1000);
        flag = false;
        System.out.println("Wynik: " + count);
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
