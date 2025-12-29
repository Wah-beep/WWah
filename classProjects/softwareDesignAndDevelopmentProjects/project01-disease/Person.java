import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.*;
import java.util.*;

import java.util.Random;

/** Circle for drawing in a JFrame
 *
 * @author Amy Larson
 */
public class Person extends JPanel {

	private static int days = 0;

	private static int duration = 1;

	private static int incubationPeriod = 2;

	private int dayInfected = 0;;

	/** diameter of the circle */
	private static final int SIZE = 5;
	
	/** Population of which this person is part */
	private Population population;
	
	private static Random random = new Random();
	
	/** Box inside of which this person can move */
	private BoundingBox borders;
	
	/** Health status of the person. From enum Status */
	Status status = Status.HEALTHY_VACCINATED;
	
	/** Position (column) relative to the origin of the population panel */
	private int locationX = 0;
	
	/** Position (row) relative to the origin of the population panel */
	private int locationY = 0;
	
	/** Movement along the x-axis at each time step */
	private int deltaX = 0;
	
	/** Movement along the y-axis at each time step */
	private int deltaY = 0;

	/** Probability of transmission set to 10 */
	private static int transmissionProbability = 10;
	
	//preventionLevel set to 0
	private static int preventionLevel = 0;

	/** Setter for transmission probability */
	public static void setTransmissionProbability(int probability) {
		transmissionProbability = probability; 
	}

	public static void setIncubationPeriod(int period) {
        incubationPeriod = period;
    }

	/** Default constructor */
    public Person(BoundingBox borders, Population pop) {

        setLayout(null);
        
        this.borders = borders;
        this.population = pop;
        
        // get a random location from within their border
        locationX = borders.getRandomX();
        locationY = borders.getRandomY();

		// randomly place each person
    	setBounds(locationX, locationY, SIZE, SIZE);
		//System.out.println("created with bounds: " + getBounds()); 
		
		// randomly select rate of movement per time step
		deltaX = random.nextInt(4) - 2;		// range -2 to 2
		deltaY = random.nextInt(4) - 2;
		
		setVisible(true);
    }
    
    /** Move person based on their deltas along X and Y. */
    public void move() {
    
    	// change in each direction
    	locationX += deltaX;
    	locationY += deltaY;
    	
    	// determine if up against a border and need to turn around
    	if (locationX < borders.left) {
    		locationX = borders.left + SIZE;
    		deltaX = -deltaX;
    	} else if (locationX > borders.right) {
    		locationX = borders.right - SIZE;
    		deltaX = -deltaX;
    	}
    	// check if y (row) is out of bounds
    	if (locationY < borders.top) {
    		locationY = borders.top + SIZE;
    		deltaY = -deltaY;
    	} else if (locationY > borders.bottom) {
    		locationY = borders.bottom - SIZE;
    		deltaY = -deltaY;
    	}
    	
    	setLocation(locationX,locationY);

		if (status == Status.ASYMPTOMATIC && (days - dayInfected) >= duration) {
			Status oldStatus = this.status;
			this.status = Status.HEALTHY_RECOVERED;
			population.statusChanged(oldStatus, Status.HEALTHY_RECOVERED);
		}
        }
    
    
    /** 
    * This person has been exposed to an infected individual. Determine if infection has been transmitted.
    * @param other Person encountered that might infect this person.
    */
    public void exposed(Person other) {
		// If the other person is asymptomatic or symptomatic
		if (other.status == Status.ASYMPTOMATIC || other.status == Status.SYMPTOMATIC) {
			// if this person is healthy
			if (status == Status.HEALTHY_VACCINATED || status == Status.HEALTHY_NOT_VACCINATED){
				if (preventionLevel == 1) {
					transmissionProbability *= 0.25; 
				} else if (preventionLevel == 2) {
					transmissionProbability *= 0.50; 
				} else if (preventionLevel == 3) {
					transmissionProbability *= 0.75; 
				}
				// determine if infected or not
				if (Math.random() < transmissionProbability) {
					// saves old status before changing it
					Status oldStatus = this.status;
	
					// updates status to symptomatic
					this.status = Status.SYMPTOMATIC;
					dayInfected = MessageBoard.days();
	
					// updates population
					population.statusChanged(oldStatus, Status.SYMPTOMATIC);
				}
			}
		}
    }
    // part of Java Graphics. Called when repaint() is called.
    // this draws the circle inside the Person panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(status.color);
        g.fillOval(0, 0, SIZE, SIZE);
    }

    // ______________________ SETTERS and GETTERS
	
	//Getter for preventionLevel
	public static int getpreventionLevel(){
		return preventionLevel;
	}
	
	//Setter for preventionLevel
	public static void setpreventionLevel(int value){
		preventionLevel = value;
	}
    
    public void borders(BoundingBox box) {
    	borders = box;
    }
        
    public int getCenterX() {
    	return (locationX + SIZE)/2;
    }
    public int getCenterY() {
    	return (locationY + SIZE)/2;
    }
    public static int SIZE() {
    	return SIZE;
    }
    
    public Status status() {
    	return status;
    }
    
    public void status(Status s) {
    	status = s;
    }
    
    public void deltaX(int dX) {
    	deltaX = dX;
    }
    
    public void deltaY(int dY) {
    	deltaY = dY;
    }

	public static void setDays(int d) {
		days = d;
	}
	public static int getDays() {
		return days;
	}

	public static void setDuration(int d) {
		duration = d;
	}
	public static int getDuration() {
		return duration;
	}
    

}