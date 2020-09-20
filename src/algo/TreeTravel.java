package algo;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 李高丞
 * @version 1.0
 */
public class TreeTravel {
    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int value) {
            this.val = value;
        }
    }

    public static void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    public static void preOrderWithArray(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) return;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                System.out.print(root.val + " ");
                stack.offerLast(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode node = stack.pollLast();
                root = node.right;
            }
        }
    }

    public static void inOrderWithArray(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) return;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.offerLast(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode node = stack.pollLast();
                System.out.print(node.val + " ");
                root = node.right;
            }
        }
    }

    public static void postOrderWithArray(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) return;

        TreeNode cur, pre = null;
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            cur = stack.peekLast();
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                System.out.print(cur.val + " ");
                stack.pollLast();
                pre = cur;
            } else {
                if (cur.right != null) stack.offerLast(cur.right);
                if (cur.left != null) stack.offerLast(cur.left);
            }
        }
    }
}
