package MemoryEater;

/**
 * Created by Maciej on 2015-03-13.
 */
public class MemoryEater {
    private int[] buffer;
    public MemoryEater(int size){
        buffer = new int[size];
    }
    public static MemoryEater alloc(int sizeInMb){
        return new MemoryEater(sizeInMb*(1024*1024)/4);
    }
}
