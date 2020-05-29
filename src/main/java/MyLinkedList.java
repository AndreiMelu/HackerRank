
import java.util.Scanner;

class Node1 {
    int data;
    Node1 next;

    Node1(int d) {
        data = d;
        next = null;
    }
}

public class MyLinkedList {

    public static Node1 insert(Node1 head, int data) {
        //Complete this method
        if (head == null)
            return new Node1(data);
        else if (head.next == null) {
            head.next = new Node1(data);
        } else {
            insert(head.next, data);
        }
        return head;
    }

    public static void display(Node1 head) {
        Node1 start = head;
        while (start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node1 head = null;
        int N = sc.nextInt();

        while (N-- > 0) {
            int ele = sc.nextInt();
            head = insert(head, ele);
        }
        display(head);
        sc.close();
    }
}