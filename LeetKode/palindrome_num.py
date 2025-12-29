class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        if x < 0:
            return False
        
        originalNum = x
        reversedNum = 0

        while x > 0:
            lastDigit = x % 10
            reversedNum = (reversedNum * 10) + lastDigit
            x //=10
        return originalNum == reversedNum


sol = Solution()
result = sol.isPalindrome(121)
print(result)