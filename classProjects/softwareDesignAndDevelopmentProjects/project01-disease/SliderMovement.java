import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderMovement extends SliderPanel {
    public SliderMovement(int row, int column) {
        super(row, column);

        // Set the title
        title.setText("Movement");

        // Set up the slider
        JSlider slider = (JSlider) getComponent(0);
        slider.setMinimum(1);
        slider.setMaximum(4);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
		slider.setPaintLabels(true);
        slider.setFont(new Font("Verdana", Font.PLAIN, 12));

        // Removes the existing listener
        slider.removeChangeListener(sliderListener);

        // Adds new listener to update value label and MessageBoard
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                getvalueLabel().setText(Integer.toString(value));
                MessageBoard.update(Message.MOVEMENT, value);
            }
        });

    }
}
    

