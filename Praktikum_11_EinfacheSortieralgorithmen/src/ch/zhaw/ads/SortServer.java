package ch.zhaw.ads;

import java.awt.font.FontRenderContext;
import java.util.*;
import java.util.function.*;

public class SortServer implements CommandExecutor {
    private final int DATARANGE = 10000000;
    public int dataElems;

    public void swap(int[] a, int i, int j) {
        int h = a[i];
        a[i] = a[j];
        a[j] = h;
    }


    public void bubbleSort(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            boolean swap = true;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swap = false;
                }
            }
            if (swap) break;
        }

    }


    public void insertionSort(int[] a) {
        for (int j = 0; j < a.length; j++) {
            int x = a[j];
            int i;
            for (i = j; ((i > 0) && a[i - 1] > x); i--) {
                a[i] = a[i - 1];
            }
            a[i] = x;
        }
    }


    public void selectionSort(int[] a) {
        for (int j = 0; j < a.length; j++) {
            int min = j;
            for (int i = j + 1; i < a.length; i++) {
                if (a[i] < a[min]) min = i;
            }
            if (min != j) swap(a, min, j);
        }
    }


    public void streamSort(int[] a) {
        int[] b = Arrays.stream(a).sorted().toArray();
        System.arraycopy(b, 0, a, 0, a.length);
    }


    public boolean isSorted(int[] a) {
        int[] copy = Arrays.copyOf(a, a.length);
        Arrays.sort(copy);
        return Arrays.equals(copy, a);
    }


    public int[] randomData() {
        int[] a = new int[dataElems];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(DATARANGE);
        }
        return a;
    }

    public int[] ascendingData() {
        int[] a = randomData();
        Arrays.sort(a);
        return a;
    }

    public int[] descendingData() {
        int[] ascending = ascendingData();
        int[] descending = new int[ascending.length];
        for (int i = 0; i < ascending.length; i++) {
            descending[ascending.length - (i + 1)] = ascending[i];
        }

        return descending;
    }

    public double measureTime(Supplier<int[]> generator, Consumer<int[]> sorter) throws Exception {
        double elapsed;

        int[] a = generator.get();
        int[] b = new int[dataElems];

        long startTime = System.currentTimeMillis();
        long endTime = startTime;
        int count = 0;

        while (endTime < startTime + 1000) {
            b = Arrays.copyOf(a, dataElems);
            sorter.accept(b);
            count++;
            endTime = System.currentTimeMillis();
        }

        elapsed = (double) (endTime - startTime) / count;
        if (!isSorted(b)) throw new Exception("ERROR not sorted");
        System.out.println(count);
        return elapsed;
    }

    public String execute(String arg) {
        Map<String, Consumer<int[]>> sorter = new HashMap<>();
        sorter.put("BUBBLE", this::bubbleSort);
        sorter.put("INSERTION", this::insertionSort);
        sorter.put("SELECTION", this::selectionSort);
        sorter.put("STREAM", this::streamSort);

        Map<String, Supplier<int[]>> generator = new HashMap<>();
        generator.put("RANDOM", this::randomData);
        generator.put("ASC", this::ascendingData);
        generator.put("DESC", this::descendingData);

        String args[] = arg.toUpperCase().split(" ");
        dataElems = Integer.parseInt(args[2]);
        try {
            double time = measureTime(generator.get(args[1]), sorter.get(args[0]));
            return arg + " " + time + " ms";
        } catch (Exception ex) {
            return arg + " " + ex.getMessage();
        }
    }

    public static void main(String[] args) {
        SortServer sorter = new SortServer();
        String sort;
        sort = "BUBBLE RANDOM 10000";
        System.out.println(sorter.execute(sort));
        sort = "SELECTION RANDOM 10000";
        System.out.println(sorter.execute(sort));
        sort = "INSERTION RANDOM 10000";
        System.out.println(sorter.execute(sort));

        sort = "BUBBLE ASC 10000";
        System.out.println(sorter.execute(sort));
        sort = "SELECTION ASC 10000";
        System.out.println(sorter.execute(sort));
        sort = "INSERTION ASC 10000";
        System.out.println(sorter.execute(sort));
    }
}