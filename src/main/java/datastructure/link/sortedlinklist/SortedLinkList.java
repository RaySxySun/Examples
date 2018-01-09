package datastructure.link.sortedlinklist;

public class SortedLinkList {
    private Node first;

    public SortedLinkList() {
        this.first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insert(long key) {
        Node node = new Node(key);
        Node pre = null;
        Node cur = first;

        while (cur != null && key > cur.dData) {
            pre = cur;
            cur = cur.next;
        }

        if (pre == null) {
            first = node;
        } else {
            pre.next = node;
        }
        node.next = cur;
    }

    // remove the first element
    public Node remove() {
        Node tmp = first;
        first = first.next;
        return tmp;
    }

    public void displayList() {
        System.out.print("List (first-->last): ");
        Node current = first;       // start at beginning of list

        while(current != null)      // until end of list,
        {
            current.displayNode();   // print data
            current = current.next;  // move to next link
        }

        System.out.println("");

    }
}

