import java.util.*;

public class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        int prefixSum = 0;
        long result = 0;

        for (int num : nums) {
            if (num % modulo == k) {
                prefixSum++;
            }

            int target = (prefixSum - k + modulo) % modulo;
            result += prefixCount.getOrDefault(target, 0);

            prefixCount.put(prefixSum % modulo, prefixCount.getOrDefault(prefixSum % modulo, 0) + 1);
        }

        return result;
    }
}