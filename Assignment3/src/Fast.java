import java.util.Arrays;


public class Fast {
    private static  void test(Point[] points) {
        Point[] aux = new Point[points.length];
        
        for (int j = 0; j < points.length; j++) {
            aux[j] = points[j];
        }
        
        for (int j = 0; j < points.length; j++) {
            Point p = points[j];
            
            //2 sorts to get rid of permutations
            Arrays.sort(aux);
            Arrays.sort(aux, p.SLOPE_ORDER);
            
            boolean valid = false;
            String separator = " -> ";
            Point start = p, end;
            
            for (int k = 1; k < aux.length; k++) {
                if (k < aux.length - 2) {
                    if (p.compareTo(aux[k]) < 0
                        && p.slopeTo(aux[k]) != p.slopeTo(aux[k-1])
                        && p.slopeTo(aux[k]) == p.slopeTo(aux[k+1])
                        && p.slopeTo(aux[k+1]) == p.slopeTo(aux[k+2])) {
                        valid = true;
                        StdOut.print(start.toString());
                        StdOut.print(separator + aux[k].toString());
                        start.draw();
                        aux[k].draw();
                    }
                    else if (valid
                            && p.slopeTo(aux[k]) != p.slopeTo(aux[k+1])) {
                        end = aux[k];
                        StdOut.println(separator + end.toString());
                        end.draw();
                        start.drawTo(end);
                        valid = false;
                    }
                    else if (valid) {
                        StdOut.print(separator + aux[k].toString());
                        aux[k].draw();
                    }
                }
                else if (valid && k == aux.length - 2) {
                    if (p.slopeTo(aux[k]) == p.slopeTo(aux[k+1])) {
                        StdOut.print(separator + aux[k].toString());
                        aux[k].draw();
                    }
                    else {
                        end = aux[k];
                        StdOut.println(separator + aux[k].toString());
                        end.draw();
                        start.drawTo(end);
                        valid = false;
                    }
                }
                else if (valid && k == aux.length - 1) {
                    end = aux[k];
                    StdOut.println(separator + aux[k].toString());
                    end.draw();
                    start.drawTo(end);
                    valid = false; 
                }
            }    
        }
    }
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p; 
        }
        test(points);
        
        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();

    }

}
