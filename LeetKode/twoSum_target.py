class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        hashMap = {}
        for i, val in enumerate(nums):
            completed = target - val
            if completed in hashMap:
                return hashMap[completed], i
            hashMap[val] = 1
        return -1, -1

sol = Solution()
nums = [2,4,5,3]
target = 7
result = sol.twoSum(nums, target)
print(result)
        