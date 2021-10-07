import java.util.*;

/**
 * ArrayListBasedHeapPQ. Priority queue implementation
 * using heap based arraylist.
 *
 * @author Vaansh Lakhwara
 * @version 1.0
 */

public class ArrayListBasedHeapPQ {
    int callcount = 0;
    ArrayList<Job> heap;

    /**
     * Constructor
     */
    public ArrayListBasedHeapPQ() {
        heap = new ArrayList<Job>();
    }

    /**
     * Removes minimum key from queue.
     *
     * @return minimum key.
     */
    public Job removemin() {
        if (heap.isEmpty()) return null;
        Job answer = heap.get(0);
        swap(heap, 0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return answer;
    }

    /**
     * Insert an element into queue.
     *
     * @param element to insert
     */
    public Job insert(Job element, int waittimecommon) {
        element.entryTime = waittimecommon;
        heap.add(element);
        upheap(heap.size() - 1);
        return element;
    }

    /**
     * State of queue.
     *
     * @return boolean value of queue being empty.
     */
    public boolean isEmpty() {
        return heap.size() == 0;
    }


    /**
     * Returns minimum key from queue.
     *
     * @return minimum key.
     */
    public Job min() {
        if (heap.isEmpty())
            return null;
        return heap.get(0);
    }

    /**
     * Compare two jobs.
     *
     * @return integer value of comparison.
     */
    public static int compareJobs(Job o1, Job o2) {
        if (o1.finalPriority < o2.finalPriority)
            return -1;
        else if (o1.finalPriority > o2.finalPriority)
            return 1;
        else {
            if (o1.entryTime > o2.entryTime)
                return 1;
            else
                return -1;

        }
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap property.
     */
    protected void upheap(int j) {
        while (j > 0) {
            int p = parent(j);
            if (compareJobs(heap.get(j), heap.get(p)) >= 0)
                break;
            swap(heap, j, p);
            j = p;
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    protected void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = leftChild(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)) {
                int rightIndex = rightChild(j);
                if (compareJobs(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex;
            }
            if (compareJobs(heap.get(smallChildIndex), heap.get(j)) >= 0)
                break;
            swap(heap, j, smallChildIndex);
            j = smallChildIndex;
        }
    }


    /**
     * Swap two locations i and j in ArrayList a.
     *
     * @param a List.
     * @param i Integer.
     * @param j Integer.
     */
    private static void swap(ArrayList<Job> a, int i, int j) {
        Job t = a.get(i);
        a.set(i, a.get(j));
        a.set(j, t);
    }

    /**
     * Left child of node.
     *
     * @param i node
     * @return left child.
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Right child of node.
     *
     * @param i node
     * @return right child.
     */
    private static int rightChild(int i) {
        return 2 * i + 2;
    }

    /**
     * Parent node of child.
     *
     * @param i child node.
     * @return parent.
     */
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Has left.
     *
     * @param i node to check.
     * @return boolean value of left.
     */
    private boolean hasLeft(int i) {
        return leftChild(i) < heap.size();
    }

    /**
     * Has right.
     *
     * @param i node to check.
     * @return boolean value of right.
     */
    private boolean hasRight(int i) {
        return rightChild(i) < heap.size();
    }

}
