/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package files;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jmalan
 */
public class ConcurrentHeap {

    public static void main(String[] args) {
        //running basic examples with different type
        integerHeapExample();
        letterHeapExample();

        concurrentExample();
        //run it concurrently 
        

        //System.out.println("Hello World!");
    }
    
    public static void concurrentExample(){
        ArrayHeap <Integer> heap = new ArrayHeap<>();

        Runnable multiThread = new Thread(() -> {
            for (int i=0; i < 10; i++){
                heap.insert((int)(Math.random()*100));
            }
        });

        Thread t1 = new Thread(multiThread);
        t1.start();

        try {
            t1.join();  // wait for T to be done
        } catch  (InterruptedException e) {
            System.out.println("Not Handling exceptions yet ... goodbye");
        }

        System.out.println("HEAP HERE: " +heap + "\n");
    }
    public static void integerHeapExample(){
        Integer[] arr = { -200, 3, 90, -77, 1, 2, 69, -3 };
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(arr));
        // make a new heap out of the array
        ArrayHeap<Integer> heaps = new ArrayHeap<>();
        ArrayList<Integer> sortedArray = heaps.sort(array);
        System.out.println(sortedArray + "\n");
        System.out.println("Checks if the heap is a valid structure " + heaps.checkinvariant());
       Integer[] r = {-2,3,9,-7,1,2,6,-3};

        // create a new heap of Integers
        // use a for loop to insert the elements above into the heap
        ArrayHeap<Integer> heap = new ArrayHeap<>();
        for (Integer h :r){
            heap.insert(h);
        }
        System.out.println(heap);
        System.out.println("Max element removed is: " + heap.removeMax());
        System.out.println(heap);
        System.out.println("Checks if the heap is a valid structure " + heap.checkinvariant());
    }

    public static void letterHeapExample(){
        ArrayHeap<Character> letterHeap = new ArrayHeap<>();
        letterHeap.insert('A');
        letterHeap.insert('C');
        letterHeap.insert('G');
        letterHeap.insert('B');
        letterHeap.insert('D');
        letterHeap.insert('G'); // inserting again, will still both copies
        letterHeap.insert('F');
        letterHeap.insert('E');
        letterHeap.insert('H');
        letterHeap.insert('I');
        System.out.println("size:" + letterHeap.size());
        System.out.println(letterHeap);
        System.out.println("Checks if the heap is a valid structure " + letterHeap.checkinvariant());
        letterHeap.removeMax();
        System.out.println(letterHeap);
        System.out.println("Checks if the heap is a valid structure " + letterHeap.checkinvariant());
        System.out.println("Check for the last value ");
        letterHeap.corruptheap();
        System.out.print(letterHeap);
        System.out.println("Checks if the heap is a valid structure " + letterHeap.checkinvariant());
    }
}

