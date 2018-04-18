import javafx.util.Pair;

import java.util.Arrays;

public class FlexiblePQ<T extends Comparable<T>, K extends Comparable<K>>  {
    Pair<T, K>[] heap; //define a heap of java pairs
    int size;
    String type;

    public FlexiblePQ(Pair<T, K>[] heap, int n) { //The flexible PQ requires the intitial content size, the heap.
        this.size = n;
        this.heap = Arrays.copyOf(heap, size);
        this.type = "min"; //!default to PQ using min heap
    }

    //toggle switches the min/max priority up
    public void toggle() {
        if(type.equals("min")) {
            type = "max";
        }
        else {
            type = "min";
        }

        buildHeap();
    }

    //Switch to a min priority
    public void switchToMin() {
        type = "min";
        buildHeap();
    }

    //Switch to a max priority
    public void switchToMax() {
        type = "max";
        buildHeap();
    }

    //Retrieve state
    public String state() {
        return type;
    }

    //Heapify takes into account the current state, takes an index and puts it in the right position relative to its parents.
    //Since a max heap should always have parents greater than its children, if the child of the index passed is larger
    //It swaps them
    public void heapify(int index) {
        int extremity = index;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        if(type.equals("min")) {

            if (leftIndex < size && heap[index].getKey().compareTo(heap[leftIndex].getKey()) > 0) {
                extremity = leftIndex;
            }
            if (rightIndex < size && heap[extremity].getKey().compareTo(heap[rightIndex].getKey()) > 0) {
                extremity = rightIndex;
            }

        }
        if(type.equals("max")) {
            if (leftIndex < size && heap[index].getKey().compareTo(heap[leftIndex].getKey()) < 0) {
                extremity = leftIndex;
            }
            if (rightIndex < size && heap[extremity].getKey().compareTo(heap[rightIndex].getKey()) < 0) {
                extremity = rightIndex;
            }
        }

        if (extremity != index) {
            swap(index, extremity);
            heapify(extremity);
        }
    }

    //Build heap is an initializer method that passes every index to heapify, thus heapifying the entire array into a min or max priority queue
    public void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    //Insert a new java pair into the array. Depending on min or max, it will insert it at the start of the array then heapify that index.
    public void insert(T key, K val) {
        Pair<T, K> elem = new Pair<>(key, val);
        heap = Arrays.copyOf(heap, size + 1);
        int i = size;
        int parentIndex = (int) Math.floor((i - 1) / 2);
        if(type.equals("min")) {
            while (i > 0 && elem.getKey().compareTo(heap[parentIndex].getKey()) < 0) {
                heap[i] = heap[parentIndex];
                i = parentIndex;
                parentIndex = (int) Math.floor((i - 1) / 2);
            }
        }
        if(type.equals("max")) {
            while (i > 0 && elem.getKey().compareTo(heap[parentIndex].getKey()) > 0) {
                heap[i] = heap[parentIndex];
                i = parentIndex;
                parentIndex = (int) Math.floor((i - 1) / 2);
            }
        }
        heap[i] = elem;
        size++;
    }


    //If a min array it returns the smallest value (the root), if a max array, returns the largest (also the root), then pops the root.
    public Pair<T, K> remove() {
        if (size == 0) return null;

        Pair<T, K> minORMax = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return minORMax;
    }

    //Return the content size of the array
    public int getSize() {
        return size;
    }

    //Return the heap
    public Pair<T, K>[] getHeap() {
        return heap;
    }
    //Get the root
    public Pair<T, K> getTop() {
        if (size == 0) {
            return null;
        } else {
            return heap[0];
        }
    }
    //Determine whether there is any content in the heap
    public boolean isEmpty() {
        return size == 0;
    }

    //Print the Priority Queue
    public void printHeap() {
        if (heap == null)
            System.out.print("null");
        int iMax = size - 1, i;
        if (iMax == -1)
            System.out.print("[]");

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (i = 0; i < iMax; i++) {
            b.append(heap[i].getValue());
            b.append(", ");
        }
        System.out.println(b.append(heap[i].getValue()).append(']').toString());
    }

    //Swap the position of two elements in the main PQ
    private void swap(int firstIndex, int secondIndex) {
        Pair<T, K> temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    }

    public static void main(String[] args) {
        Pair<Integer, String> pair1 = new Pair<Integer, String>(1, "1");
        Pair<Integer, String> pair2 = new Pair<Integer, String>(2,"2");
        Pair<Integer, String> pair3 = new Pair<Integer, String>(3,"3");
        Pair<Integer, String> pair4 = new Pair<Integer, String>(4,"4");
        Pair<Integer, String> pair5 = new Pair<Integer, String>(5,"5");
        Pair<Integer, String> pair6 = new Pair<Integer, String>(6,"6");
        Pair<Integer, String> pair7 = new Pair<Integer, String>(7,"7");
        Pair<Integer, String> pair8 = new Pair<Integer, String>(8,"8");
        Pair<Integer, String> pair9 = new Pair<Integer, String>(9, "9");
        Pair<Integer, String> pair10 = new Pair<Integer, String>(10, "10");
        Pair<Integer, String> pair11 = new Pair<Integer, String>(11, "11");
        Pair<Integer, String> pair12 = new Pair<Integer, String>(12, "12");
        Pair<Integer, String> pair13 = new Pair<Integer, String>(13, "13");
        Pair<Integer, String> pair14 = new Pair<Integer, String>(14, "14");
        Pair<Integer, String> pair15 = new Pair<Integer, String>(15, "15");
        Pair<Integer, String> pair16 = new Pair<Integer, String>(16, "16");
        Pair<Integer, String> pair17 = new Pair<Integer, String>(17, "17");
        Pair<Integer, String> pair18 = new Pair<Integer, String>(18, "18");
        Pair<Integer, String> pair20 = new Pair<Integer, String>(20, "20");

        Pair<String, Integer>[] a = new Pair[]{pair1, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair9, pair10, pair11, pair12, pair13, pair14, pair15, pair16, pair17, pair18, pair20};


        FlexiblePQ heap = new FlexiblePQ(a, 19);
        System.out.print("Base: ");
        heap.printHeap();
        heap.buildHeap();
        System.out.print("Default (min): ");
        heap.printHeap();
        System.out.print("Size: " + heap.getSize() + ", IsEmpty: " + heap.isEmpty());
        heap.insert(100, "100");
        System.out.print("Default (min) + insert: ");
        heap.printHeap();
        heap.toggle();
        heap.insert(99, "99");
        System.out.print("Toggle (max) + insert: ");
        heap.printHeap();
        System.out.print("State: " + heap.type + ", Top: " + heap.getTop().getKey() + ", Size: " + heap.getSize());
        System.out.print("Switch to min + insert: ");
        heap.switchToMin();
        heap.insert(50, "50");
        heap.printHeap();
        System.out.print("Switch to max + remove x2: ");
        heap.switchToMax();
        heap.remove();
        heap.remove();
        heap.printHeap();
        System.out.print("State: " + heap.type + ", Top: " + heap.getTop().getKey() + ", Size: " + heap.getSize() + "isEmpty: " + heap.isEmpty());


    }
}
