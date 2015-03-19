package Test1;
import MemoryEater.*;
/**
 * Created by Maciek on 2015-03-19.
 */
public class Test1TimeForNAlloc {
    public static long count() {
        long startTime = System.nanoTime();
        for(int i=0;i<1000;i++){
            MemoryEater.alloc(1);
        }
        long endTime = System.nanoTime();
        System.out.println("<Test1TimeForNAlloc> Time for alloc 1000 obj: " + (endTime - startTime));
        System.out.println("<Test1TimeForNAlloc> Time for alloc 1000 obj (in milisec): " + (endTime - startTime)/1000000.0);
        return endTime-startTime;
    }


}
