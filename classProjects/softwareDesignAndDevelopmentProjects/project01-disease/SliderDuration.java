import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderDuration extends SliderPanel {

    public SliderDuration(int row, int column) {
        super(row, column);
        
        // Set the title
        title.setText("Duration");
        
        // Access the slider and set its range to 1-10
        JSlider slider = (JSlider) getComponent(0);
        slider.setMinimum(1);
        slider.setMaximum(10);
        slider.setValue(5); // Default value
        
        // Remove the existing listener to replace it
        slider.removeChangeListener(sliderListener);
        
        // Add new listener to update value label and MessageBoard
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                getvalueLabel().setText(Integer.toString(value));
                MessageBoard.update(Message.DURATION, value);
            }
        });
    }
}