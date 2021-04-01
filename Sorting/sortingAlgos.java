package Sorting;

public class sortingAlgos {

    public static void main(String[] args) {
        System.out.println("Bubble Sort");
        int[] arr1 = {5,6,8,7,9,5,4,2,1,10};
        bubbleSort(arr1);
        int[] arr2 = {15,16,18,17,19,15,14,12,11,10};
        bubbleSort(arr2);
        int[] arr3 = {15,16,18,17,19,15,14,12,11,10};
        insertionSort(arr3);
        int[] arr4 = {15,16,18,17,19,15,14,12,11,10};
        quickSort(arr4, 0, 9);
        for(int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i] + " ");
        }
        System.out.println();
    }

    public static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = i+1; j < arr.length; j++ ) {
                if(arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int k = i;
            for(int j = i+1; j < arr.length; j++ ) {
                if(arr[i] > arr[j]) {
                    k = j;
                }
            }
            if (k != i) {
                int temp = arr[i];
                    arr[i] = arr[k];
                    arr[k] = temp;
            }
        }
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public static void insertionSort(int[] a) {

        for(int i = 1; i < a.length; i++) { //This loop is the insertion loop
            int key = a[i];
            int hole = i;
            //The below logic shifts the array in the forward direction as we deck cards on insertion
            while(hole > 0 && a[hole-1] > key) {
                a[hole] = a[hole-1];//hole is filled with previous element 
                hole--;
            }
            a[hole] = key;
        }

        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void quickSort(int[] a, int l, int h) {

        if(l < h) {
            int p = partition(a, l, h);
            quickSort(a, l, p-1);
            quickSort(a, p+1, h);
        }
    }

    public static int partition(int[] a, int start, int end) {
        int pivot = a[end];
        int pindex = start; //partition index
        
        for(int i = start; i < end; i++) {

            if(a[i] <= pivot) {
                int temp = a[i];
                a[i] = a[pindex];
                a[pindex] = temp;
                pindex++;
            }

        }
               
        int temp = a[pindex];
        a[pindex] = a[end];
        a[end] = temp;
        return pindex;
    }


}
