public class OmniTouch extends Robot{
	public OmniTouch(String name){
		super(name);
		this.moveBehavior = new MoveOmni();
		this.senseBehavior = new SenseTouch();
	}
	
}

