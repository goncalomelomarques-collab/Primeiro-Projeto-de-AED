package dataStructures;

/**
 * Implementation of Singly Linked List
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
public class SinglyLinkedList<E> extends SequenceLinkedList<E> {

    public SinglyLinkedList( ) {
        super();
    }

    /**
     * Inserts the specified element at the first position in the list.
     *
     * @param element to be inserted
     */
    @Override
    public void addFirst(E element) {
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        addFirstNode(newNode);
    }

    /**
     * Inserts the specified element at the last position in the list.
     *
     * @param element to be inserted
     */
    @Override
    public void addLast(E element) {
       LinkedNode<E> newNode = new SinglyListNode<>(element);
       addLastNode(newNode);
    }

    void addMiddle(int position, E element) {
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        LinkedNode<E> currentNode = head;
        LinkedNode<E> previousNode = null;

        for (int i = 0; i < position; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        pairNode<E> pair = new pairNode<>(previousNode, currentNode);
        addMiddleNode(pair, newNode);
    }
}
