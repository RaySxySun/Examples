package datastructure.link.linklist;

public class LinkListApp {
    public static void main(String[] args) {
        LinkList list = new LinkList();
        list.insertFirst(22, 2.99);      // insert 4 items
        list.insertFirst(44, 4.99);
        list.insertFirst(66, 6.99);
        list.insertFirst(88, 8.99);
        list.displayList();
        Node node = list.find(44);
        if (node != null) {
            System.out.print("find the node: ");
            node.displayLink();
            System.out.println();
        } else {
            System.out.println("no such node");
        }

        Node node2 = list.delete(44);
        if (node2 != null) {
            System.out.print("remove the node:");
            node.displayLink();
            System.out.println();
        } else
            System.out.println("no such node");
        list.displayList();
    }
}
