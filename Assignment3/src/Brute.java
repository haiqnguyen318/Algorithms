
public class Brute {
    private static void test(Point[] points) {
        for (int a = 0; a < points.length; a++) {
            for (int b = 0; b < points.length; b++) {
                for (int c = 0; c < points.length; c++) {
                    for (int d = 0; d < points.length; d++) {
                        Point p, q, r, s;
                        p = points[a];
                        q = points[b];
                        r = points[c];
                        s = points[d];
                        double slope1 = p.slopeTo(q);
                        double slope2 = p.slopeTo(r);
                        double slope3 = p.slopeTo(s);
                        if (p.compareTo(q) < 0 && q.compareTo(r) < 0 && r.compareTo(s) < 0
                            && slope1 == slope2 && slope2 == slope3) {
                            print(p, q, r, s);
                            draw(p, q, r, s);
                        }
                    }
                }
            }
        }
    }
    
    private static void print(Point a, Point b, Point c, Point d) {
        String separator = " -> ";
        StdOut.println(a.toString() + separator + b.toString() + separator + c.toString() + separator + d.toString());
    }
    
    private static void draw(Point a, Point b, Point c, Point d) {
        a.draw();
        b.draw();
        c.draw();
        d.draw();
        a.drawTo(b);
        b.drawTo(c);
        c.drawTo(d);
        a.drawTo(d);
    }
    
    public static void main(String[] args) {

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);  // make the points a bit larger

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
