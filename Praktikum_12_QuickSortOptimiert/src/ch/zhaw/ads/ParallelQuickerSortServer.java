package ch.zhaw.ads;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ParallelQuickerSortServer extends Thread implements CommandExecutor {
    int[] arr, sourceArr;
    int left, right;
    static int dataElems = 100000;
    static int insertion_threshold = 100;
    private final int SPLIT_THRESHOLD = 10000;
    private final int DATARANGE = 10000000;

    public ParallelQuickerSortServer() {}

    public ParallelQuickerSortServer(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    public void run() {
        int mid = 0;
        Thread t1 = null;
        Thread t2 = null;

        if (left < right) {
            // To do Aufgabe 12.3
        }
    }

    private void quickerSort(int[] arr, int left, int right) {
        // To do Aus Aufgabe 12.1 übernehmen
    }

    private int partition(int[] arr, int left, int right) {
        // To do Aus Aufgabe 12.1 übernehmen
    }

    private void insertionSort(int[] a, int l, int r) {
        for (int k = l + 1; k < r + 1; k++) {
            if (a[k] < a[k - 1]) {
                int x = a[k];
                int i;
                for (i = k; ((i > 0) && (a[i - 1] > x)); i--) {
                    a[i] = a[i - 1];
                }
                a[i] = x;
            }
        }
    }

    int[] randomData() {
        int[] a = new int[dataElems];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * DATARANGE);
        }
        return a;
    }

    public int[] ascendingData() {
        int[] a = new int[dataElems];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        return a;
    }

    public int[] descendingData() {
        int[] a = new int[dataElems];
        for (int i = 0; i < a.length; i++) {
            a[i] = a.length - i;
        }
        return a;
    }

    private void swap(int[] arr, int i, int j) {
        int swapElement;

        swapElement = arr[i];
        arr[i] = arr[j];
        arr[j] = swapElement;
    }

    private boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private double measureTime(Supplier<int[]> generator) throws Exception {
        double elapsed = 0;

        sourceArr = generator.get();

        long startTime = System.currentTimeMillis();
        long endTime = startTime;
        int count = 0;
        while (endTime < startTime + 10000) {
            System.arraycopy(sourceArr, 0, arr, 0, arr.length);
            Thread rootThread = new ParallelQuickerSortServer(arr, 0, arr.length - 1);
            rootThread.start();
            try {
                rootThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            endTime = System.currentTimeMillis();
        }
        elapsed = (double) (endTime - startTime) / count;

        if (!isSorted(arr)) throw new Exception("ERROR not eorted");
        return elapsed;
    }

    public String execute(String arg) {
        StringBuffer result = new StringBuffer();

        Map<String, Supplier<int[]>> generator =  new HashMap<>();
        generator.put("RANDOM", this::randomData);
        generator.put("ASC", this::ascendingData);
        generator.put("DESC", this::descendingData);

        String args[] = arg.toUpperCase().split(" ");
        dataElems = Integer.parseInt(args[1]);
        arr = new int[dataElems];
        sourceArr = new int[dataElems];
        insertion_threshold = Integer.parseInt(args[2]);
        try {
            try {
                double time = measureTime(generator.get(args[0]));
                return arg + " " + Double.toString(time) + " ms\n";
            } catch (Exception ex) {
                return arg + " " + ex.getMessage();
            }
        } catch (Exception ex) {
            return arg + " " + ex.getMessage();
        }
    }

    public static void main(String[] args) throws Exception {
        ParallelQuickerSortServer sorter = new ParallelQuickerSortServer();
        String sort;
        sort = "RANDOM 10000 100"; System.out.println(sorter.execute(sort));
        sort = "ASC 10000 100"; System.out.println(sorter.execute(sort));
        sort = "DESC 10000 100"; System.out.println(sorter.execute(sort));
    }

}