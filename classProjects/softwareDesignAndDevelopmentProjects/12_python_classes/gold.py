import random

class Gold:
    def __init__(self, x=0, y=0, size=12):
        self.location = [x,y]
        self.size = size
        self.color = "gold"

    def make_random(self,width,height):
        # determine random location within the environment
        w_half = (int) (width/2)
        h_half = (int) (height/2)
        self.location = [ random.randint(-w_half, w_half-self.size),
                          random.randint(-h_half, h_half-self.size) ]

        # random sized pit
        self.size = random.randint(12,30)
        
        # calculate x,y of middle of square (used to determine if robot overlaps pit)
        self.middle = [(int)(self.location[0]+self.size/2), (int)(self.location[1]+self.size/2)]

        # returning self (using the Builder pattern)
        return self
