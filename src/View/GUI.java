package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUI extends JFrame{
	JPanel mainPanel,leftPanel,rightPanel;
	InputPanel input_panel;
	OutputPanel output_panel;
	ActionPanel action_panel;
	Font f;
	Dimension screenSize;
	GridBagConstraints gbc;
	GridBagLayout gbag;
	JScrollPane input_scrollpane,output_scrollpane;
	
	public GUI(String s) {
		
		super(s);
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		gbag = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		mainPanel = new JPanel(gbag);
		
		//craeting leftPanel
		leftPanel = new JPanel(new GridLayout(2,1));
		input_panel = new InputPanel(new BorderLayout());
		output_panel = new OutputPanel(new BorderLayout());
		leftPanel.add(input_panel);
		leftPanel.add(output_panel);

		//setting grid constraints for leftPanel 
		gbc.weightx = 1.0;		
		gbc.ipadx = 4*screenSize.width/5;
		gbc.ipady = screenSize.height;
		gbc.insets = new Insets(15,15,15,15);
		gbag.setConstraints(leftPanel,gbc);
		
		//creating rightPanel
		rightPanel = new JPanel(new GridLayout(1,1));
		action_panel = new ActionPanel(new GridLayout(5,1,0,screenSize.height/10));
		rightPanel.add(action_panel);
		
		//setting grid constraints for rightPanel 
		gbc.weighty = 1.0;
		gbc.ipadx = screenSize.width/5;
		gbc.ipady = screenSize.height;
		gbc.insets = new Insets(15,15,15,15);
		gbag.setConstraints(rightPanel,gbc);
		
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		add(mainPanel);		
					
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}	
}