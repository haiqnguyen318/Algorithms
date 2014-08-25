import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item value;
        private Node next;
        private Node prev;
        private Node(Item item) {
            value = item;
            next = null;
            prev = null;
        }
        private Item getValue() {
            return value;
        }
        private Node getNext() {
            return next;
        }
        private void setValue(Item item) {
            value = item;
        }
        private void setNext(Node n) {
            next = n;
        }
        private Node getPrev() {
            return prev;
        }
        private void setPrev(Node n) {
            prev = n;
        }
    }
    private class DequeIterator implements Iterator {
        private Node next;
        private DequeIterator(Node first) {
            next = first;
        }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item result = next.getValue();
            next = next.getNext();
            return result;
        }
        
        public boolean hasNext() {
            return next != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    private Node first;
    private Node last;
    private int size;
    
    public Deque() {
        first = null; 
        last = null;
        size = 0;
    }
    
    public boolean isEmpty() {
        return size == 0; 
    }
    
    public int size() {
        return size;
    }
    
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node newFirst = new Node(item);
        if (size == 0) {
            first = newFirst;
            size++;
            return;
        }
        newFirst.setNext(first);
        first.setPrev(newFirst);
        if (size == 1) 
            last = first;
        first = newFirst;
        size++;
    }
    
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node newLast = new Node(item);
        if (size == 0) {
            first = newLast;
            size++;
            return;
        }
        else if (size == 1) {
            last = newLast;
            first.setNext(last);
            last.setPrev(first);
            size++;
        }
        last.setNext(newLast);
        newLast.setPrev(last);
        last = newLast;
        size++;
    }
    
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item result = first.getValue();
        if (size == 1) {
            first = null;
            size--;
            return result;
        }
        Node newFirst = first.getNext();
        newFirst.setPrev(null);
        first = newFirst;
        size--;
        return result;
    }
    
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item result = last.getValue();
        if (size == 1) {
            last = null;
            size--;
            return result;
        }
        Node newLast = last.getPrev();
        newLast.setNext(null);
        last = newLast;
        size--;
        return result;
    }
    @Override
    public Iterator<Item> iterator() {
        Iterator<Item> it = new DequeIterator(first);
        return it;
    }
    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        dq.addFirst("Hai");
        dq.addFirst("freddy");
        dq.addLast("Nguyen");
        Iterator<String> it = dq.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        
    }
}