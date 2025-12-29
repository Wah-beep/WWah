from typing import List

class Solution(object):
    def removeDuplicates(self, nums: List[int]) -> int:
        i = 1

        for j in range(1, len(nums)):
            if nums[j] != nums[i - 1]:
                nums[i] = nums[j]
                i += 1
        
        return i
    
    def removeDuplicates_my(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        seen = {}
        expectedNums = []
        for num in nums:
            if num not in seen:
                seen[num] = True
                expectedNums.append(num)
        for i in range(len(nums)):
            expectedNums.append("_")
        
        return expectedNums

list1 = [1,1,2,3,3,4]
sol = Solution()
result = sol.removeDuplicates(list1)
print(result)