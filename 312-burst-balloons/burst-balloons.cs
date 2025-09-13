public class Solution {
    //Dictionary<(int left, int right), int> dp;
    int[,] dp;
    public int MaxCoins(int[] nums) {
        List<int> numList = new List<int>();
        numList.Add(1);
        numList.AddRange(nums.ToList());
        numList.Add(1);
        //dp = new();
        dp = new int[numList.Count, numList.Count];
        return FindMaxCoins(numList, 1, numList.Count-2);
    }
    private int FindMaxCoins(IList<int> nums, int left, int right)
    {
        if(left > right)
        {
            return 0;
        }
        // if(dp.ContainsKey((left, right)))
        // {
        //     return dp[(left, right)];
        // }
        if(dp[left, right] != 0)
        {
            return dp[left, right];
        }
        int coins=0, maxCoins=0;
        for(int i=left; i<=right; i++)
        {
            coins = nums[left-1] * nums[i] * nums[right+1];
            coins += FindMaxCoins(nums, left, i-1) + FindMaxCoins(nums, i+1, right);
            maxCoins = Math.Max(maxCoins, coins);
        }
        dp[left, right] = maxCoins;
        return maxCoins;
    }
}