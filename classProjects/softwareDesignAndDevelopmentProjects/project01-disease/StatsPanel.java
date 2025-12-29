import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.*;
import java.util.*;


public class StatsPanel extends JPanel {

	JLabel heading;
	JLabel infectedLabel, recoveredLabel, deceasedLabel, daysLabel;


	public StatsPanel() {
			
		setBounds( //0,50,100,100);
			Layout.STATS_PANEL_X, Layout.STATS_PANEL_Y,
			Layout.STATS_PANEL_W, Layout.STATS_PANEL_H
		);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		heading = new JLabel("Heading");
		heading.setText("Health Statistics of Population");
		heading.setBounds(Layout.STATS_PANEL_W/2-100,15,300,50);
		add(heading);

		//add the new labels
		infectedLabel = new JLabel("Currently Infected: 0%", SwingConstants.CENTER);
		infectedLabel.setBounds(Layout.INFECTED_LABEL_X, Layout.INFECTED_LABEL_Y, Layout.START_W, Layout.START_H);
		
        recoveredLabel = new JLabel("Recovered: 0%", SwingConstants.CENTER);
		recoveredLabel.setBounds(Layout.RECOVERED_LABEL_X, Layout.RECOVERED_LABEL_Y, Layout.START_W, Layout.START_H);
		
        deceasedLabel = new JLabel("Deceased: 0%", SwingConstants.CENTER);
		deceasedLabel.setBounds(Layout.DECEASED_LABEL_X, Layout.DECEASED_LABEL_Y, Layout.START_W, Layout.START_H);
		
        daysLabel = new JLabel("Days: 0", SwingConstants.CENTER);
		daysLabel.setBounds(Layout.DAYS_LABEL_X, Layout.DAYS_LABEL_Y, Layout.START_W, Layout.START_H);
		
        
        add(infectedLabel);
        add(recoveredLabel);
        add(deceasedLabel);
        add(daysLabel);
		
		setVisible(true);
	}
	//update the ststspanel with real time statics from message board
	public void update() {

		HashMap<Status, Integer> stats = MessageBoard.stats();
        int days = MessageBoard.days();

        int totalPopulation = stats.values().stream().mapToInt(Integer::intValue).sum();
        
        if (totalPopulation == 0) return;
        
        int infected = stats.getOrDefault(Status.ASYMPTOMATIC, 0) + stats.getOrDefault(Status.SYMPTOMATIC, 0);
        int recovered = stats.getOrDefault(Status.HEALTHY_RECOVERED, 0);
        int deceased = stats.getOrDefault(Status.NOT_ALIVE, 0);
        
        infectedLabel.setText("Currently Infected: " + (infected * 100 / totalPopulation) + "%");
        recoveredLabel.setText("Recovered: " + (recovered * 100 / totalPopulation) + "%");
        deceasedLabel.setText("Deceased: " + (deceased * 100 / totalPopulation) + "%");
        daysLabel.setText("Days: " + days);
    }
}