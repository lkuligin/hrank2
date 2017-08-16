package datastruct.trees;

import java.util.*;
import java.io.*;

public class BinaryTreeHeight {

    static int height(Node root) {
        int leftHeight = (root.left != null) ? 1+height(root.left) : 0;
        int rightHeight = (root.right != null) ? 1+height(root.right) : 0;
        return Math.max(leftHeight, rightHeight);
    }

    public static Node insert(Node root, int data) {
        if(root == null){
            return new Node(data);
        }
        else {
            Node cur;
            if(data <= root.data){
                cur = insert(root.left, data);
                root.left = cur;
            }
            else{
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0){
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }
}
