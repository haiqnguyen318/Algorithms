
import java.io.*;
import java.util.*;

public class ProjectEuler_problem1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            long N = in.nextLong();
            long multiple3 = (N-1)/3;
            long multiple5 = (N-1)/5;
            long multiple15 = (N-1)/15;
            long sum3 = multiple3*(2*3 + (multiple3 - 1)*3)/2;
            long sum5 = multiple5*(2*5 + (multiple5 - 1)*5)/2;
            long sum15 = multiple15*(2*15 + (multiple15 - 1)*15)/2;
            long sum = sum5 + sum3 - sum15;
            System.out.println(sum);
        }
    }
}