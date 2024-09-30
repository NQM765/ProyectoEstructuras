package ProyectoEstructuras2;

import java.util.ArrayList;
import java.util.List;


public class BinarySearchTree {


    class Node {
        Tarjeta tarjeta;
        Node left, right;
        
        public Node(Tarjeta tarjeta) {
            this.tarjeta = tarjeta;
            left = right = null;
        }
    }

    Node root;

    public BinarySearchTree() {
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
        return root;
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
        } else if (tarjeta.getTime().isBefore(root.tarjeta.getTime())) {
            root.left = removeRec(root.left, tarjeta);
        } else {
            int comparison = tarjeta.getTitle().compareTo(root.tarjeta.getTitle());
    
            if (comparison < 0) {
                root.left = removeRec(root.left, tarjeta);
            } else if (comparison > 0) {
                root.right = removeRec(root.right, tarjeta);
            } else {

                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }

                root.tarjeta = findMin(root.right);
    
                root.right = removeRec(root.right, root.tarjeta);
            }
        }
    
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

    public void inorder () {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.tarjeta.getTitle());
            inorderRec(root.right);

        }
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

    public int countInorder() {
        return countInorderRec(root);
    }
    
    private int countInorderRec(Node root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        count += countInorderRec(root.left);
        count += 1; 
        count += countInorderRec(root.right);
        return count;
    }

    public List<Tarjeta> getTarjetasInDescendingOrder() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        reverseInOrderTraversal(root, tarjetas);
        return tarjetas;
    }
    private void reverseInOrderTraversal(Node node, List<Tarjeta> tarjetas) {
        if (node != null) {
            reverseInOrderTraversal(node.right, tarjetas);
            tarjetas.add(node.tarjeta);
            reverseInOrderTraversal(node.left, tarjetas);
        }
    }
}