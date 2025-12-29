# https://www.geeksforgeeks.org/python-turtle-screen-setup-method/#
import turtle

class Display:
    '''
    Turtle graphics window for displaying the robot environment.
    Each robot is a turtle that redraws as its location is updated.
    Pits are stationary - drawn as boxes 1 time.
    Gold is also stationary and drawn one time.
    '''

    def __init__(self, env):
        # The environment being displayed
        self.env = env

        # Create the graphics window
        self.screen = turtle.Screen()
        self.screen.setup(env.width, env.height)
        self.screen.bgcolor("gray")
        

        self.turtle = turtle.Turtle()
        self.turtle.hideturtle()
        self.turtle.speed(0)

        # Create a list of robots that will be dynamically updated
        self.robots = []

    def draw(self):
        ''' Draws the environment objects. '''
        
        # Draw pits
        for pit in self.env.pits:
            self.draw_pit(pit)

        # Draw gold if it exists
        for gold in self.env.golds:
            self.draw_gold(gold)

        # Create a turtle robot to correspond to each robot in the environment
        for robot in self.env.robots:
            trobot = turtle.Turtle(shape="circle", visible=False)
            trobot.speed(0)
            trobot.shape("circle")
            trobot.color("green")
            trobot.penup()  
            trobot.goto(robot.location[0], robot.location[1])

            trobot.showturtle()
            self.robots.append([robot, trobot])

        

    def update(self):
        '''
        relocate, thus redraw each "turtle" that corresponds to a robot in the environment.
        The list of robot pairs are in self.robots.
        The robots move within the environment and are redisplayed here.
        '''
        
        # time to redraw the turtles (because the robots in the environment moved)
        for robot in self.robots:
            
            # if fell in a pit and not alive ...
            if not robot[0].alive:
                robot[1].color("red")
                continue # move on to the next one
            # if found a pot of gold...
            if robot[0].gold:
                robot[1].color("blue")
            # move the turtle robot to the new location of the corresponding robot in the environment
            robot[1].setx(robot[0].location[0])
            robot[1].sety(robot[0].location[1])


    def draw_pit(self, pit):
        ''' Draw a square pit. The location specifies the lower left corner. '''
        location = pit.location

        pen = self.turtle
        pen.penup()
        pen.goto(location[0], location[1])
        pen.pendown()

        pen.fillcolor("black")

        # Draw bottom, right, top, left edges
        pen.begin_fill()
        pen.goto(location[0] + pit.size, location[1])
        pen.goto(location[0] + pit.size, location[1] + pit.size)
        pen.goto(location[0], location[1] + pit.size)
        pen.goto(location[0], location[1])
        pen.end_fill()

    def draw_gold(self, gold):
        ''' Draws a gold piece as a square '''

        location = gold.location
        size = gold.size

        pen = self.turtle
        pen.penup()
        pen.goto(gold.location[0], gold.location[1])
        pen.color(gold.color)
        pen.begin_fill()

        # Draw square representing gold
        pen.goto(gold.location[0] + gold.size, gold.location[1])
        pen.goto(gold.location[0] + gold.size, gold.location[1] + gold.size)
        pen.goto(gold.location[0], gold.location[1] + gold.size)
        pen.goto(gold.location[0], gold.location[1])

        pen.end_fill()