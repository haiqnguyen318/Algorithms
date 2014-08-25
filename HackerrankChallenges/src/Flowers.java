/* Sample program illustrating input/output methods */
import java.util.*;

public class Flowers {
    private static int calculateExpense(int[] prices, int people) {
        if (prices.length <= people) {
            int sum = 0;
            for (int k = 0; k < prices.length; k++) {
                sum += prices[k];
            }
            return sum;
        }
        Arrays.sort(prices);
        int[] aux = new int[prices.length];
        int left = 0;
        int right = prices.length - 1;
        while (left < prices.length) {
            aux[left] = prices[right];
            left++;
            right--;
        }
        int[] spending = new int[people];
        int[] qty = new int[people];
        int idx = 0;
        for (int n = 0; n < aux.length; n++) {
            spending[idx] += (qty[idx] + 1)*aux[n];
            qty[idx]++;
            idx++;
            if (idx == people) idx = 0;
        }
        int result = 0;
        for (int k = 0; k < spending.length; k++) {
            result += spending[k];
        }
        return result;
        
    }
    
    private static void swap(int[] ar, int x, int y) {
        int tmp = ar[x];
        ar[x] = ar[y];
        ar[y] = tmp;
    }
    
   public static void main( String args[] ){
      
// helpers for input/output      

      Scanner in = new Scanner(System.in);
      
      int N, K;
      N = in.nextInt();
      K = in.nextInt();
      
      int C[] = new int[N];
      for(int i=0; i<N; i++){
         C[i] = in.nextInt();
      }
      
      int result = calculateExpense(C, K);
      System.out.println( result );
      
   }
}
