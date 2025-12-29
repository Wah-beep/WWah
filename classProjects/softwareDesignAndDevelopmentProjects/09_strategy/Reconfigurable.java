public class Reconfigurable extends Robot{
	
	MoveBehavior moveBehavior;
	SenseBehavior senseBehavior;
	
	public Reconfigurable(String name, SenseBehavior sensing, MoveBehavior moving){
		super(name);
		super.senseBehavior = sensing;
		super.moveBehavior = moving;
	}
	
	//Setters
	public void setMoveBehavior(MoveBehavior moving){
		super.moveBehavior = moving;
	}
	
	public void setSenseBehavior(SenseBehavior sensing){
		super.senseBehavior = sensing;
	}
}