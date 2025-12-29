import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderIncubation extends SliderPanel {
    
    public SliderIncubation(int x, int y) {
        super(x, y);
        
        // Set title correctly
        title.setText("Incubation Period");
        
        // Configure slider range directly
        JSlider slider = getSlider(); // Use the new getter method
        slider.setMinimum(1);
        slider.setMaximum(7);
        slider.setValue(2); // Default incubation period

        // Update MessageBoard when the slider moves
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                getvalueLabel().setText(String.valueOf(value));
                MessageBoard.update(Message.INCUBATION, value);
            }
        });
    }
}
