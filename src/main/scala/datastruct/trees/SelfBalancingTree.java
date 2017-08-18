package datastruct.trees;


public class SelfBalancingTree {
    class Node {
        int val;   //Value
        int ht;      //Height, for null nodes is always -1
        Node left;   //Left child
        Node right;   //Right child

        Node(){
        }

        Node(int val, int ht, Node l, Node r) {
            this.val = val;
            this.ht = ht;
            this.left = l;
            this.right = r;
        }

        @Override
        public String toString() {
            String l = (this.left == null) ? "null" : this.left.toString();
            String r = (this.right == null) ? "null" : this.right.toString();
            return "val: " + this.val + " ht: " + this.ht + " (left: " + l + "), right: (" + r + ")";
        }
    }


    Node insert(Node root, int val)
    {
        if (root == null) {
            Node n = new Node();
            n.val = val;
            n.ht = 0;
            return n;
        }

        if (root.val >= val) {
            root.left = insert(root.left, val);
        }
        else {
            root.right = insert(root.right, val);
        }

        return rebalance(root);
    }

    Node rebalance(Node node) {
        int balanceFactor = getHeight(node.left)-getHeight(node.right);
        //System.out.println("b: " + balanceFactor);

        if (balanceFactor < -1) {
            //System.out.println("-1 right " + node.right);
            //System.out.println("-1 left: " + node.left);
            if (recalcHeight(node.right.left) < recalcHeight(node.right.right) ) { //a node was inserted into the right subtree of the right subtree
                return leftRotation(node);
            }
            else { //a node is inserted into the left subtree of the right subtree
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }
        }
        else if (balanceFactor > 1) {
            //System.out.println("1 right " + node.right);
            //System.out.println("-1 left " + node.left);
            if (recalcHeight(node.left.left) >= recalcHeight(node.left.right)) { //a node is inserted into the left subtree of the left subtree
                return rightRotation(node);
            }
            else {
                node.left = leftRotation(node.left);
                return rightRotation(node);
            }
        }
        else {
            node.ht = recalcHeight(node);
            //System.out.println("h " + node.ht);
            return node;
        }
    }

    Node leftRotation(Node node) {
        Node tmp = node.right;
        node.right = tmp.left; //node.left = node.left
        tmp.left = node;
        node.ht = recalcHeight(node);
        tmp.ht = recalcHeight(tmp);
        return tmp;
    }

    Node rightRotation(Node node) {
        Node tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        tmp.right = node;
        node.ht = recalcHeight(node);
        tmp.ht = recalcHeight(tmp);
        return tmp;
    }

    int getHeight(Node node) {
        return (node == null) ? -1 : node.ht;
    }

    int recalcHeight(Node node) {
        return (node == null) ? -1 : 1+Math.max(getHeight(node.right), getHeight(node.left));
    }


}
