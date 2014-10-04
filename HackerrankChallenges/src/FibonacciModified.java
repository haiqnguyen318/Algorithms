import java.math.BigInteger;
import java.util.Scanner;


public class FibonacciModified {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    BigInteger a = new BigInteger(in.next());
    BigInteger b = new BigInteger(in.next());
    int n = in.nextInt();
    BigInteger current = new BigInteger("-1");
    for (int j = 3; j <= n; j++) {
      current = b.multiply(b).add(a);
      a = b;
      b = current;
    }
    System.out.println(current);
  }

}
