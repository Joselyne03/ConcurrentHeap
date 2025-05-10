package files;
public interface PriorityQueue<E extends Comparable<E>> {
    void insert(E element) throws InterruptedException;

    E max();

    E removeMax();

    int size();

    boolean isEmpty();
}
