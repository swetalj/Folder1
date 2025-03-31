class Solution {
    public int findLucky(int[] arr) {
        Map<Integer, Integer> x = new HashMap<>();
        for (int num : arr) {
            x.put(num, x.getOrDefault(num, 0) + 1);
        }
        int high = -1;
        for (Map.Entry<Integer, Integer> entry : x.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (key == value) {
                high = Math.max(high, value);
            }
        }
        return high;
    }

        // return -1;
    // }
}