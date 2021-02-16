package Trees;

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
}
