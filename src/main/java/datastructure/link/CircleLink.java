package datastructure.link;

import java.util.HashMap;

// To check if a list has a circle
/* Example: A->B->C->D->B->C->D
 *
 *                  Best        Avg         Worst       mem         stable
 * double check:    O(n2)       O(n2)       O(n2)       O(1)
 * hashset:         O(n)        O(n)        O(n)        O(n)
 * double pointer:  <O(n)       <O(n)       <O(n)       O(1)
 */
public class CircleLink {

    public static int circleLength = 0;

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


    public static boolean hasLoop1(Node node) {
        Node temp1 = node;
        HashMap<Node, Node> ns = new HashMap<>();
        while (node != null) {
            if (ns.get(temp1) != null) return true;
            else ns.put(temp1, temp1);
            temp1 = temp1.next;
            if (temp1 == null) return false;
        }
        return true;
    }

    public static boolean hasLoop2(Node node) {
        Node tmp1 = node;
        Node tmp2 = node.next;

        int times = 0;
        while (tmp2 != null) {
            int d1 = tmp1.value;
            int d2 = tmp2.value;
            if (times != 0) {
                circleLength++;
            }
            if (times == 2) {
                System.out.println("the length of the circle is: " + (circleLength - 1));
                return true;
            }
            if (d1 == d2) {
                times++;
            }
            tmp1 = tmp1.next;
            tmp2 = tmp2.next.next;
            if (tmp2 == null) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // 1->2->3->4->3
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n2;

        System.out.println(hasLoop1(n1));
        System.out.println(hasLoop2(n1));
    }


}
