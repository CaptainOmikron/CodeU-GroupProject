
'''
COMPLEXITY ANALYSIS:
The map construction is obviously in O(N).
The for loop goes through N iterations. But
each iteration only consists of lookups in
dicts and arrays with constant lookup time.
So the for loop is in O(N).
The total complexity is:
O(N) '+' O(N) '=' O(N)
But I wanted to be sure that I 
didnt miss anything, so I also did an 
experimental time complexity analyis. You 
can find the code for that in the other
file. One graph, that I calculated can be
found as a png in this gitHub rep
'''
def rearrangingCars(start, end, printing = True):
    N = len(start)
    currentMap = dict() # keys are cars/empty spot, values are positions
    targetMap = dict()
    lot = start # saves the current lot configuration
    # fill Map: O(N)
    for i in range(N):
        currentMap[start[i]] = i
        targetMap[end[i]] = i 
    emptyLocation = currentMap[0]
    # now put all the cars into correct position one by one:
    for car in range(1, N): # *N
        currentLocation = currentMap[car]
        targetLocation = targetMap[car]
        if targetLocation != currentLocation:
            # move car in target position to empty:
            if targetLocation != emptyLocation:
                inTheWay = lot[targetLocation] 
                # update lot:
                lot[targetLocation] = 0
                lot[emptyLocation] = inTheWay
                # update currentMap:
                currentMap[inTheWay] = emptyLocation
                currentMap[0] = targetLocation
                # print:
                if printing:
                    print('move from ' + str(targetLocation) + ' to ' + str(emptyLocation))
                    print(lot)
                # update emptyLocation
                emptyLocation = targetLocation
            # move car to target:
            # update lot:
            lot[currentLocation] = 0
            lot[targetLocation] = car
            # update currentMap:
            currentMap[car] = targetLocation
            currentMap[0] = emptyLocation
            # print:
            if printing:
                print('move from ' + str(currentLocation) + ' to ' + str(targetLocation))
                print(lot)
            emptyLocation = currentLocation
    return lot

#################
# Testing:
#################
import unittest
import random
import time
class TestRearrangingCars(unittest.TestCase):
    def testRearrangingCars(self):
        size = 10
        repetitions = 100
        for i in range(repetitions):
            end = self.createTestData(size)
            start = self.createTestData(size)
            result = rearrangingCars(start, end, printing = False)
            self.assertTrue(result == end)
    def createTestData(self, N):
        a = list(range(N))
        random.shuffle(a)
        return a

if __name__ == '__main__':
    unittest.main()



