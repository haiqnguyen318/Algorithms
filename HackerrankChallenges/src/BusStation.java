
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BusStation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] groups = new int[N];
        int sum = 0;
        int biggestGroup = 0;
        for (int k = 0; k < N; k++) {
            int friends = in.nextInt();
            groups[k] = friends;
            sum += friends;
            if (friends > biggestGroup) biggestGroup = friends;
        }
        
        List<Integer> divisors = new LinkedList<Integer>();
        
        int n = 1;

        while (n <= sum) {
            if (sum % n == 0) divisors.add(new Integer(n));
            n++;
        }
        
        LinkedList<Integer> results = new LinkedList<Integer>();
        for (Integer i: divisors) {
            if (i < biggestGroup) continue;
            int aux = i;
            int index = 0;
            
            while (index < N) {
                if (aux < groups[index]) break;
                aux -= groups[index];
                if (aux == 0) aux = i;
                index++;
            }
            
            if (index == N) results.add(i);
        }
        StringBuilder output = new StringBuilder();
        for (Integer i: results) {
            if (results != null) output.append(i + " ");
        }
        System.out.println(output.toString());
        
        
    }

}
