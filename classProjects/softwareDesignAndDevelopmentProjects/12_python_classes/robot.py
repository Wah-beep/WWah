import math
import random

class Robot:
    '''
    This robot moves around in an environment with pits and a prize.
    If it comes across a pit, it will not be alive any more.
    If it comes across the prize, it is a winner.
    A robot is at a location = [x,y]
    '''

    # static variables for the width and height of its environment
    env_width = 0
    env_height = 0

    # max speed in any direction
    max_speed = 5

    def __init__(self, x=0, y=0, radius=15):
        
        self.location = [x,y]
        self.radius = radius
        
        # randomly set change in direction along x and y at each time step
        self.delta = [random.randint(-self.max_speed,self.max_speed),random.randint(-self.max_speed,self.max_speed)]
        
        if self.delta == [0,0]:
            self.delta = [1,1]

        # we are alive thus far
        self.alive = True

        self.gold = False

        

    def move(self):
        '''
        Move the robot based on its delta values (change in x and y)
        Check if it reached the edge of its environment.
        If at the edge, bounce off the wall.
        '''
        #print("Environment: ["+str(env_width)+","+str(env_height)+"]")

        # move in x-direction (i.e. delta[0])
        self.location[0] += self.delta[0]

        # bounce off wall if at the left or right edge
        # robot bounces by reversing direction of delta x
        # >>> TO DO
        if (self.location[0] >= env_width/2) or (self.location[0] <= -env_width/2) :
            self.delta[0] = -self.delta[0]
            #print('x '+str(self.location[0]))

        # move in y-direction (i.e. delta[1])
        self.location[1] += self.delta[1]

        # bounce off wall if at the top or bottom edge
        # robot bounces by reversing direction of delta y
        # >>>> TO DO
        if (self.location[1] >= env_height/2) or (self.location[1] <= -env_height/2) :
            self.delta[1] = -self.delta[1]
            #print('y '+str(self.location[1]))


        

    def not_alive(self):
        self.delta = [0,0]
        self.alive = False


    def found_gold(self):
        
        
        self.delta = [0,0]
        self.gold = True

        
        
