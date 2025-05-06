/**
 * This is the Array heap class which will read an ArrayList and will use an implemented Priority Queue and will build a
 * heap under the necessary conditions
 * @since 04-12-2022
 * @author Josleyne Malan
 */

package files;
import java.util.ArrayList;
public class ArrayHeap <E extends Comparable <E>> implements PriorityQueue<E>{
    private ArrayList<E> heap ;// this represents the heap that we will resize

    public ArrayHeap (){
        heap = new ArrayList<E>();
    //an empty priority queue/heap
    }

    /**
     * Will insert an element while also re-organizing the list through the compare feature
     * @param element that will be inserted
     */
    
  public void insert(E element) {
        //int count = size();
        if (heap.isEmpty()){
            heap.add(element);
        }else{
       heap.add(element);
       bubbleUp(size()-1);}
    }

    /**
     * Return the root of a heap
     * @return the root of a heap
     */
    public E max() {
        if (heap.isEmpty()){
            return null;
        }else {
            return heap.get(0);
            //the root of the tree
        }
    }

    /**
     * Will remove the root of a heap and also re-organize the heap but bubbling down the re-assigned index
     * @return the removed index
     */
    public E removeMax() {
        E oldMax = max();
        int lastIndex = heap.size() - 1;
        if(heap.size() == 1){
            heap.remove(lastIndex);
            return oldMax;
        }else {
            lastIndex = heap.size() - 1;
            heap.set(0, heap.get(lastIndex));
            heap.remove(lastIndex);
            bubbleDown(0);
            return oldMax;
        }
    }

    public int size() {
        int c = heap.toArray().length;
        return c;
    }

    public boolean isEmpty() {
        //false=not empty vise versa
        return heap.size() == 0;
    }

    //the following are helper methods

    /**
     * Will swap two elements when called
     * @param i index of element
     * @param j index of element
     */
    public void swap (int i,int j){
        E placeHolder = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j,placeHolder);
    }

    /**
     * Will return the index of a parent
     * @param i index of a child
     * @return a parent index
     */
    public int parent (int i){
        // Math.floor() will round any decimal down
            int finalParent = (int) Math.floor((i-1)/2);
            return finalParent;

    }

    /**
     * Takes in the index of a parent and returns the index of their left child (note that this could be off the end of the array).
     * @param i index of a parent
     * @return the left child of the parent
     */
    public int leftChild(int i){
        int leftParent = (2 * i) + 1;

   return leftParent;
    }

    /**
     * Takes in the index of a parent and returns the index of their right child (note that this could be off the end of the array)
     * @param i index of parent
     * @return index of the right child
     */
    public int rightChild(int i){
        int rightParent = (2 * i) + 2;
        return rightParent;
    }

    /**
     * This method will move a given element from the bottom of a heap to a sorted position.
     * will continue to re assign the child and parent until the element is in the right part of the heap
     * @param i The element that is inserted
     */
    public void bubbleUp(int i) {

        E el = heap.get(i);//the child index
        int c = i;
        int p = parent(c);

        while (el.compareTo(heap.get(p)) > 0) {
            swap(c, p);
            c = p;
            p = parent(p);
            if (p < 0) {
                break;
            } else {
                el = heap.get(c);

            }

        }

    }

    /**
     * This will read the index of an element from the root of the heap and will bubble down until it reaches a balanced
     * level. This is also recursive. This will also compare the left child and right child to consider all factors
     * of comparison.
     * @param i index of the root
     */
    public void bubbleDown(int i) {
        int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);
    
        // Check if left child is larger
        if (left < heap.size() && heap.get(left).compareTo(heap.get(largest)) > 0) {
            largest = left;
        }
    
        // Check if right child is larger
        if (right < heap.size() && heap.get(right).compareTo(heap.get(largest)) > 0) {
            largest = right;
        }
    
        // Recursive Step: If the largest is not the current node, swap and continue
        if (largest != i) {
            swap(i, largest);
            bubbleDown(largest);
        }
    }

    /**
     * This will build an empty ArrayHeap and will fill it up with a given ArrayList and will re-assign the heap array.
     * Provides an inplace heapification with the bottom up appraoch 
     * @param array An array list
     *
     */
    public void buildMaxHeap(ArrayList<E> array) {
        heap = new ArrayList<>(array); // copy input array into heap

        // Start from the last parent node and bubble down
        for (int i = parent(heap.size() - 1); i >= 0; i--) {
            bubbleDown(i);
        }
    }

    /**
     * This method implements the heapsort algorithm as discussed in class and makes use of the buildMaxHeap.
     * It should return an arraylist of sorted elements.
     * This will take a Max Heap and will move it to a new array list in sorted order.
     * @param array an Array List
     * @return A new sorted Array List
     */
    public ArrayList<E> sort(ArrayList<E> array) {
        buildMaxHeap(array);
        ArrayList<E> sorted = new ArrayList<E>();
        int size = heap.size();
        for (int i = 0; i < size; i++) {
            // removeMax()
            sorted.add(removeMax());
        }
        return sorted;
    }

    /**
     * This function checks if the current max-heap data structure follows the nessasary conditions
     * to be a proper max-heap
     * Condition : 
     * 1) Must be a full binary tree (left/rightchild) unless it is a leaf
     * 2) the parent of any child must always be greater
     * @return true if the conditions of the heap remains entact, false if otherwise
     */
    public boolean checkinvariant(){
        //ArrayHeap<E> maxHeap
        //maxHeap should be the one that is currently under the shared constructor
        for (int i=0; i < heap.size(); i++){
            int left = leftChild(i);
            int right = rightChild(i);
            //detects if the right and left child are greatere than the parent - a violation of max-heap!!!!!!!
            if (left < heap.size() && heap.get(i).compareTo(heap.get(left)) < 0){
                return false;
            }
            if (right < heap.size() && heap.get(i).compareTo(heap.get(right)) < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * As a way to violate the structure of a max heap, a the value of a leaf will be copy onto the root of the heap.
     * @return Nothing, will copy the value from a leaf to the root
     */
    public void corruptheap(){
        int leaf = heap.size() - 1;
        if (leaf >= 0 && leaf < heap.size()){
            heap.set(0,heap.get(leaf));
        }
    }
    /**
     * This will print an array heap based on levels of a binary heap.
     * @return a printed array heap
     */
    @Override
    public String toString() {
        String str = "";
        int occur = 1;
        int j = 0;
        for(int i=0; i < heap.size() ;i++){
            if (j == occur) {
                str = str + "\n";
                j=0;
                occur = occur * 2;
            }
            str = str + " " + heap.get(i).toString();
            j ++;
        }
        return str;
    }
}
