/*
 * Population 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Models a collection of circles roaming about impacting other circles.
 * @author Amy Larson (with Erik Steinmetz)
 */
public class Population {

	/** Population consists of collection of Person(s) */
    private ArrayList<Person> persons = new ArrayList<>();
    
    /** Size of population of all status */
    private int populationSize = 500;

    /** Pauses simulation then people do not move */
    private boolean paused = false;
    
    /** Count of population in each status (in enum Status) */
    HashMap<Status,Integer> statusCounts = new HashMap<>();
    
    Random random = new Random();

    /** Default constructor. */
  	public Population() {
    
     	/** Start with 0 persons with each health status */
 		for (Status s : Status.values()) {
 			System.out.println(s);
 			statusCounts.put(s,0);
 		}
     } 
    
    /** Create the population
    * @param panel Place all in this JPanel
    */
	private boolean initialInfectionSet = false; // Ensure only one person gets infected

	public void populate(PopulationPanel panel) {
		// Get movement level
		int movementLevel = MessageBoard.movement();
		// Populate each of the four areas equally
		for (int area = 0; area < 4; area++) {
			for (int i = 0; i < populationSize / 4; i++) {
				// Boundaries define the area in which they can move
				Person person = new Person(Layout.BOUNDARIES[area], this);
				//
				double probability = random.nextDouble();
				// If movement level is 1, 2, 3, or 4, move 75%, 50%, 25%, and 10% of the population
				if ((movementLevel == 1 && probability < 0.75) || // 75% 
                (movementLevel == 2 && probability < 0.50) || // 50% 
                (movementLevel == 3 && probability < 0.25) || // 25% 
                (movementLevel == 4 && probability < 0.10)) { // 10% 

					// set deltaX and deltaY to 0 so they don't move
                	person.deltaX(0); 
                	person.deltaY(0);
				}else {
					// set deltaX and deltaY to random numbers (-2 to 2)
					person.deltaX(random.nextInt(4) - 2);
                	person.deltaY(random.nextInt(4) - 2);
				}

				persons.add(person);
				panel.add(person);
				statusCounts.put(person.status(), statusCounts.get(person.status()) + 1);
			}
		}
		
		//Prevention Level
		int preventionLevel = MessageBoard.preventionlevel();
		
		//Probabilities
		double vaccinatedProbability = 0.0;
		
		//Setting probabilities
		if(preventionLevel == 1){
			vaccinatedProbability = 0.3;
		}else if(preventionLevel == 2){
			vaccinatedProbability = 0.6;
		}else if(preventionLevel == 3){
			vaccinatedProbability = 0.8;
		}else{
			vaccinatedProbability = 0.0;
		}
		
		for(int area = 0; area < 4; area++){
			for(int i = 0; i < populationSize / 4; i++){
				Person person = new Person(Layout.BOUNDARIES[area], this);
				
				if(random.nextDouble() < vaccinatedProbability){
					person.status(Status.HEALTHY_VACCINATED);
				}else{
					person.status(Status.HEALTHY_NOT_VACCINATED);
				}
				persons.add(person);
				panel.add(person);
				statusCounts.put(person.status(), statusCounts.get(person.status()) + 1);
			}
		}
		
		
		// Makes sure one person is selected and infected
		if (!persons.isEmpty() && !initialInfectionSet) {
			// Select a random person
			int randomIndex = random.nextInt(persons.size()); 
			Person infectedPerson = persons.get(randomIndex);
			//store the currenct stat
			Status previousStatus = infectedPerson.status(); 

			// Update the person's status to SYMPTOMATIC
			infectedPerson.status(Status.SYMPTOMATIC);
			//update the stats count
			statusChanged(previousStatus, Status.SYMPTOMATIC); 
			System.out.println("New status: " + infectedPerson.status());
			// Prevent further infections
			initialInfectionSet = true; 
		}
		// Update the message board 
		shareStats();
		// Set infection duration 
		Person.setDuration(MessageBoard.duration());
	}

	/** Execute a time step so that everyone moves accordingly */
	public void update() {
		// get new the transmission probability
		Integer newTransmissionProbability = MessageBoard.transmissionProbability();
		
		// if there is a new transmission probability
		if (newTransmissionProbability != null) {
			// update transmission probability in Person
			Person.setTransmissionProbability(newTransmissionProbability);
		}
		
		//Setting preventionLevel
		Integer preventionLevel = MessageBoard.preventionlevel();
		if(preventionLevel != null){
			Person.setpreventionLevel(preventionLevel);
		}
	
		for (Person person : persons) {
			person.move();
		}
		// Determine if anyone has crossed paths with another
		// potentially changing their status from healthy to asymptomatic
		checkForEncounters();
		shareStats();
	}
	
	/** Determine if any of the persons are crossing paths. */
	public void checkForEncounters() {
		for (Person person : persons) {
			// determine if person might change status of other
			// this can occur only if person is contagious and other is healthy
			if ( Status.HEALTHY_VACCINATED==person.status() || 
				 Status.HEALTHY_NOT_VACCINATED==person.status() ||
				 Status.HEALTHY_RECOVERED==person.status()) {
					continue;
			}
			for (Person other: persons) {
				// do not compare anyone to themselves
				if (person.equals(other)) {
					continue;
				}
				// if already infected, status cannot change
				if (Status.SYMPTOMATIC==other.status() ||
					Status.ASYMPTOMATIC==other.status()) {
					continue;
				}
				// calculate the distance to the center of each person.
				// if closer than 2*radius, they are overlapping
				// use Pythagoreans theorem
				int deltaX = person.getCenterX() - other.getCenterX();
				int deltaY = person.getCenterY() - other.getCenterY();
				double distance = Math.pow((deltaX*deltaX + deltaY*deltaY),0.5);
				if (distance < Person.SIZE()) {
					// they are overlapping, thus other is exposed to person
					other.exposed(person);
				}
			}
		}
	
	} // end checkforEncounters


	public void shareStats(){
		@SuppressWarnings("unchecked")
		HashMap<Status, Integer> cloned = (HashMap<Status, Integer>) statusCounts.clone();
    	MessageBoard.update(Message.STATS, cloned);
	}


	public void statusChanged(Status remove, Status add) {
		if (remove != null) {
			statusCounts.put(remove, statusCounts.get(remove) - 1);
		}
		if (add != null) {
			statusCounts.put(add, statusCounts.get(add) + 1);
		}
	}
	

    /** Pause the simulation - people no longer move. */
    public void pause() {
        paused = true;
    }

    /** Continue the simulation */
    public void play() {
        System.out.println("Playing now");
        paused = false;
    }


    public ArrayList<Person> getPeople() {
        return persons;
    }

}
