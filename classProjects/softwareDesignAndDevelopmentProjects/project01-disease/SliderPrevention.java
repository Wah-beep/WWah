import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

public class SliderPrevention extends SliderPanel{
	
	public SliderPrevention(int row, int column){
		
		super(row, column);
		
		//Title
		title.setText("Prevention");
		
		//Slider
		JSlider slider = (JSlider) getComponent(0);
		
		slider.setMinimum(0);
		slider.setMaximum(3);
		slider.setValue(0);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		//changes in slider value
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e){
				int value = slider.getValue();
				getvalueLabel().setText(String.valueOf(value));
				
				MessageBoard.update(Message.PREVENTION, value);
			}
		});
	}
}