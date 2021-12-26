package Trees;

import java.util.HashSet;

public class BinarySearchTree {

    static class BST{
        int data;
        BST left;
        BST right;
        BST(int x){
            this.data = x;
            this.left = null;
            this.right = null;
        }
    }
    public static void main(String[] srgs) {
        System.out.println("Binary Search Tree : ");

        BST root = new BST(10);
        Add(root, 5);
        Add(root, 13);
        Add(root, 11);
        Add(root, 7);
        Add(root, 2);
        System.out.println("Inorder");
        inorder(root);
        System.out.println("\nFind 7 : "+ find(root, 7).data);
        System.out.println("\nFind Min : "+ findMin(root).data);
        System.out.println("\nFind Max : "+ findMax(root).data);
        System.out.println("\nBST LCA(7, 13) : "+ LCA(root, 7, 13).data);
        System.out.println("\nBST LCA(11, 13) : "+ LCA(root, 11, 13).data);
        System.out.println("\nisBST : "+ isBST(root));
        System.out.println("\nKth smallest 4 : "+ KthSmallest(root,4));
        Kth c1 = new Kth();
        c1.count = 0;
        System.out.println("\nKth smallest2 4 : "+ KthSmallest2(root,c1, 4));
        Kth c = new Kth();
        c.count = 3;
        System.out.println("\nKth largest 3 : "+ KthLargest(root,c));
        HashSet<Integer> set = new HashSet<>();
        System.out.println("\nPair with sum exists : " + FindPair(root, set, 23));

        System.out.println("\nCheck BST : " + checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    static void Add(BST root, int x) {
        if(root == null) return;

        if(x < root.data){
            if(root.left == null) {
                root.left = new BST(x);
                return;
            }
            Add(root.left, x);
        }
        if(x > root.data) {
            if(root.right == null) {
                root.right = new BST(x);
                return;
            }
            Add(root.right, x);
        }
    }

    static void inorder(BST root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    static BST find(BST root, int x){
        if(root == null) return null;
        
        if(x < root.data) {
            return find(root.left, x);
        }

        if(x > root.data) {
            return find(root.right, x);
        }

        return root;
    }

    static BST findMin(BST root) {
        if(root == null) return null;
        if(root.left == null) return root;
        return findMin(root.left);
    } 

    static BST findMax(BST root) {
        if(root == null) return null;
        if(root.right == null) return root;
        return findMax(root.right);
    } 

    static BST LCA(BST root, int n1, int n2) {
        //Assumption n1< n2
        if(root == null) return null;
        if(root.data > Math.max(n1, n2)) return LCA(root.left, n1, n2);
        else if(root.data < Math.min(n1, n2)) return LCA(root.right, n1, n2);
        else {
            // if((n1 < root.data && root.data < n2)) {
            // this is the implicit condition
            //     return root; //ans
            // }    
            return root;
        }
    } 

    static boolean isBST(BST root) {
        if(root == null) return true;
        if(root.left != null && findMax(root.left).data > root.data) return false;
        if(root.right != null && findMin(root.right).data < root.data) return false;
        if(!isBST(root.left) || !isBST(root.right)) return false;
        //if all is passed then it is a BST.
        return true;
    }

    //inorder travesal gives sorted list
    //Use recursion for 0(height) time complexity, although in skew tree it will be O(N)
    static int count = 0;
    static int KthSmallest(BST root, int k) {
      if(root == null) return 0;
      int left = KthSmallest(root.left, k);
      if(left != 0) return left;
      count++;
      if(count == k) {
        return root.data;
      }
      return KthSmallest(root.right, k);
    } 

    static int KthSmallest2(BST root, Kth k, int ck){
        if (root == null) return 0;
        int left = KthSmallest2(root.left, k, ck);
        if (left != 0) return left;
        k.count++; //It should be a pass by reference value
        if (k.count == ck) return root.data;
        return KthSmallest2(root.right, k, ck);
    }

    static int KthLargest(BST root, Kth k){
        if (root == null) return 0;
        int right = KthLargest(root.right, k);
        if (right != 0) return right;
        k.count --; //It should be a pass by reference value
        if (k.count == 0) return root.data;
        return KthLargest(root.left, k);
    }

    static boolean FindPair(BST root, HashSet<Integer> set, int sum) {
        if (root == null) return false;

        if (FindPair(root.left, set, sum)) return true;

        if (set.contains(sum - root.data)) return true;
        else {
            set.add(root.data);
        }

        return FindPair(root.right, set, sum);
    }

    static boolean checkBST(BST root, int min, int max) {
        if (root == null) return true;
        if (root.data < min || root.data > max) return false;
        return checkBST(root.left, min, root.data - 1) && checkBST(root.right, root.data + 1, max);
    }

}

class Kth {
    int count;
}