class Solution {
    public int maxBalancedSubarray(int[] nums) {
        int px = 0;
        int bal = 0;
        int ans = 0;

        // Map of (px, bal) -> earliest index
        Map<String, Integer> seen = new HashMap<>();
        seen.put("0#0", -1);

        for (int i = 0; i < nums.length; i++) {
            px ^= nums[i];
            bal += (nums[i] % 2 == 1 ? 1 : -1);

            String key = px + "#" + bal;

            if (seen.containsKey(key)) {
                ans = Math.max(ans, i - seen.get(key));
            } else {
                seen.put(key, i);
            }
        }

        return ans;
    }
}