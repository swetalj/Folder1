/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int val=0, TreeNode left=null, TreeNode right=null) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    public TreeNode SortedArrayToBST(int[] nums) {
        if (nums == null || nums.Length == 0) {
            return null;
        } 

        int mid = nums.Length / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = SortedArrayToBST(SubArray(nums, 0, mid));
        root.right = SortedArrayToBST(SubArray(nums, mid + 1, nums.Length));

        return root;
    }
        private int[] SubArray(int[] array, int start, int end) {
        int length = end - start;
        int[] result = new int[length];
        Array.Copy(array, start, result, 0, length);
        return result;
    }
}
