package algo;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 李高丞
 * @version 1.0
 */
public class TreeTravel {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

    }

    public static void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void preOrderWithArray(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) return;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                System.out.print(root.val + " ");
                stack.offer(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode tmp = stack.pollLast();
                root = tmp.right;
            }
        }
    }

    public static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    public static void inOrderWithArray(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) return;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.offer(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode tmp = stack.pollLast();
                System.out.print(tmp.val + " ");
                root = tmp.right;
            }
        }
    }

    public static void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    public static void postOrderWithArray(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) return;

        TreeNode cur, pre = null;
        stack.offer(root);
        while (!stack.isEmpty()) {
            cur = stack.peekLast();
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                System.out.print(cur.val + " ");
                stack.pollLast();
                pre = cur;
            } else {
                if (cur.right != null) stack.offer(cur.right);
                if (cur.left != null) stack.offer(cur.left);
            }
        }
    }

    static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
