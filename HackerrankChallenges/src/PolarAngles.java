import java.util.Arrays;
import java.util.Scanner;


public class PolarAngles {
  private static class Point implements Comparable<Point> {
    private int x;
    private int y;
    private int quarter;
    private double cos;
    private double length;
    
    public Point(int X, int Y) {
      x = X;
      y = Y;
      cos = x/Math.sqrt(x*x + y*y);
      length = x*x +y*y;
      if (x > 0) {
        if (y >= 0) quarter = 1;
        else quarter = 4;
      }
      else if (x == 0) {
        if (y > 0) quarter = 2;
        else quarter = 4;
      }
      else {
        if (y <= 0) quarter = 3;
        else quarter = 2;
      }
    }
    
    public double cos() {
      return cos;
    }
    public double length() {
      return length;
    }
    public int quarter() {
      return quarter;
    }
    public int compareTo(Point that) {
      if (this.quarter < that.quarter) return -1;
      else if (this.quarter > that.quarter) return 1;
      else {
        if (quarter == 1 || quarter == 2) {
          if (this.cos() < that.cos()) return 1;
          else if (this.cos() > that.cos()) return -1;
          else return this.length() < that.length ? -1 : 1;
        }
        else if (quarter == 3 || quarter == 4) {
          if (this.cos() < that.cos()) return -1;
          else if (this.cos() > that.cos()) return 1;
          else return this.length() < that.length ? -1 : 1;
        }
      }
      return 0;
    }
  }
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    Point[] points = new Point[N];
    
    for (int k = 0; k < N; k++) {
      int x = in.nextInt();
      int y = in.nextInt();
      Point p = new Point(x,y);
      points[k] = p;
    }
    
    Arrays.sort(points);
    StringBuilder sb = new StringBuilder();
    
    for (int k = 0; k < N; k++) {
      sb.append(points[k].x + " " + points[k].y + "\n");
    }
    System.out.println(sb.toString());
  }

}
