
package LinkedList;

public class LinkedList {

    static class ListNode {
        int data;
        ListNode next;
        ListNode(int x) {
            this.data = x;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Linked List");
        ListNode head = new ListNode(1);
        ListNode curr = head;
        for(int i = 2; i <= 5; i++) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        curr = head;
        while(curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
        ListNode rev = reverseKGroup(head, 3);
        System.out.println();
        while(rev != null) {
            System.out.print(rev.data + " ");
            rev = rev.next;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        if(headA == null || headB == null) return null;
        ListNode currA = headA;
        ListNode currB = headB;
        int lenA = 0;
        while(currA != null) {
            lenA++;
            currA = currA.next;
        }

        int lenB = 0;
        while(currB != null) {
            lenB++;
            currB = currB.next;
        }
        
        
        if(lenA > lenB) {
            int diff = lenA - lenB;
            while(diff != 0) {
                headA = headA.next;
                diff--;
            }
        }
        
        
        if(lenB > lenA) {
            int diff = lenB - lenA;
            while(diff != 0) {
                headB = headB.next;
                diff--;
            }
        }
        
        //System.out.println("lenA: "+ lenA + " lenB: "+ lenB))
        ListNode intersection = null;
        while(headA != null && headB != null) {
            if(headA == headB) {
                intersection = headA;
                break;
            }
            else {
                headA = headA.next;
                headB = headB.next;
            }
        }
        
        return intersection;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        
        if(head == null && k == 1) return head;
        ListNode prev = null;
        ListNode next = null;
        ListNode curr = head;
        int count = 0;
        int len = countNodes(head);
        if(len == k) {
            while(count < k && curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                count++;
            }
        } else {
            prev = curr;

        }
        
        if(next != null) {
            head.next = reverseKGroup(next, k);
        }
        
        return prev;
    }
    
    public static int countNodes(ListNode head) {
        System.out.println("data : " + head.data);
        if(head == null) return 0;
        int count = 0;
        ListNode curr = head;
        while(curr != null) {
            count++;
            curr = curr.next;
            if(count == 3) break;
        }
        System.out.println("count : " + count);
        return count;
    }










}
