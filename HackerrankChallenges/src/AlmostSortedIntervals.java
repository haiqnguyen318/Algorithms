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
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] ar = new int[N];
        for (int k = 0; k < N; k++) {
            ar[k] = in.nextInt();
        }
        System.out.println(count(ar));
        
    }
}