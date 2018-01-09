package datastructure.link.linklist;

public class Node {
    public int iData;
    public double dData;
    public Node next;

    public Node(int iData, double dData) {
        this.iData = iData;
        this.dData = dData;
    }

    public void displayLink() {
        System.out.print("{" + iData + ", " + dData + "}");
    }

}
