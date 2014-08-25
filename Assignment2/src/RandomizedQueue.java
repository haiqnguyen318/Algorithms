import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;
    private final int DEFAULT_LENGTH = 1;
    public RandomizedQueue() {
        queue = (Item[]) new Object[DEFAULT_LENGTH];
        size = 0;
    }
    private void expand() {
        Item[] newqueue = (Item[]) new Object[queue.length*2];
        for (int j = 0; j < queue.length; j++) {
            newqueue[j] = queue[j];
        }
        queue = newqueue;
    }
    private void compress() {
        Item[] newqueue = (Item[]) new Object[queue.length/2];
        for (int j = 0; j < queue.length/2; j++) {
            newqueue[j] = queue[j];
        }
        queue = newqueue;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        queue[size] = item;
        size++;
        if (size == queue.length)
            expand();
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(size);
        if (random == size-1) {
            Item result = queue[random];
            queue[random] = null;
            size--;
            if (size <= queue.length/4) 
                compress();
            return result;
        }
        Item lastItem = queue[size-1];
        Item result = queue[random];
        queue[random] = lastItem;
        queue[size-1] = null;
        size--;
        if (size <= queue.length/4)
            compress();
        return result;
    }
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomIndex = StdRandom.uniform(size);
        return queue[randomIndex];
    }
    
    private class QueueIterator<Item> implements Iterator<Item> {
        private Item[] array;
        private int size;
        public QueueIterator(Item[] array, int size) {
            this.array = array;
            this.size = size;
        }
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return size > 0;
        }
        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            // TODO Auto-generated method stub
            int random = StdRandom.uniform(size);
            Item result = array[random];
            if (random == size-1) {                
                array[random] = null;
                size--;
                return result;
            }
            Item lastItem = array[size - 1];
            array[random] = lastItem;
            array[size-1] = null;
            size--;
            return result;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public Iterator<Item> iterator() {
        return new QueueIterator(queue, size());
    }
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("hai");
        queue.enqueue("nguyen");
        queue.enqueue("freddy");
        queue.enqueue("me");
        queue.enqueue("bo");
        queue.enqueue("chi");
        Iterator<String> it = queue.iterator();
        System.out.println(queue.sample());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        
    }
}