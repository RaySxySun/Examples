package datastructure.link.sortedlinklist;

public class Node {
    public long dData;
    public Node next;

    public Node(long dData) {
        this.dData = dData;
    }

    public void displayNode() {
        System.out.print("{" + dData + "}");
    }

}
