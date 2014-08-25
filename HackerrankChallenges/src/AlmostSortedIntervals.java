import java.util.Scanner;

public class AlmostSortedIntervals {
    private static int count(int[] ar) {
        int length = ar.length;
        int ct = length;
        int currentIntervalLength = 1;
        for (int k = 1; k < ar.length; k++) {
            int current = ar[k];
            if (current > ar[k - 1]) {
                ct += currentIntervalLength;
                currentIntervalLength++;
            }
            else {
                currentIntervalLength = 1;
            }
        }
        return ct;
    }
    
    public static void main(String[] args) {
        In in = new In("input04.txt");
        int N = in.readInt();
        int[] ar = new int[N];
        for (int k = 0; k < N; k++) {
            ar[k] = in.readInt();
        }
        System.out.println(count(ar));
        
    }
}