/*
 * Given an array A, PMEAN of the array is defined as the sum of A[i]*i.
 * The array can be rotated to the left such that the first element goes
 * to the end, other elements move 1 step to the left.
 * Find the biggest PMEAN possible of the array.
 * */
 
/* Solution:
 * As the array rotates, its new PMEAN = old PMEAN - sum of elements
 *                                      + n times first element (as it moves to the end)
 */
import java.util.Scanner;

public class RotationGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        long[] array = new long[N];
        long sum = 0;
        long pmean = 0;
        for (int k = 0; k < N; k++) {
            long cur = in.nextLong();
            sum += cur;
            pmean += (k+1)*cur;
            array[k] = cur;
        }
        
        long max = pmean;
        for (int k = 0; k < N; k++) {
            pmean = pmean - sum + array[k]*N;
            if (pmean > max) max = pmean;
        }
        
        System.out.println(max);
        
    }
}