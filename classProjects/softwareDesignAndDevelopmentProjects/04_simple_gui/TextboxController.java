import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Primary display for the Game
* Components for the display are created within the JFrame.
* Some of these components are shared with the Game
* @author Amy Larson
*/
public class TextboxController {
	
	/** The Java graphics frame/window in which the controls will be placed */
	JFrame frame;
	
	// Define the locations of the components within the frame
	final private int leftColumn = 50;
	final private int rightColumn = 600;
	final private int topRow = 100;
	final private int bottomRow = 400;

	/**
	* Constructor.
	* @param frame in which play button controller components will display.
	*/
	public TextboxController(JFrame frame) {
		this.frame = frame;
	}

	/** text box for user-defined control */
	Integer textboxValue = 0;
	protected final JTextField textbox = new JTextField(textboxValue);
	protected final JButton textSubmitButton = new JButton("submit");
	protected final JLabel textboxLabel = new JLabel(textboxValue.toString());
	protected final JLabel textboxLabe2 = new JLabel("Enter a value between 0 and 100");
	
	/**
	* Configures the graphics components and places in the frame.
	* @param column (location) within the graphics window for left edge.
	* @param row (location) within the graphics window for top edge.
	*/	
	public void setInFrame(int column, int row) {
	
		// __________________________________________________________
		// __________________________________________________________
		// An alternative design for a control component.
		// Rather than a new class, the components are part of the frame
		
		// Set up text box for user to enter value Lower Left
		textbox.setBounds(leftColumn,bottomRow,100,100);		
		textbox.setFont(new Font("Verdana", Font.PLAIN, 36));
		frame.getContentPane().add(textbox);
		
		textSubmitButton.setBounds(leftColumn+150,bottomRow,100,100);
		textSubmitButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		// Control the display of the button (so it is a solid filled color)
		textSubmitButton.setOpaque(true);
		textSubmitButton.setContentAreaFilled(true);
		textSubmitButton.setBorderPainted(false);
		textSubmitButton.setFocusPainted(false);
		textSubmitButton.setBackground(new Color(187,133,136));		
		textSubmitButton.addActionListener(submitListener);
		frame.getContentPane().add(textSubmitButton);
		
		textboxLabel.setFont(new Font("Verdana", Font.PLAIN, 36));
		textboxLabel.setBounds(leftColumn, bottomRow+110, 100, 100);
        frame.getContentPane().add(textboxLabel);
		
		//Added the text to the user to enter between 0-100
		//It is not displaying well. It doesn't show the whole sentence
		textboxLabe2.setFont(new Font("Verdana", Font.PLAIN, 16));
		textboxLabe2.setBounds(leftColumn, topRow+200, 100, 100);
        frame.getContentPane().add(textboxLabe2);
		// __________________________________________________________
		// __________________________________________________________
	} // end setInFrame
	
	/** Control function for the text box */
	// this both defines the class and declares an instance
	ActionListener submitListener = new ActionListener() {

		/**
		* Performs the actions for each of the JButtons
		* @param ae The event which occurred, identifying which button was pushed.
		*/
		@Override
		public void actionPerformed(ActionEvent ae) {
			System.out.println("Clicked on submit.");

			if (textbox.getText().isEmpty()) {
				return;
			}else{
				//if in range
				textboxValue = Integer.parseInt(textbox.getText());
				if(textboxValue>=0 && textboxValue<=100){
					//Setting & Displaying in label box
					textboxLabel.setText(textboxValue.toString());
				}
				return;
			}
		}
	}; // end submitListener
} // end class TextboxController
