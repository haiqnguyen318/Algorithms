
public class Percolation {
    private int size;
    private final int top;
    private final int bottom;
    private	WeightedQuickUnionUF uf;
    private boolean[] open;
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        size = n;
        //top is the upper left most index, represents top row
        top = 0;
        
        //bottom is the lower right most index, represents bottom row
        bottom = n*n+1;
        
        uf = new WeightedQuickUnionUF(n*n+2);
        open = new boolean[n*n+2];
        open[top] = true;
        open[bottom] = true;
        
        for (int j = 1; j <= size; j++) {
            uf.union(top, j);
        }
        
        for (int j = bottom-size; j < bottom; j++) {
            uf.union(bottom, j);
        }
    }
    //buggy
    public void open(int i, int j) {
        if (i <= 0 || i > size || j <= 0 || j > size) throw new IndexOutOfBoundsException();
        int idx = (i-1)*size+j;
        open[idx] = true;
        
		boolean hasAbove = true, hasBelow = true, hasLeft = true, hasRight = true;
		if (i < 2) hasAbove = false;
		if (i > size-1) hasBelow = false;
		if (j < 2) hasLeft = false;
		if (j > size-1) hasRight = false;
		
		int aboveIdx = idx-size;
		int belowIdx = idx+size;
		int leftIdx = idx-1;
		int rightIdx = idx+1;
		
		if (hasAbove) {
			if (open[aboveIdx])
				uf.union(idx, aboveIdx);
		}
		if (hasBelow) {
			if (open[belowIdx])
				uf.union(idx, belowIdx);
		}
		if (hasLeft) {
			if (open[leftIdx])
				uf.union(idx, leftIdx);
		}
		if (hasRight) {
			if (open[rightIdx])
				uf.union(idx, rightIdx);
		}
		
		
	}
	
	public boolean isOpen(int i, int j) {
	    if (i <= 0 || i > size || j <= 0 || j > size) throw new IndexOutOfBoundsException();
		return open[(i-1)*size+j];
	}
	
	public boolean isFull(int i, int j) {
	    if (i <= 0 || i > size || j <= 0 || j > size) throw new IndexOutOfBoundsException();
		return isOpen(i, j) && uf.connected((i-1)*size+j, top);
	}
	
	public boolean percolates() {
		return size == 1 ? isOpen(1, 1) : uf.connected(bottom, top);
	}
	
	
}
