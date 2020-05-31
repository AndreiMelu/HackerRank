import java.util.Scanner;

/**
 * Task
 * A Node class is provided for you in the editor. A Node object has an integer data field, data, and
 * a Node instance pointer, next, pointing to another node (i.e.: the next node in a list).
 * <p>
 * A removeDuplicates function is declared in your editor, which takes a pointer to the head node of a linked list as a parameter.
 * Complete removeDuplicates so that it deletes any duplicate nodes from the list and returns the head of the updated list.
 * <p>
 * Note: The head pointer may be null, indicating that the list is empty.
 * Be sure to reset your next pointer when performing deletions to avoid breaking the list.
 */


class NodeDuplicates {
    int data;
    NodeDuplicates next;

    NodeDuplicates(int d) {
        data = d;
        next = null;
    }
}

public class MoreLinkedLists {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NodeDuplicates head = null;
        int T = sc.nextInt();
        while (T-- > 0) {
            int ele = sc.nextInt();
            head = insert(head, ele);
        }
        head = removeDuplicates(head);
        display(head);

    }

    public static NodeDuplicates insert(NodeDuplicates head, int data) {
        NodeDuplicates p = new NodeDuplicates(data);
        if (head == null)
            head = p;
        else if (head.next == null)
            head.next = p;
        else {
            NodeDuplicates start = head;
            while (start.next != null)
                start = start.next;
            start.next = p;

        }
        return head;
    }

    public static void display(NodeDuplicates head) {
        NodeDuplicates start = head;
        while (start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static NodeDuplicates removeDuplicates(NodeDuplicates head) {
        //Write your code here
        if (head == null || head.next == null){
            return head;
        }
        if (head.data == head.next.data){
            head.next = head.next.next;
            removeDuplicates(head);
        }else{
            removeDuplicates(head.next);
        }
        return head;
    }
}