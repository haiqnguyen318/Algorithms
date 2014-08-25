
public class PercolationStats {
    private double[] openFraction;
    private int trials;
	
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        trials = T;
        openFraction = new double[T];
        int idx = 0;
        for (int k = 0; k < T; k++) {
            int openCt = 0;
            Percolation p = new Percolation(N);
            while (!p.percolates()) {
                int i = StdRandom.uniform(1, N+1);
                int j = StdRandom.uniform(1, N+1);
				
                if (p.isOpen(i, j)) continue;
                else {
                    p.open(i, j);
                    openCt++;
                }
            }
			
            openFraction[idx] = (double) openCt/(N*N);
            idx++;
        }
    }
	
    public double mean() {
        return StdStats.mean(openFraction);
    }
	
    public double stddev() {
        return StdStats.stddev(openFraction);
    }
	
    public double confidenceLo() {
		double mean = mean();
		double stdDev = stddev();
		double result = mean - (1.96*stdDev)/Math.sqrt(trials);
		return result;
	}
	
	public double confidenceHi() {
		double mean = mean();
		double stdDev = stddev();
		double result = mean + (1.96*stdDev)/Math.sqrt(trials);
		return result;
	}
	
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		PercolationStats ps = new PercolationStats(N, T);
		System.out.println("mean = "+ ps.mean());
		System.out.println("stddev = "+ ps.stddev());
		System.out.println("95% confidence interval ="+ ps.confidenceLo() + " " +ps.confidenceHi());
		
	}

}
