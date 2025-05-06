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
        //example test cases
        Integer[] arr = { -200, 3, 90, -77, 1, 2, 69, -3 };
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(arr));
        // make a new heap out of the array
        ArrayHeap<Integer> heaps = new ArrayHeap<Integer>();
        ArrayList<Integer> sortedArray = heaps.sort(array);
        System.out.println(sortedArray + "\n");
       Integer[] r = {-2,3,9,-7,1,2,6,-3};

        // create a new heap of Integers
        // use a for loop to insert the elements above into the heap
        ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
        for (Integer h :r){
            heap.insert(h);
        }
        System.out.println(heap);
        System.out.println("Max element removed is: " + heap.removeMax());
        System.out.println(heap);

        ArrayHeap<Character> letterHeap = new ArrayHeap<Character>();
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
        letterHeap.removeMax();
        System.out.println(letterHeap);
        //System.out.println("Hello World!");
    }
}
