package datastructure.link.doubleLinkList;

public class Node {
    public long dData;
    public Node next;
    public Node previous;

    public Node(long dData) {
        this.dData = dData;
    }

    public void displayNode()          // display this link
    { System.out.print(dData + " "); }
}
