
public class Subset {

    public static void main(String[] args) {
        int amt = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        String input;
        while (!StdIn.isEmpty()) {
            input = StdIn.readString();
            rq.enqueue(input);
        }
        for (int j = 0; j < amt; j++) {
            StdOut.println(rq.dequeue());
        }
    }

}
