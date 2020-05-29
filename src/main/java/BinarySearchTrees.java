
import java.util.Scanner;

class NodeTree {
    NodeTree left, right;
    int data;

    NodeTree(int data) {
        this.data = data;
        left = right = null;
    }
}

public class BinarySearchTrees {

    public static int getHeight(NodeTree root) {
        //Write your code here
        int heightLeft = 0;
        int heightRight = 0;

        if (root.left != null) {
            heightLeft = getHeight(root.left) + 1;
        }
        if (root.right != null) {
            heightRight = getHeight(root.right) + 1;
        }

        return (Math.max(heightLeft, heightRight));
    }

    public static NodeTree insert(NodeTree root, int data) {
        if (root == null) {
            return new NodeTree(data);
        } else {
            NodeTree current;
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
        NodeTree root = null;
        while (T-- > 0) {
            int data = sc.nextInt();
            root = insert(root, data);
        }
        int height = 0;
        if (root != null) {
            height = getHeight(root);
        }
        System.out.println(height);
    }
}