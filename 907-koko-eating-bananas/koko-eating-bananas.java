// class Solution {
//     public int minEatingSpeed(int[] piles, int h) {
//         int k;
//         int l = ;
//         int r

//         k<l
//         l=h+1;
//         r
//         k>l
//         l
//         r=h-1;


//         return k 
//     }
// }


class Solution {
    public int minEatingSpeed(int[] piles, int h) {
    int n = piles.length;
        long total = 0;
        for (int p : piles) 
            total += p;
        
        int left = (int) ((total - 1) / h) + 1;
        int right = (int) ((total - n) / (h - n + 1)) + 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int time = 0;
            for (int p : piles) {
                time += (p - 1) / mid + 1;
            }
            if (time > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}