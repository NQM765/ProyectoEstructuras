package ProyectoEstructuras2;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    class Node {
        Tarjeta tarjeta;
        Node left, right;
        int height;
        
        public Node(Tarjeta tarjeta) {
            this.tarjeta = tarjeta;
            left = right = null;
            height = 1;
        }
    }

    Node root;

    public AVLTree() {
        root = null;
    }

    public void insert(Tarjeta tarjeta) {
        root = insertRec(root, tarjeta);
    }
    
    private Node insertRec(Node root, Tarjeta tarjeta) {
        if (root == null) {
            root = new Node(tarjeta);
            return root;
        }
        
        if (tarjeta.getTime().isAfter(root.tarjeta.getTime())) {
            root.right = insertRec(root.right, tarjeta);
        } 
        else if (tarjeta.getTime().isBefore(root.tarjeta.getTime())) {
            root.left = insertRec(root.left, tarjeta);
        }
        else if (tarjeta.getTime().isEqual(root.tarjeta.getTime())) {
            if (tarjeta.getTitle().compareTo(root.tarjeta.getTitle()) < 0) {
                root.left = insertRec(root.left, tarjeta);
            } else {
                root.right = insertRec(root.right, tarjeta);
            }
        }
     
        root.height = 1 + Math.max(height(root.left), height(root.right));

        if (root != null) {
            root = balance(root, tarjeta);
        } 
        
        return root;
    }

    private Node balance(Node root, Tarjeta tarjeta) {
        if (root == null) {
            return null;
        }

        int balance = getBalance(root);

        if (balance > 1 && tarjeta.getTime().isBefore(root.left.tarjeta.getTime())) {
            return rightRotate(root);
        }
        if (balance < -1 && tarjeta.getTime().isAfter(root.right.tarjeta.getTime())) {
            return leftRotate(root);
        }
        if (balance > 1 && tarjeta.getTime().isAfter(root.left.tarjeta.getTime())) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && tarjeta.getTime().isBefore(root.right.tarjeta.getTime())) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.tarjeta.getTitle());
            inorderRec(root.right);
        }
    }

    public int countInorder() {
        return countInorderRec(root);
    }
    
    private int countInorderRec(Node root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        count += countInorderRec(root.left);
        count += 1; // Cuenta este nodo
        count += countInorderRec(root.right);
        return count;
    }

    public Tarjeta search(Tarjeta tarjeta) {
        Node node = searchRec(root, tarjeta);
        return node == null ? null : node.tarjeta;
    }

    private Node searchRec(Node root, Tarjeta tarjeta) {
        if (root == null || root.tarjeta.equals(tarjeta)) {
            return root;
        }
        if (tarjeta.getTime().isAfter(root.tarjeta.getTime())) {
            return searchRec(root.right, tarjeta);
        }
        return searchRec(root.left, tarjeta);
    }

    public void remove(Tarjeta tarjeta) {
        root = removeRec(root, tarjeta);
    }

    private Node removeRec(Node root, Tarjeta tarjeta) {
        if (root == null) {
            return root;
        }

        if (tarjeta.getTime().isAfter(root.tarjeta.getTime())) {
            root.right = removeRec(root.right, tarjeta);
        } 
        else if (tarjeta.getTime().isBefore(root.tarjeta.getTime())) {
            root.left = removeRec(root.left, tarjeta);
        } 
        else if (tarjeta.getTime().isEqual(root.tarjeta.getTime())) {
            if (tarjeta.getTitle().compareTo(root.tarjeta.getTitle()) < 0) {
                root.left = removeRec(root.left, tarjeta);
            } else {
                root.right = removeRec(root.right, tarjeta);
            }
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.tarjeta = findMin(root.right);

            root.right = removeRec(root.right, root.tarjeta);
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));
        root = balance(root, tarjeta);

        return root;
    }

    private Tarjeta findMin(Node root) {
        Tarjeta min = root.tarjeta;
        while (root.left != null) {
            min = root.left.tarjeta;
            root = root.left;
        }
        return min;
    }

    private int height(Node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    private int getBalance(Node N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    private Node rightRotate(Node y) {
        if (y == null || y.left == null) {
            return y;
        }
        Node x = y.left;
        Node T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        
        return x;
    }

    private Node leftRotate(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        Node y = x.right;
        Node T2 = y.left;
        
        y.left = x;
        x.right = T2;
        
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        
        return y;
    }

    public List<Tarjeta> getTarjetasInDescendingOrder() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        reverseInOrderTraversal(root, tarjetas);
        return tarjetas;
    }

    private void reverseInOrderTraversal(Node node, List<Tarjeta> tarjetas) {
        if (node == null) {
            return;
        }
        reverseInOrderTraversal(node.right, tarjetas);
        tarjetas.add(node.tarjeta);
        reverseInOrderTraversal(node.left, tarjetas);
    }
}