package res2_laundry;

public class Node {
    Node next;
    String[] data;
    Node() {
        next = null;
        data = null;
    }
    Node(String[] Data) {
        next = null;
        data = Data;
    }
    Node(String[] Data, Node Next) {
        next = Next;
        data = Data;
    }
}
