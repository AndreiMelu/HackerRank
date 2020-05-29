import java.util.*;

class BTS_Node {
    BTS_Node left, right;
    int data;

    BTS_Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class BST_LevelOrderTraversal {

    static void levelOrder(BTS_Node root) {
        Queue<BTS_Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            BTS_Node current = queue.remove();
            System.out.print(current.data+" ");
            if (current.left!=null) queue.add(current.left);
            if (current.right!=null) queue.add(current.right);
        }

    }

    public static BTS_Node insert(BTS_Node root, int data) {
        if (root == null) {
            return new BTS_Node(data);
        } else {
            BTS_Node current;
            if (data <= root.data) {
                current = insert(root.left, data);
                root.left = current;
            } else {
                current = insert(root.right, data);
                root.right = current;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        BTS_Node root = null;
        while (T-- > 0) {
            int data = sc.nextInt();
            root = insert(root, data);
        }
        levelOrder(root);
    }
}