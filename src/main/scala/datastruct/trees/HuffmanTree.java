package datastruct.trees;

public class HuffmanTree {
    class Node
    {
        int freq;
        char data;
        Node left;
        Node right;

    };

    void decode(String S , Node root) {
        Node cur = root;
        for (int j=0; j< S.length(); j++) {
            cur = (S.charAt(j) == '0') ? cur.left : cur.right;
            if (cur.data != '\0') {
                System.out.print(cur.data);
                cur = root;
            }
        }
    }
}
