import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The ControlPanel class represents a GUI panel that provides game controls,
 * including a start button, a play clock display, and sliders for color change speed
 * and probability adjustments.
 */
public class ControlPanel extends JPanel {

    private final int W_PANEL = 600; // Width of the panel
    private final int H_PANEL = 300; // Height of the panel

    private final JButton go = new JButton("Go"); // Button to start the game

    private final JLabel labelForPlayClock = new JLabel("Time Remaining"); // Label for the play clock
    protected final JLabel playClockDisplay = new JLabel("0"); // Displays the remaining time
    
    private ColorChangeSpeedSlider colorSpeed; // Slider to control color change speed
    
    private GameClockControl playClock; // Game clock control instance
    
    private GameViewingPanel gameView; // Game viewing panel instance

    private ProbabilitySlider probabilitySlider; // Slider to adjust probability

    /**
     * Constructs a ControlPanel with specified parameters.
     * 
     * @param row The vertical position of the panel.
     * @param clock The game clock control.
     * @param gameView The game viewing panel.
     */
    public ControlPanel(Integer row, GameClockControl clock, GameViewingPanel gameView) {
        this.playClock = clock;
        this.gameView = gameView;

        setBounds(10, row, W_PANEL, H_PANEL);
        setLayout(null);

        // Place the play clock label and value
        labelForPlayClock.setBounds(20, 50, 150, 30);
        this.add(labelForPlayClock);
        playClockDisplay.setBounds(150, 50, 80, 30);
        this.add(playClockDisplay);
        
        // Initialize and add the color speed slider
        colorSpeed = new ColorChangeSpeedSlider(playClock, "Speed");
        colorSpeed.setInFrame(175, 180);
        this.add(colorSpeed);

        // Configure and add the Go button
        go.setOpaque(true);
        go.setContentAreaFilled(true);
        go.setBorderPainted(false);
        go.setFocusPainted(false);
        go.setBackground(Color.GREEN);
        go.setBounds(40, 100, 120, 30);
        go.addActionListener(goControl);
        this.add(go);

        // Initialize and add the probability slider
        probabilitySlider = new ProbabilitySlider(gameView);
        probabilitySlider.setBounds(200, 100, 300, 50);
        this.add(probabilitySlider);

        setVisible(true);
    } // end ControlPanel()

    /**
     * Handles the game over state by resetting the Go button color.
     */
    public void gameOver() {
        go.setBackground(Color.GREEN);
    }

    /**
     * Updates the time display with the remaining seconds from the game clock.
     */
    public void updateTimeDisplay() {
        playClockDisplay.setText(playClock.secondsRemaining().toString());
    }

    /**
     * ActionListener for the Go button, managing the game start functionality.
     */
    ActionListener goControl = new ActionListener() {

        /**
         * Performs the actions for the Go button.
         * 
         * @param ae The event that occurred, identifying which button was pressed.
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Ignore action if a game is already active
            if (!playClock.isPlayActive()) {
                go.setBackground(Color.GRAY);
                go.setForeground(new Color(59, 59, 59));
                playClock.setPlayActive();
            }
        }
    }; // end goControl

} // end class ControlPanel

