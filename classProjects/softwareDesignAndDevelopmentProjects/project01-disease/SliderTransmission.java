import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderTransmission extends SliderPanel {

    public SliderTransmission(int row, int column) {
        super(row, column);
        
        // Set the title
        title.setText("Transmission");
        
        // Access the slider and set its range to 0-100
        JSlider slider = (JSlider) getComponent(0);
        slider.setMinimum(0);
        slider.setMaximum(100);
        slider.setValue(50); 
        
        // Remove the existing listener to replace it
        slider.removeChangeListener(sliderListener);
        
        // Add new listener to update value label and MessageBoard
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                getvalueLabel().setText(Integer.toString(value));
                MessageBoard.update(Message.TRANSMISSION, value);
            }
        });
    }
// Adding SliderTransmission to ControlPanel at location 0
public class ControlPanel extends JPanel {
    public ControlPanel() {
        SliderTransmission sliderTransmission = new SliderTransmission(0, 0);
        add(sliderTransmission, 0);
        }

}
}

