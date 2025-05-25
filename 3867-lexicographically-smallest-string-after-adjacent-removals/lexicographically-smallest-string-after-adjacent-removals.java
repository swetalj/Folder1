class Solution {
    private boolean isConsec(char a, char b) {
        int diff = Math.abs(a - b);
        return diff == 1 || diff == 25;
    }
    public String lexicographicallySmallestString(String s) {
        int n = s.length();
        String[][] dp = new String[n + 1][n + 1];

        // Initialize dp with empty strings
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = "";
            }
        }

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len;

                // Base: Keep s.charAt(i)
                String res = s.charAt(i) + dp[i + 1][j];

                // Try to remove s[i] and s[k] if possible
                for (int k = i + 1; k < j; k++) {
                    if (isConsec(s.charAt(i), s.charAt(k)) && dp[i + 1][k].equals("")) {
                        if (dp[k + 1][j].compareTo(res) < 0) {
                            res = dp[k + 1][j];
                        }
                    }
                }

                dp[i][j] = res;
            }
        }

        return dp[0][n];
    }
}