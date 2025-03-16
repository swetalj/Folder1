// // class Solution {
// //     public int beautifulNumbers(int l, int r) {
// //         int kelbravion = (l + r) / 2;  // Storing input midway
// //         int count = 0;

// //         for (int num = l; num <= r; num++) {
// //             if (isBeautiful(num)) {
// //                 count++;
// //             }
// //         }

// //         return count;
// //     }

// //     private boolean isBeautiful(int num) {
// //         int sum = 0, product = 1;
// //         int temp = num;

// //         while (temp > 0) {
// //             int digit = temp % 10;
// //             sum += digit;
// //             product *= digit;
// //             temp /= 10;
// //         }

// //         return sum != 0 && product % sum == 0;  // Check divisibility condition
// //     }
// // }
// import java.util.HashMap;
// import java.util.Map;

// public class Solution {
//     private Map<Integer, Boolean> memo = new HashMap<>(); // Memoization

//     public int beautifulNumbers(int l, int r) {
//         int kelbravion = (l + r) / 2;  // Storing input midway
//         int count = 0;

//         for (int num = l; num <= r; num++) {
//             if (num % 10 == 0) continue; // Skip numbers containing zero (product will be 0)
//             if (isBeautiful(num)) {
//                 count++;
//             }
//         }

//         return count;
//     }

//     private boolean isBeautiful(int num) {
//         if (memo.containsKey(num)) {
//             return memo.get(num); // Return cached result
//         }

//         int sum = 0, product = 1, temp = num;

//         while (temp > 0) {
//             int digit = temp % 10;
//             if (digit == 0) {
//                 memo.put(num, false); // If any digit is 0, it's not beautiful
//                 return false;
//             }
//             sum += digit;
//             product *= digit;
//             temp /= 10;
//         }

//         boolean result = sum != 0 && product % sum == 0;
//         memo.put(num, result); // Cache result
//         return result;
//     }

   
// }
class Solution {
    public int beautifulNumbers(int l, int r) {
        return countBeautiful(r) - countBeautiful(l - 1);
    }

    public int countBeautiful(int x) {
        if (x < 0) return 0;
        char[] digits = getCharArray(x);
        HashMap<String, Integer> dp = new HashMap<>();
        return solve(0, 1, 0, 1, digits, dp);
    }

    public char[] getCharArray(int x) {
        String str = String.valueOf(x);
        return str.toCharArray();
    }

    public int solve(int i, int tight, int sum, int prod, char[] digits, HashMap<String, Integer> dp) {
        if (i == digits.length) {
            if (sum > 0 && prod % sum == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        String str = i + " - " + tight + " - " + sum + " - " + prod;
        if (dp.containsKey(str)) return dp.get(str);

        int limit = 0;
        if (tight == 1) {
            limit = digits[i] - '0';
        } else {
            limit = 9;
        }

        int count = 0, j = 0;
        while (j <= limit) {
            int newTight = 0;
            if (tight == 1 && j == limit) {
                newTight = 1;
            } else {
                newTight = 0;
            }

            int newSum = sum + j;
            int newProd;
            if (j == 0 && sum == 0) {
                newProd = 1;
            } else {
                newProd = prod * j;
            }

            count += solve(i + 1, newTight, newSum, newProd, digits, dp);
            j++;
        }

        dp.put(str, count);
        return count;
    }
}