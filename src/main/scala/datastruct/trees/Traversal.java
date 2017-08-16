package datastruct.trees;

import java.util.LinkedList;
import java.util.Queue;

public class Traversal {
    void preOrder(Node root) {
        System.out.print(root.data + " ");
        if (root.left != null) preOrder(root.left);
        if (root.right != null) preOrder(root.right);
    }

    void postOrder(Node root) {
        if (root.left != null) postOrder(root.left);
        if (root.right != null) postOrder(root.right);
        System.out.print(root.data + " ");
    }

    void inOrder(Node root) {
        if (root.left != null) inOrder(root.left);
        System.out.print(root.data + " ");
        if (root.right != null) inOrder(root.right);
    }

    void topView(Node root) {
        if (root.left != null) topViewLeft(root.left);
        System.out.print(root.data + " ");
        if (root.right != null) topViewRight(root.right);
    }

    void topViewLeft(Node root) {
        if (root.left != null) topViewLeft(root.left);
        System.out.print(root.data + " ");
    }

    void topViewRight(Node root) {
        System.out.print(root.data + " ");
        if (root.right != null) topViewRight(root.right);
    }

    void levelOrder(Node root) {
        Queue<Node> q = new LinkedList();
        q.add(root);
        while (!q.isEmpty()) {
            Node current = q.poll();
            System.out.print(current.data + " ");
            if (current.left != null) q.add(current.left);
            if (current.right != null) q.add(current.right);
        }
    }

    //for binary search tree only
    static Node lca(Node root, int v1, int v2) {
        return (root == null) ? null : (root.data < v1 && root.data < v2) ? lca(root.right, v1, v2) :
                (root.data > v1 && root.data > v2) ? lca(root.left, v1, v2) : root;
    }

}
