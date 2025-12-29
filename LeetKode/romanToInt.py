class Solution(object):
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        roman = {
            "I": 1,
            "V": 5,
            "X": 10,
            "L": 50,
            "C": 100,
            "D": 500,
            "M": 1000
        }
        totalInt = 0
        prev_value = 0
        for char in reversed(s):
            value = roman[char]
            if value < prev_value:
                totalInt -= value
            else:
                totalInt += value
                prev_value = value
        return totalInt
            
sol = Solution()
result = sol.romanToInt("MCMXCIV")
print(result)