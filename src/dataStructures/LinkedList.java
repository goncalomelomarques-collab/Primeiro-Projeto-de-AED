package dataStructures;

import java.io.*;

/**
 * Linked List
 * Includes description of general methods to be implemented by linked lists.
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
abstract class LinkedList<E> implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    /**
     *  Node at the head of the list.
     */
    transient LinkedNode<E> head;
    /**
     * Node at the tail of the list.
     */
    transient LinkedNode<E> tail;
    /**
     * Number of elements in the list.
     */
    transient int currentSize;
    /**
     * Constructor of an empty singly linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public LinkedList(){
        head=null;
        tail=null;
        currentSize=0;
    }
    /**
     * Returns true iff the list contains no elements.
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        boolean empty = (currentSize == 0);
        return empty;
    }
    /**
     * Returns the number of elements in the list.
     * @return number of elements in the list
     */
    public int size() {
        return currentSize;
    }

    /**
     * Returns an iterator of the elements in the list (in a proper sequence).
     * @return Iterator of the elements in the list
     */
    public Iterator<E> iterator() {
        return new LinkedIterator<>(head);
    }

    /**
     * Insert a node on the head of list
     * @param newNode
     */
    void addFirstNode(LinkedNode<E> newNode){
        if(currentSize == 0){
            head = newNode;
            tail = newNode;
        }
        else{
            newNode.setNext(head);
            head = newNode;}
        currentSize++;
    }
    /**
     * Insert a node on the tail of list
     * @param newNode
     */
    void addLastNode(LinkedNode<E> newNode){
        if(currentSize == 0){
            head = newNode;
            tail = newNode;
        }
        else{
            tail = newNode;
            tail.setNext(newNode);}
        currentSize++;
    }
    /**
     * Record with two nodes (prev, node)
     * @param prev
     * @param node
     */
    record pairNode<E>(LinkedNode<E> prev, LinkedNode<E> node){}

    /**
     * Insert node (newNode) between pair.previous() and pair.node()
     * @pre: pair.previous()!=null && pair.node()!=null
     * @param newNode
     */
    void addMiddleNode(pairNode<E> pair,LinkedNode<E> newNode) {
        // 1. O próximo do novo nó passa a ser o nó atual do par
        newNode.setNext(pair.node()); // ou newNode.next = pair.node(); dependendo da sua classe LinkedNode
        // 2. O próximo do nó anterior passa a ser o novo nó, inserindo-o no meio
        pair.prev().setNext(newNode); // ou pair.previous().next = newNode;
        // 3. Incrementa o tamanho da lista
        currentSize++;
    }
    /**
     * Removes the first node in the list.
     * @pre: !isEmpty()
     * @return
     */
    E removeFirstNode(){
        E removedElement = head.getElement();
        head = head.getNext();
        if(head == null){tail = null;}
        currentSize--;
	    return removedElement;
    }

    /**
     * remove the last node (pair.node()) of the list
     * @return
     */
    E removeLastNode(pairNode<E> pair){
	    E removedElement = tail.getElement();
        tail = pair.prev();
        if(tail == null){head = null;}
        else{ tail.setNext(null);}
        currentSize--;
        return removedElement;
    }
    /**
     * remove the node pair.node()
     @pre: pair.previous()!=null && pair.node()!=null
     * @param pair
     */
    void removeMiddleNode(pairNode<E> pair) {
        if(pair.node() == tail){
            tail = pair.prev();
        }
        pair.prev().setNext(pair.node().getNext());
        currentSize--;
    }

    /**
     *
     * @param element
     * @return pair with the previous node and the element node, Or null if no element
     */
    pairNode<E>  nodeOf(E element){
        boolean found = false;
        LinkedNode<E> currentNode = head;
        LinkedNode<E> previousNode = null;

        while(currentNode != null && !found) {
            if (currentNode.getElement().equals(element)) {
                found = true;
            }else{
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
        }

        if(!found){return null;}
        else{
            return new pairNode<>(previousNode, currentNode);}
    }

    LinkedNode<E> getFirstNode(){
        return head;
    }

    LinkedNode<E> getLastNode(){
        return tail;
    }
    

}
