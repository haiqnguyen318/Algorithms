
public final class Board {
    private final int dimension;
    private final int[][] board;
    private Board previous;
    private Queue<Board> neighbors;
    
    public Board(int[][] blocks) {
        board = blocks;
        dimension = blocks.length;
        neighbors = new Queue<Board>();
    }   
    public int dimension() {
        return dimension;
    }
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] != 0
                 && board[i][j] != i * dimension + j + 1) hamming++;
            }
        }
        return hamming;
    }
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int block = board[i][j];
                if (block == 0) continue;
                int correctRow;
                if (block % dimension == 0) correctRow = block / dimension - 1;
                else correctRow = block / dimension;
                int correctCol;
                if (block % dimension == 0) correctCol = dimension - 1;
                else correctCol = block % dimension - 1;
                manhattan += (Math.abs(i - correctRow) + Math.abs(j - correctCol));
            }
        }
        return manhattan;
    }
    public boolean isGoal() {
        return hamming() == 0;
    }
    public Board twin() {
        int[][] newBoard = new int[dimension][dimension];
        copy(board, newBoard);
        int[] zero = findZero();
        int row = zero[0];
        while (row == zero[0]) {
            row = StdRandom.uniform(dimension);
        }
        int col1 = StdRandom.uniform(dimension);        
        int col2;
        if (col1 > 0) col2 = col1 - 1;
        else col2 = col1 + 1;
        int temp = newBoard[row][col1];
        newBoard[row][col1] = newBoard[row][col2];
        newBoard[row][col2] = temp;
        return new Board(newBoard);
    }
    public boolean equals(Object y) { 
        if (y instanceof Board) {
            Board that = (Board) y;
            if (dimension() != that.dimension()) return false;
            int[][] thisBoard = board;
            int[][] thatBoard = that.board;
            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    if (thisBoard[i][j] != thatBoard[i][j]) return false;
                }
            }
            return true;
        }
        return false;
    }
    public Iterable<Board> neighbors() {
        int[] blankPos = findZero();
        int blankRow = blankPos[0];
        int blankCol = blankPos[1];
        
        int[][] neighbor1 = new int[dimension][dimension];
        int[][] neighbor2 = new int[dimension][dimension];
        int[][] neighbor3 = new int[dimension][dimension];
        int[][] neighbor4 = new int[dimension][dimension];
        
        if (blankRow == 0) {
            copy(board, neighbor4);
            swap(neighbor4, blankRow, blankCol, blankRow + 1, blankCol);
            Board neighborBoard4 = new Board(neighbor4);
            addNeighbor(neighborBoard4);
            neighborBoard4.setPrevious(this);
            if (blankCol > 0) {
                copy(board, neighbor2);
                swap(neighbor2, blankRow, blankCol, blankRow, blankCol - 1);
                Board neighborBoard2 = new Board(neighbor2);
                addNeighbor(neighborBoard2);
                neighborBoard2.setPrevious(this);
            }
            if (blankCol < dimension() - 1) {
                copy(board, neighbor3);
                swap(neighbor3, blankRow, blankCol, blankRow, blankCol + 1);
                Board neighborBoard3 = new Board(neighbor3);
                addNeighbor(neighborBoard3);
                neighborBoard3.setPrevious(this);
            }
        }
        
        else if (blankRow == dimension() - 1) {
            copy(board, neighbor1);
            swap(neighbor1, blankRow, blankCol, blankRow - 1, blankCol);
            Board neighborBoard1 = new Board(neighbor1);
            addNeighbor(neighborBoard1);
            neighborBoard1.setPrevious(this);
            if (blankCol > 0) {
                copy(board, neighbor2);
                swap(neighbor2, blankRow, blankCol, blankRow, blankCol - 1);
                Board neighborBoard2 = new Board(neighbor2);
                addNeighbor(neighborBoard2);
                neighborBoard2.setPrevious(this);
            }
            if (blankCol < dimension() - 1) {
                copy(board, neighbor3);
                swap(neighbor3, blankRow, blankCol, blankRow, blankCol + 1);
                Board neighborBoard3 = new Board(neighbor3);
                addNeighbor(neighborBoard3);
                neighborBoard3.setPrevious(this);
            }
        }
        
        else {
            copy(board, neighbor1);
            swap(neighbor1, blankRow, blankCol, blankRow - 1, blankCol);
            Board neighborBoard1 = new Board(neighbor1);
            addNeighbor(neighborBoard1);
            neighborBoard1.setPrevious(this);
            
            copy(board, neighbor4);
            swap(neighbor4, blankRow, blankCol, blankRow + 1, blankCol);
            Board neighborBoard4 = new Board(neighbor4);
            addNeighbor(neighborBoard4);
            neighborBoard4.setPrevious(this);
            
            if (blankCol > 0) {
                copy(board, neighbor2);
                swap(neighbor2, blankRow, blankCol, blankRow, blankCol - 1);
                Board neighborBoard2 = new Board(neighbor2);
                addNeighbor(neighborBoard2);
                neighborBoard2.setPrevious(this);
            }
            if (blankCol < dimension() - 1) {
                copy(board, neighbor3);
                swap(neighbor3, blankRow, blankCol, blankRow, blankCol + 1);
                Board neighborBoard3 = new Board(neighbor3);
                addNeighbor(neighborBoard3);
                neighborBoard3.setPrevious(this);
            }
        }
        return neighbors;
        
    }
    
    public String toString() {
        String s = dimension + "\n";
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (j == dimension - 1) s = s + " " + board[i][j] + "\n";
                else s = s + " " + board[i][j];
            }
        }
        return s;
    }
    private int[] findZero() {
        int x = 0;
        int y = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (board[i][j] == 0) {
                    x = i; 
                    y = j;
                }
            }
        }
        int[] result = {x, y};
        return result;
    }
    private void copy(int[][] copied, int[][] copy) {
        for (int i = 0; i < copied.length; i++) {
            for (int j = 0; j < copied.length; j++) {
                copy[i][j] = copied[i][j];
            }
        }
    }
    private void swap(int[][] board, int r1, int c1, int r2, int c2) {
        int tmp = board[r1][c1];
        board[r1][c1] = board[r2][c2];
        board[r2][c2] = tmp;
    }
    private void setPrevious(Board that) {
        previous = that;
    }
    private void addNeighbor(Board b) {
        if (!b.equals(previous)) neighbors.enqueue(b);
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = in.readInt();
            }
        }
        Board b = new Board(board);
        StdOut.println(b.manhattan());
    }
}
