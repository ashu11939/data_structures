package Trees;

public class Heap {
    /**
     * Below is the implementation of a Max Heap
     */
    static class HeapNode{
        int[] arr;//array to store elements
        int count;//number of elements in the array
        int size;//size of the heap
        String type; //max or min
        HeapNode(int[] arr, String type) {
            this.arr = arr;
            this.count = 0;
            this.size = arr.length;
            this.type = type;
        }
    }

    static int leftChild(HeapNode node, int i){
        int l = 2*i + 1;
        if(l <= node.count) return node.arr[l];
        else return -1;
    }

    
    static int rightChild(HeapNode node, int i){
        int r = 2*i + 2;
        if(r <= node.count) return node.arr[r];
        else return -1;
    }

    static int Max(HeapNode node){
        return node.arr[0];
    }

    static boolean isLeaf(HeapNode node, int i){
        if( i >= node.count / 2) return true;
        else return false;
    }

    static void HeapifyDown(HeapNode node, int i) {

        int l, r, max, temp;
        l = 2 * i + 1;
        r = 2 * i + 2;
        if(l <= node.count && node.arr[i] < node.arr[l]) max = l;
        else max = i;
        if(r <= node.count && node.arr[max] < node.arr[r]) max = r;

        if(i != max) {
            temp = node.arr[max];
            node.arr[max] = node.arr[i];
            node.arr[i] = temp;
            HeapifyDown(node, max);
        }
    }

    static int parent(int pos){
        if(pos == 0) return 0;
        else return (pos - 1) / 2;
    }

    static void HeapifyUp(HeapNode node, int pos){

        int current = pos;
        int parent = parent(current);
        if(parent >= 0 && node.arr[current] > node.arr[parent]) {
            //swap pos and parent
            int temp = node.arr[current];
            node.arr[current] = node.arr[parent];
            node.arr[parent] = temp;
            current = parent;
            HeapifyUp(node, current);
        }
    }

    static void Insert(HeapNode node, int data) {
        node.arr[node.count++] = data;
        HeapifyUp(node, node.count - 1);
    }

    static void PrintHeap(HeapNode node) {
        for(int i = 0; i < node.count / 2; i ++) {
            System.out.print("Parent - " + node.arr[i] + " left child : " + node.arr[2*i + 1] + " right child : " + node.arr[2*i + 2]);
            System.out.println();
        }
    }

    static int DeleteMax(HeapNode node) {
        int ans = node.arr[0];
        int temp = node.arr[0];
        node.arr[0] = node.arr[node.count-1];
        node.arr[node.count-1] = temp;
        node.count--;
        HeapifyUp(node, node.count-1);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("****Heap*****");
        int size = 15;
        int[] arr = new int[size];
        HeapNode heap = new HeapNode(arr, "max");

        Insert(heap, 5); 
		Insert(heap, 3); 
		Insert(heap, 17); 
		Insert(heap, 10); 
		Insert(heap, 84); 
		Insert(heap, 19); 
		Insert(heap, 6); 
		Insert(heap, 22); 
		Insert(heap, 9); 
        Insert(heap, 100); 

		PrintHeap(heap);
        System.out.println("****Delete Max***** " + DeleteMax(heap));
        PrintHeap(heap);
    }
}
