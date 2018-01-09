package datastructure.link.linklist;

public class LinkList {
    public Node first;

    public LinkList() {
        this.first = null;
    }

    public void insertFirst(int i, double d) {
        Node node = new Node(i, d);
        node.next = first;
        first = node;
    }

    public Node find(int key) {
        Node current = first;
        while (current.iData != key) {
            if (current.next == null) {
                return null;
            } else {
                current = current.next;
            }
        }
        return current;
    }

    public Node delete(int key) {
        Node current = first;
        Node previous = first;

        while (current.iData != key) {
            if (current.next == null) {
                return null;
            } else {
                previous = current;
                current = current.next;
            }
        }

        if (current == first) {
            first = first.next;
        } else {
            previous.next = current.next;
        }

        return current;
    }

    public void displayList() {
        System.out.print("List(first->last)");
        Node current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }
}
