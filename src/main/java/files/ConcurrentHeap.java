package files;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jmalan
 */
public class ConcurrentHeap {
    // Color Print statments: 
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BRIGHTRED = "\u001B[91m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    static double min = 0.0;  // how long to wait between consecutive apes going one way
    static double var = 0.01;  //  4 seconds is usually enough, but vary a bit to see what happens
    
        public static void main(String[] args) {
            //running basic examples with different type

            //integerHeapExample();
            //letterHeapExample();
            //corruptExample();
    
            concurrentExample();
            //run it concurrently 
    
            //System.out.println("Hello World!");
        }
    
        private static java.util.Random dice = new java.util.Random(); // random number generator, for delays mostly	
        public static void tryToSleep(double secMin, double secVar) {
            try {
                java.lang.Thread.sleep(Math.round(secMin*1000) + Math.round(dice.nextDouble()*(secVar)*1000));
            } catch (InterruptedException e) {
                System.out.println("Not Handling interruptions yet ... just going on with the program without as much sleep as needed ... how appropriate!");
            }
        }
    
    public static void concurrentExample(){
        ArrayHeap <Integer> heap = new ArrayHeap<>();
        ArrayList <Thread> threads = new ArrayList<>();

        for (int i = 0; i<5; i++){
            Thread insertThread = new Thread(() -> { 
                for (int j=0; j < 20; j++){
                    int value = (int)(Math.random()*100);
                    heap.insert(value);
                    System.out.println(ANSI_CYAN + Thread.currentThread().getName() + " Inserted val: " + value + ANSI_RESET);
                    tryToSleep(min, var);
                    if (!heap.checkinvariant()){
                        System.out.println(ANSI_BRIGHTRED + "INVARIANT IS VIOLATED USING : insert()" + ANSI_RESET);
                        System.out.println(ANSI_BRIGHTRED + "HEAP HERE: \n" +heap + ANSI_RESET + "\n");
                    }
                }
            });
            threads.add(insertThread);

        }

        for (int i=0; i<5;i++){
            Thread deleteThread = new Thread(() -> {
                for (int k=0; k < 20; k++){
                    if (heap.size() > 1){
                       Integer valueRemoved = heap.removeMax();
                       System.out.println(ANSI_RED + Thread.currentThread().getName() + ((valueRemoved != null) ? ": The value deleted is " + valueRemoved : ": No value was removed") + ANSI_RESET);
                    }
                    if (!heap.checkinvariant()){
                        System.out.println(ANSI_BRIGHTRED + "INVARIANT IS VIOLATED USING : deleteThread() " + ANSI_RESET);
                        System.out.println(ANSI_BRIGHTRED + "HEAP HERE: \n" +heap + ANSI_RESET + "\n");
                    }
                    tryToSleep(min, var);
                    
                }
            });
            threads.add(deleteThread);
        }

        for (int i = 0; i<5; i++){
            //this reading thread wcan possibly 
            Thread readSize = new Thread(() -> { 
                for (int j=0; j < 20; j++){
                    //int value = (int)(Math.random()*100);
                    int size = heap.size();
                    System.out.println(ANSI_BLUE + Thread.currentThread().getName() + " The size is: " + size + ANSI_RESET);
                    tryToSleep(min, var);
                    if (!heap.checkinvariant()){
                        System.out.println(ANSI_BRIGHTRED + "INVARIANT IS VIOLATED USING: readSize() " + ANSI_RESET);
                        System.out.println(ANSI_BRIGHTRED + "HEAP HERE: \n" +heap + ANSI_RESET + "\n");
                    }
                }
            });
            threads.add(readSize);

        }

        for(Thread thread:threads){
            thread.start();
        }

        for (Thread thread : threads){
            try {
                thread.join();  // wait for T to be done
            } catch  (InterruptedException e) {
                System.out.println("Not Handling exceptions yet ... goodbye");
            }
    
        }
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

    public static void corruptExample(){
        Integer[] r = {10,-3,12,11,45,79,78,-100};

        ArrayHeap<Integer> heap = new ArrayHeap<>();
        for (Integer h :r){
            heap.insert(h);
        }
        System.out.println("Original Heap: \n" + heap);
        System.out.println("Original Heap Checker: " + heap.checkinvariant());
        heap.corruptheap();
        System.out.println("Corrupted Heap: \n" + heap + "\n" + "New Status of heap: " + heap.checkinvariant());
    }

}
