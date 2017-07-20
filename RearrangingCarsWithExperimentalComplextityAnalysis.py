
# Same Code but with Experimental Complexity Analysis

'''
The map construction is obviously in O(N).
The for loop goes through N iterations. But
each iteration only consists of lookups in
dicts and arrays with constant lookup time.
So the for loop is in O(N).
The theoretical total complexity is:
O(N) '+' O(N) '=' O(N)
But I wanted to be sure that I 
didnt miss anything, so I also did an 
experimental time complexity analyis. 
One graph, that I calculated can be
found as a png in this gitHub rep. But feel
free to run this code yourself:)
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
# Testing & Complexity Analysis:
#################

import matplotlib.pyplot as plt
from matplotlib.font_manager import FontProperties
from scipy.odr import * 
def fit(title, x, y, x_err, y_err, f, colour = 'c', marker = '.', xlabel = '', ylabel = '', name = '', pNames = '', log = False, show = True, label = True, fitStart = 0, fitEnd = 0, fitOnly = False, startingValues = [0., 1.]): # f ist die Fitfunktion
	font = {'family': 'serif',
		'weight': 'normal',
		'size': 13,
		}
	if(not fitOnly):
		plt.errorbar(x, y, xerr=x_err, yerr=y_err, fmt = colour + marker, label = name + ' Data')
	
	if (fitEnd == 0):
		fitEnd = len(x)
	
	x = x[fitStart : fitEnd]
	x_err = x_err[fitStart : fitEnd]
	y = y[fitStart : fitEnd]
	y_err = y_err[fitStart : fitEnd]

	model = Model(f)
	data = RealData(x, y, sx=x_err, sy=y_err)

	odr = ODR(data, model, beta0 = startingValues)
	out = odr.run()

	print('--------------------')
	print('FittingInfo fuer ' + name)
	print('Fitparameter: ', end = '')
	print(pNames)
	out.pprint()
	print('--------------------')

	chisq = np.sum(((f(out.beta, x) - y)/y_err)**2)
	print('chisquare = ' + str(chisq))    
	dof = len(y) - len(startingValues)
	chisqRed = chisq / dof
	print('chisquare_red = ' + str(chisqRed))    
	from scipy.stats import chi2
	prob = round(1-chi2.cdf(chisq, dof ), 2)*100
	print('prop = ' + str(prob))    

	x_fit = np.linspace(x[0], x[-1], 1000)
	y_fit = f(out.beta, x_fit)

	plt.plot(x_fit, y_fit, colour + '-', label = name + ' Fit')
	
	plt.title(title, fontdict = font)
	plt.grid(True)
	if(label):
		plt.legend(prop = font)
	plt.xlabel(xlabel, fontdict = font)
	plt.ylabel(ylabel, fontdict = font)
	plt.ticklabel_format(style='sci', scilimits=(0,0))
	if (log):
		plt.yscale('log')
	if (show):
		plt.show()
def lin(P, x):
    return P[0]*x + P[1]
import unittest
import random
import time
import numpy as np
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
    def testComplexity(self):
        repetitions = 500
        N = 1
        Ns = []
        times = []
        while N < 5000:
            print(N)
            totalTime = 0.0
            for i in range(repetitions):
                start = self.createTestData(N)
                end = self.createTestData(N)
                startTime = time.time()
                rearrangingCars(start, end, printing = False)
                endTime = time.time()
                totalTime += (endTime-startTime)
            times.append(totalTime/repetitions)
            Ns.append(N)
            print('{0:.7f}'.format((totalTime/repetitions)))
            N += 500
        Ns = np.array(Ns)
        times = np.array(times)
        fit('Complexity for Rearranging Cars', Ns, times, np.sqrt(Ns), np.array([1e-4]*len(Ns)), lin, colour = 'b', xlabel = 'N', ylabel = 'time (s)')

if __name__ == '__main__':
    unittest.main()



