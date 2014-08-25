
public class Solver {
    private static int moves;
    private Board board;
    private Board twin;
    private MinPQ<Node> queue;
    private MinPQ<Node> twinQueue;
    private Queue<Board> solution;
    public Solver(Board initial) {
        moves = -1;
        board = initial;
        twin = board.twin();
        queue = new MinPQ<Node>();
        twinQueue = new MinPQ<Node>();
        solution = new Queue<Board>();
        queue.insert(new Node(board));
        twinQueue.insert(new Node(twin));       
        
        while (!board.isGoal() && !twin.isGoal()) {
            board = queue.delMin().board;
            solution.enqueue(board);
            Iterable<Board> neighbors = board.neighbors();
            queue = new MinPQ<Node>();
            for (Board b: neighbors) {
                if (b != null) queue.insert(new Node(b));
            }
            
            twin = twinQueue.delMin().board;
            Iterable<Board> twNeighbors = twin.neighbors();
            twinQueue = new MinPQ<Node>();
            for (Board b: twNeighbors) {
                if (b != null) twinQueue.insert(new Node(b));
            }
            moves++;
        }
    }
    
    public boolean isSolvable() {
        return board.isGoal();
    }
    
    public int moves() {
        if (!isSolvable()) return -1;
        return moves;
    }
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        return solution;
    }
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
    
    private static class Node implements Comparable<Node>{
        private Board board;
        private int move;
        private Node(Board b) {
            this.board = b;
            this.move = moves;
        }
        public int compareTo(Node that) {
            if (board.manhattan() + move > that.board.manhattan() + that.move) return 1;
            if (board.manhattan() + move == that.board.manhattan() + that.move) return -1;
            else return 0;
        }
    }

}
