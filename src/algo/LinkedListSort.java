package algo;

/**
 * @author 李高丞
 * @version 1.0
 */
public class LinkedListSort {

    public static void main(String[] args) {
        ListNode node = new ListNode(9);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(6);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(1);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node = MergeSort(node);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    private static class ListNode {
        int value;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int value) {
            this.value = value;
        }
    }

    private static ListNode InsertSort(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode();
        ListNode pre = head, cur = head.next;
        dummy.next = head;
        while (cur != null) {
            if (cur.value >= pre.value) {
                pre = cur;
                cur = cur.next;
            } else {
                ListNode p = dummy;
                while (p.next.value < cur.value) p = p.next;
                pre.next = cur.next;
                cur.next = p.next;
                p.next = cur;
                cur = pre.next;
            }
        }
        return dummy.next;
    }

    private static ListNode MergeSort(ListNode head) {
        if (head.next == null) return head;

        ListNode p = head, q = head, pre = null;
        // 将链表分为两半，然后进行排序
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        pre.next = null;

        ListNode left = MergeSort(head);
        ListNode right = MergeSort(p);
        return merge(left, right);
    }

    private static ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        // 对两个链表进行合并
        while (left != null && right != null) {
            if (left.value <= right.value) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) cur.next = left;
        if (right != null) cur.next = right;
        return dummy.next;
    }
}
