import copy
import math
import random

max_value = 15

class Smiley:
	goal = [['O',' ','O'],
        [' ','-',' '],
        [' ','V',' ']]

	characters = [' ','O','-','V']

	row_count = 3
	col_count = 3
    
    def print(self,pic):
        for row in pic:
            for char in row:
                print(char,end='')
            print()
    
    #not finish
    def make_random_starter(self):
        
        # create a list with 0 through max_value randomly ordered
        starter = [i for i in range(max_value+1)]
        
        # mix it up 
        for _ in range(max_value):
            i = random.randint(0,max_value)
            j = random.randint(0,max_value)
            temp = starter[i]
            starter[i] = starter[j]
            starter[j] = temp
            
        # set it to the instance variable
        self.starter = starter
    
    #not finish
    def cost(self,solution):
        cost = 0
        # TODO ... fill this in
        # add 1 when the solution is greater and not equal
        #for i in range(len(solution)-1):
        #    if solution[i] > solution[i+1]:
        #        cost += 1
        for i in range(len(solution)-1):
            if solution[i] > solution[i+1]:
                cost += 1
        return cost
        
    #not finish
    def generate_neighbor(self,solution):
        neighbor = solution.copy()
        i = random.randint(0,max_value)
        j = random.randint(0,max_value)
        temp = neighbor[i]
        neighbor[i] = neighbor[j]
        neighbor[j] = temp
        return neighbor