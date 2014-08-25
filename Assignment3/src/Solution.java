import java.util.Scanner;

public class Solution {
    private static void sort(Pair[] pairs, int low, int high) {
        if (low == high) return;
        int mid = low + (high - low) / 2;
        sort(pairs, low, mid);
        sort(pairs, mid + 1, high);
        merge(pairs, low, mid + 1, high);
    }
    
    private static void merge(Pair[] pairs, int low, int mid, int high) {
        int length = pairs.length;
        Pair[] aux = new Pair[length];
        copy(pairs, aux);
        
        int left = low;
        int right = mid;
        
        for (int index = low; index <= high; index++) {
            if (left >= mid) aux[index] = pairs[right++];
            else if (right > high) aux[index] = pairs[left++];
            else if (pairs[left].compareTo(pairs[right]) > 0)
                aux[index] = pairs[right++];
            else aux[index] = pairs[left++];
        }
        copy(aux, pairs);
    }
    
    private static void copy(Pair[] copied, Pair[] copy) {
        if (copied.length != copy.length) throw new IllegalArgumentException(); 
        for (int j = 0; j < copied.length; j++) {
            copy[j] = copied[j];
        }
    }
    private static class Pair implements Comparable<Pair> {
        private int key;
        private String value;
        
        private Pair(int k, String v) {
            key = k;
            value = v;
        }
        
        public int compareTo(Pair that) {
            if (this.key > that.key) return 1;
            else if (this.key == that.key) return 0;
            else return -1;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Pair[] array = new Pair[N];
        
        for (int j = 0; j < N; j++) {
            int key = in.nextInt();
            String value = in.next();
            if (j < N/2) {
                array[j] = new Pair(key, "-");
            }
            else array[j] = new Pair(key,value);
        }
        
        sort(array, 0, array.length - 1);
        String result = "";
        for (int j = 0; j < array.length; j++) {
            result = result + array[j].value + " ";
        }
        System.out.println(result);
    }
}