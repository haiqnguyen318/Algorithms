import java.util.Scanner;

public class Median {
    private static int median(int[] ar, int low, int high) {
        if (high - low < 1) return ar[low];
        int partitionIdx = partition(ar, low, high);
        if (partitionIdx == ar.length/2) return partitionIdx;
        else if (partitionIdx < ar.length/2) return median(ar, partitionIdx + 1, high);
        else return median(ar, low, partitionIdx - 1);
    }
    private static int partition(int[] ar, int lo, int high) {
        int curIndex = lo;
        int separation = lo;
        while (curIndex <= high) {
            if (ar[curIndex] < ar[lo]) {
                swap(ar, separation, curIndex);
                separation++;
            }
            curIndex++;
        }
        swap(ar, lo, separation);
        return separation;
    }
    private static void swap(int[] ar, int idx1, int idx2) {
        int tmp = ar[idx1];
        ar[idx1] = ar[idx2];
        ar[idx2] = tmp;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] ar = new int[N];
        for (int k = 0; k < N; k++) {
            ar[k] = in.nextInt();
        }
        System.out.println(median(ar, 0, N - 1));
    }
}