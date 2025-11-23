// // import java.util.*;

// // class Solution {
// //     public List<Integer> sumAndMultiply(String s, int[][] queries) {
// //         int n = s.length();
// //         int mod = 1000000007;        
// //         int[] prefSum = new int[n + 1];
// //         for (int i = 0; i < n; i++) {
// //             prefSum[i + 1] = prefSum[i] + (s.charAt(i) - '0');
// //         }

// //         // prefix concatenation of non-zero digits
// //         long[] num = new long[n + 1];      // prefix number formed by non-zero digits
// //         int[] count = new int[n + 1];      // count of non-zero digits so far

// //         for (int i = 0; i < n; i++) {
// //             char ch = s.charAt(i);

// //             if (ch == '0') {
// //                 num[i + 1] = num[i];
// //                 count[i + 1] = count[i];
// //             } else {
// //                 int digit = ch - '0';
// //                 num[i + 1] = (num[i] * 10 + digit) % mod;
// //                 count[i + 1] = count[i] + 1;
// //             }
// //         }

// //         // precompute powers of 10
// //         int maxLen = count[n];
// //         long[] pow10 = new long[maxLen + 1];
// //         pow10[0] = 1;
// //         for (int i = 1; i <= maxLen; i++) {
// //             pow10[i] = (pow10[i - 1] * 10) % mod;
// //         }

// //         List<Integer> res = new ArrayList<>();

// //         for (int[] q : queries) {
// //             int l = q[0];
// //             int r = q[1];

// //             // sum of digits in s[l..r]
// //             int totalSum = prefSum[r + 1] - prefSum[l];

// //             // length of non-zero digits inside s[l..r]
// //             int nonZeroLen = count[r + 1] - count[l];

// //             // x = concatenation of non-zero digits in s[l..r]
// //             long x = (num[r + 1] - (num[l] * pow10[nonZeroLen]) % mod + mod) % mod;

// //             long ans = (x * totalSum) % mod;

// //             res.add((int) ans);
// //         }

// //         return res;
// //     }
// // }
// class Solution {
//     public int[] sumAndMultiply(String s, int[][] queries) {
//         final long MOD = 1_000_000_007L;
//         int n = s.length();

//         int[] idx = new int[n + 1];      // prefix count of non-zero digits
//         long[] val = new long[n + 1];    // prefix concatenation (mod)
//         long[] tot = new long[n + 1];    // prefix digit sum
//         long[] pow10 = new long[n + 1];  // powers of 10

//         pow10[0] = 1;
//         for (int i = 1; i <= n; i++)
//             pow10[i] = (pow10[i - 1] * 10) % MOD;

//         int c = 0;

//         // Build prefixes
//         for (int i = 0; i < n; i++) {
//             int d = s.charAt(i) - '0';
//             if (d != 0) {
//                 c++;
//                 val[c] = (val[c - 1] * 10 + d) % MOD;
//                 tot[c] = tot[c - 1] + d;
//             }
//             idx[i + 1] = c;
//         }

//         int m = queries.length;
//         int[] ans = new int[m];

//         for (int i = 0; i < m; i++) {
//             int l = queries[i][0];
//             int r = queries[i][1];

//             int a = idx[l];
//             int b = idx[r + 1];

//             if (a == b) {
//                 ans[i] = 0;
//                 continue;
//             }

//             int len = b - a;

//             long num = (val[b] - (val[a] * pow10[len]) % MOD + MOD) % MOD;
//             long sumDigits = tot[b] - tot[a];

//             ans[i] = (int)((num * sumDigits) % MOD);
//         }

//         return ans;
//     }
// }
class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();
        long MOD = 1_000_000_007L;

        // powers of 10 under mod
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // prefixValue: concatenation of digits
        // prefixSum: sum of digits
        // prefixCount: non-zero digit count
        long[] prefixValue = new long[n];
        long[] prefixSum = new long[n];
        int[] prefixCount = new int[n];

        // first character
        int d0 = s.charAt(0) - '0';
        prefixValue[0] = d0 % MOD;
        prefixSum[0] = d0;
        prefixCount[0] = d0 == 0 ? 0 : 1;

        // build prefixes
        for (int i = 1; i < n; i++) {

            int digit = s.charAt(i) - '0';

            prefixCount[i] = prefixCount[i - 1];
            long value = prefixValue[i - 1];

            // append digit if non-zero
            if (digit != 0) {
                value = (value * 10) % MOD;
                prefixCount[i]++;
            }

            prefixValue[i] = (value + digit) % MOD;
            prefixSum[i] = prefixSum[i - 1] + digit;
        }

        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            // digits & sum inside [l..r]
            int cnt = prefixCount[r] - (l > 0 ? prefixCount[l - 1] : 0);
            long sum = prefixSum[r] - (l > 0 ? prefixSum[l - 1] : 0);

            // remove left prefix from right prefix
            long xr = prefixValue[r];
            long left = (l > 0) ? prefixValue[l - 1] : 0;
            long x = (xr - (left * pow10[cnt]) % MOD + MOD) % MOD;

            ans[i] = (int)((x * (sum % MOD)) % MOD);
        }

        return ans;
    }
}