import sortedcontainers
class MKAverage:

    def __init__(self, m: int, k: int):
        self.all_elements = []
        self.bst = sortedcontainers.SortedList()
        self.m = m
        self.k = k
        self.m_sum = 0

    def addElement(self, num: int) -> None:
        self.all_elements.append(num)
        self.m_sum += num
        self.bst.add(num)
        if len(self.bst) == self.m+1:
            self.bst.remove(self.all_elements[-(self.m+1)])
            self.m_sum -= self.all_elements[-(self.m+1)]
        
        

    def calculateMKAverage(self) -> int:
        if len(self.all_elements) < self.m:
            return -1
        min_sum = sum(self.bst[:self.k])
        max_sum = sum(self.bst[-self.k:])
        return (self.m_sum-min_sum-max_sum) // (self.m-2*self.k)

# Your MKAverage object will be instantiated and called as such:
# obj = MKAverage(m, k)
# obj.addElement(num)
# param_2 = obj.calculateMKAverage()