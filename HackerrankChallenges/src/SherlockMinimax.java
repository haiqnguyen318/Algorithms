import java.util.Scanner;


public class SherlockMinimax {
    private static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }
    private static void sort(int[] array, int low, int high) {
        if (low == high) return;
        int mid = low + (high - low)/2;
        sort(array, low, mid);
        sort(array, mid + 1, high);
        merge(array, low, mid, high);
    }
    
    private static void merge(int[] array, int low, int mid, int high) {
        int[] aux = new int[array.length];
        int left = low;
        int right = mid + 1;
        
        for (int k = low; k <= high; k++) {
            if (left > mid) aux[k] = array[right++];
            else if (right > high) aux[k] = array[left++];
            else if (array[left] > array[right]) aux[k] = array[right++];
            else aux[k] = array[left++];
        }
        
        for (int k = low; k <= high; k++) {
            array[k] = aux[k];
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] array = new int[N];
        
        for (int k = 0; k < N; k++) {
            array[k] = in.nextInt();
        }
        
        int left = in.nextInt();
        int right = in.nextInt();
        
        sort(array);
        int startIndex = -1;
        int endIndex = -1;
        for (int k = 0; k < N; k++) {
            if (array[k] >= left) {
                startIndex = k;
                break;
            }
        }
        
        for (int k = array.length - 1; k >= 0; k--) {
            if (array[k] <= right) {
                endIndex = k;
                break;
            }
        }
        int maxMin = 0;
        int output = -1;
        
        if (endIndex < array.length - 1) {
            int mid = array[endIndex] + (array[endIndex + 1] - array[endIndex])/2;
            if (mid > right) {
                maxMin = right - array[endIndex];
                output = right;
            }
            else if (mid <= right) {
                maxMin = mid - array[endIndex];
                output = mid;
            }
            
        }
        else {
            maxMin = right - array[endIndex];
            output = right;
        }
        
        for (int k = startIndex; k < endIndex; k++) {
            int min = (array[k + 1] - array[k])/2;
            if (min >= maxMin) {
                maxMin = min;
                output = array[k] + min;
            }
        }
        
        if (startIndex > 0) {
            int mid = array[startIndex - 1] + (array[startIndex] - array[startIndex - 1])/2;
            int min;
            if (mid < left) {
                min = array[startIndex] - left;
                if (min >= maxMin) {
                    maxMin = min;
                    output = left;
                }
            }
            else {
                min = array[startIndex] - mid;
                if (min >= maxMin) {
                    maxMin = min;
                    output = mid;
                }
            } 
        }
        else {
            int min = array[startIndex] - left;
            if (min >= maxMin) {
                output = left;
            }
        }
        
        System.out.println(output);
        
    }

}
