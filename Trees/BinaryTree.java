package Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This is a generic class for Binary trees implementation
 * @param root
 */

public class BinaryTree {
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        //constructor
        TreeNode(int x) {
            this.data = x;
            this.left = null;
            this.right = null;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Hello Binary Tree");
        TreeNode root = createBinaryTree();
        System.out.println("\nPreorder");
        preorder(root);
        System.out.println("\nInorder");
        inorder(root);
        System.out.println("\nPostorder");
        postorder(root);
        System.out.println("\nlevelorder");
        levelorder(root);
        System.out.println("\nReverselevelorder");
        reverseLevelOrder(root);
        int height = height(root);
        System.out.println("\n  `Height of the tree: "+ height);
        int diameter = diameter(root);
        System.out.println("\n  `Diameter of the tree: "+ diameter);
        ArrayList<Integer> path = new ArrayList<Integer>();
        path(root, path, 0);
        System.out.println("\n  `Sum of the all tree nodes: "+ sum(root));
        System.out.println("\n  `Ancestors of node 12");
        ArrayList<Integer> ancestorPath = new ArrayList<Integer>();
        ancestors(root, 12, ancestorPath, 0);

        TreeNode LCA = LCA(root, 8, 6);
        System.out.println("\nLCA(8,6)="+ LCA.data);
        LCA = LCA(root, 12, 7);
        System.out.println("\nLCA(12,7)="+ LCA.data);
        LCA = LCA(root, 5, 11);
        System.out.println("\nLCA(5,11)="+ LCA.data);
        LCA = LCA(root, 2, 4);
        System.out.println("\nLCA(2,4)="+ LCA.data);
        System.out.print("\nSpiral Traversal : ");
        spiralTraversal(root);

    }

    static void spiralTraversal(TreeNode root) {
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s1.push(root);
        int leftToRight = 1;
        while(!s1.isEmpty()){

            TreeNode temp = s1.pop();
            System.out.print(temp.data + " ");
            if(leftToRight == 1) {
                if(temp.left != null) s2.push(temp.left);
                if(temp.right != null) s2.push(temp.right);
                
            } else {
                if(temp.right != null) s2.push(temp.right);
                if(temp.left != null) s2.push(temp.left);
            }

            if(s1.isEmpty()) {
                Stack<TreeNode> s = new Stack<TreeNode>();
                s = s1;
                s1 = s2;
                s2 = s;
                leftToRight = 1 - leftToRight;
            }
        }
    }
    
    static void inorder(TreeNode root) {
        if (root == null) return ;
        else {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    static void preorder(TreeNode root) {
        if (root == null) return ;
        else {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }
    
    static void postorder(TreeNode root) {
        if (root == null) return ;
        else {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    static void levelorder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode temp = q.poll();
            System.out.print(temp.data + " ");
            if(temp.left != null) {
                q.add(temp.left);
            }
            if(temp.right != null) {
                q.add(temp.right);
            }
        }
    }

    static void reverseLevelOrder(TreeNode root) {
        if (root == null) return;
        Stack<Integer> s = new Stack<Integer>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode temp = q.poll();
            s.push(temp.data);
            if(temp.right != null) {
                q.add(temp.right);
            }
            if(temp.left != null) {
                q.add(temp.left);
            }
        }
        while(!s.isEmpty()){
            System.out.print(s.pop() + " ");
        }
    }

    static int height(TreeNode root) {
        if(root == null) return 0;
        int leftsubtree = height(root.left);
        int rightsubtree = height(root.right);
        return Math.max(leftsubtree, rightsubtree) + 1;
    }
    
    static int diameter(TreeNode root) {
        if(root == null) return 0;

        int leftH = height(root.left);
        int rightH = height(root.right);

        int leftD = diameter(root.left);
        int rightD = diameter(root.right);

        return Math.max(leftH + rightH + 1, Math.max(leftD, rightD));
    }

    static void path(TreeNode root, ArrayList<Integer> pathArr, int index) {
        if (root == null) return;
        pathArr.add(index++, root.data);
        if(root.left == null && root.right == null) {
            printPath(pathArr, index);
        } else {
            path(root.left, pathArr, index);
            path(root.right, pathArr, index);
        }
    }

    static void printPath(ArrayList<Integer> pathArr, int index) {
        for (int i = 0; i < index; i++) {
            System.out.print(pathArr.get(i) + " ");
        }
        System.out.println();
    }

    static void ancestors(TreeNode root, int child, ArrayList<Integer> pathArr, int index) {

        if(root == null) return;

        pathArr.add(index++, root.data);
        if(root.data == child) {
            printPath(pathArr, index);
        }

        ancestors(root.left, child, pathArr, index);
        ancestors(root.right, child, pathArr, index);
    }


    static int sum(TreeNode root) {
        if(root == null) return 0;
        return root.data + sum(root.left) + sum(root.right);
    }

    static TreeNode LCA(TreeNode root, int n1, int n2) {
        if(root == null) return root;

        if(root.data == n1 || root.data == n2) return root;

        TreeNode left = LCA(root.left, n1, n2);
        TreeNode right = LCA(root.right, n1, n2);

        if(left != null && right != null) return root;
        if(left == null && right == null) return null; 
        if(left == null) return right;
        else return left;
    }

    static HashMap<Integer, Integer> verticalSum(TreeNode root) {
        return null;
    }

    
    static TreeNode createBinaryTree() {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(11);
        root.right.left.right = new TreeNode(12);
        root.right.right.left = new TreeNode(13);
        root.right.right.right = new TreeNode(14);
        root.right.left.right.left = new TreeNode(15);
        return root;
    }
}


