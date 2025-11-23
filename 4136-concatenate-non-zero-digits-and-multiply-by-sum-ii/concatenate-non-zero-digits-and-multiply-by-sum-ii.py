class Solution:
    def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        mod = 10**9 + 7
        
        # prefix sum of digits
        pref_sum = [0]
        for ch in s:
            pref_sum.append(pref_sum[-1] + int(ch))
        
        # prefix concatenation of non-zero digits
        num = [0]      # number formed by non-zero digits
        count = [0]    # count of non-zero digits
        for ch in s:
            if ch == '0':
                num.append(num[-1])
                count.append(count[-1])
            else:
                num.append((num[-1] * 10 + int(ch)) % mod)
                count.append(count[-1] + 1)
        
        # precompute powers of 10
        max_len = count[-1]
        pow10 = [1] * (max_len + 1)
        for i in range(1, max_len + 1):
            pow10[i] = (pow10[i-1] * 10) % mod
        
        res = []
        for l, r in queries:
            # sum of digits
            total_sum = pref_sum[r+1] - pref_sum[l]
            
            # x: concatenated non-zero digits in s[l..r]
            nonzero_len = count[r+1] - count[l]
            x = (num[r+1] - num[l] * pow10[nonzero_len]) % mod
            
            res.append((x * total_sum) % mod)
        
        return res