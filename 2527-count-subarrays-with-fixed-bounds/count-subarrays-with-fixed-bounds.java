class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long count = 0;
        int left = -1;
        int last_min = -1;
        int last_max = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                left = i;
                last_min = -1;
                last_max = -1;
            }
            if (nums[i] == minK) {
                last_min = i;
            }
            if (nums[i] == maxK) {
                last_max = i;
            }
            count += Math.max(0, Math.min(last_min, last_max) - left);
        }

        return count;
    }
}