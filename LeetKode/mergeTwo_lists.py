from typing import Optional

# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# Outside of class Solution, doesn't need @staticmethod
def print_linked_list(node):
    vals = []
    while node:
        vals.append(node.val)
        node = node.next
    print(vals)
class Solution:

    # Need @staticmethod becuase it is inside of class Solution
    @staticmethod
    def build_linked_list(values):
        dummy = ListNode()
        curr = dummy
        for v in values:
            curr.next = ListNode(v)
            curr = curr.next
        return dummy.next

    # Solving
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        dummy = ListNode()
        curr = dummy

        while list1 and list2:
            if list1.val > list2.val:
                curr.next = list2
                list2 = list2.next
            else:
                curr.next = list1
                list1 = list1.next

            curr = curr.next

        if list1:
            curr.next = list1
        else:
            curr.next = list2

        return dummy.next

# Test and Print
sol = Solution()
list1 = sol.build_linked_list([1, 2, 4]) # Building linked list of nodes
list2 = sol.build_linked_list([1, 3, 4]) # Building linked list of nodes
result = sol.mergeTwoLists(list1, list2)
print_linked_list(result)