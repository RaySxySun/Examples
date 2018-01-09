package datastructure.link.doubleLinkList;

public class DoubleLinkList {
    private Node first;
    private Node last;

    public DoubleLinkList() {
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty() {
        return (this.first == null);
    }

    public void insertFirst(long dd) {
        Node node = new Node(dd);
        if (isEmpty()) {
            last = node;
        } else {
            first.previous = node;
        }
        node.next = first;
        first = node;
    }

    public void insertLast(long dd) {
        Node node = new Node(dd);
        if (isEmpty()) {
            first = node;
        } else {
            last.next = node;
            node.previous = last;
        }
        last = node;
    }

    public Node deleteFirst() {
        // empty list
        if (isEmpty()) {
            return null;
        }
        Node tmp = first;
        if (first.next == null) {
            // only one elements
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return tmp;
    }

    public Node deleteLast() {
        //empty list
        if (isEmpty()) {
            return null;
        }
        Node tmp = last;
        if (first.next == null) {
            // only one elements
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return tmp;
    }

    public boolean insertAfter(long key, long dd) {
        if (isEmpty()) {
            return false;
        }

        Node current = first;
        while (current.dData != key) {
            current = current.next;
            if (current == null) {
                return false;
            }
        }
        Node node = new Node(dd);
        if (current == last) {
            node.next = null;
            last = node;
        } else {
            node.next = current.next;
            current.next.previous = node;
        }
        node.previous = current;
        current.next = node;
        return true;
    }

    public Node deleteKey(long key) {
        Node current = first;
        while (current.dData != key) {
            current = current.next;
            if (current == null) {
                return null;
            }
        }
        if (current == last) {
            last = current.previous;
        } else {
            current.next.previous = current.previous;
        }
        return current;
    }

    public void displayForward() {
        System.out.print("List (first-->last): ");
        Node current = first;          // start at beginning

        while(current != null)         // until end of list,
        {
            current.displayNode();      // display data
            current = current.next;     // move to next link
        }
        System.out.println("");
    }

    public void displayBackward()
    {
        System.out.print("List (last-->first): ");
        Node current = last;           // start at end
        while(current != null)         // until start of list,
        {
            current.displayNode();      // display data
            current = current.previous; // move to previous link
        }
        System.out.println("");
    }

}
