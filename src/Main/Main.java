package Main;
import Test1.*;
import Test2.Test2CountAllocObjects;
import Test2.Test2TimeForNAlloc;
import Test3.Test3CountAllocObjects;
import Test3.Test3TimeForNAlloc;
import Test4.Test4CountAllocObjects;
import Test4.Test4TimeForNAlloc;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        Test1TimeForNAlloc.count();
        System.out.println("----------------");

        Test1CountAllocObjects.count();
        System.out.println("----------------");

        Test2TimeForNAlloc.count();
        System.out.println("----------------");

        Test2CountAllocObjects.count();
        System.out.println("----------------");

        Test3TimeForNAlloc.count();
        System.out.println("----------------");

        Test3CountAllocObjects.count();
        System.out.println("----------------");

        Test4CountAllocObjects.count();
        System.out.println("----------------");

        Test4TimeForNAlloc.count();






    }



}
