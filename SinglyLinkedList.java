import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
    
        // 0 or 1 node: nothing to swap
        if (head == null || head == tail) {
            return;
        }

        // 1. Record original order of values, and match value to its node
        List<E> originalVals = new ArrayList<>();
        Map<E, Node<E>> nodeOf = new HashMap<>();
        Node<E> cur = head;
        while (cur != null) {
            originalVals.add(cur.element);
            nodeOf.put(cur.element, cur);
            cur = cur.next;
        }
        int n = originalVals.size();

        // 2. Sort a copy of the values; map each value to its rank in sorted order
        List<E> sorted = new ArrayList<>(originalVals);
        Collections.sort(sorted);
        Map<E, Integer> rankOf = new HashMap<>();
        for (int k = 0; k < n; k++) {
            rankOf.put(sorted.get(k), k);
        }

        // 3. Build the new order: position i gets the node of its partner value
        List<Node<E>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            E v = originalVals.get(i);
            E partner = sorted.get(n - 1 - rankOf.get(v));
            result.add(nodeOf.get(partner));
        }

        // 4. Relink once, front to back
        for (int i = 0; i < n - 1; i++) {
            result.get(i).next = result.get(i + 1);
        }
        head = result.get(0);
        tail = result.get(n - 1);
        tail.next = null; // prevents a cycle

    }
   
}

