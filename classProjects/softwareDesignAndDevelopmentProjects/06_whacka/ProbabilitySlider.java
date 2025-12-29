import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ProbabilitySlider extends JPanel {

    private final JSlider slider;
    private final GameViewingPanel gameView;


    public ProbabilitySlider(GameViewingPanel gameView){
        this.gameView = gameView;

        // setting up slider
        slider = new JSlider(JSlider.HORIZONTAL, 20, 100, 50);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // add a label
        JLabel label = new JLabel("Cue Probability");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // set up the layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(slider);

        // add a change listener
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                double probability = value / 100.0;
                gameView.cueProbability(probability);
            }
        });
    }
}

