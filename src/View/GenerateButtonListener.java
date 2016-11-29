package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;




import Model.Action;
import Controller.BPController;
import Controller.GenerateController;
import Model.BusinessProcess;
import Model.Step;

public class GenerateButtonListener implements ActionListener {
	
	GenerateController generateController;
	String output;
	
	public void actionPerformed(ActionEvent e) {
		
		OutputPanel.outputarea.setText("");
		generateController = new GenerateController();
		output = generateController.generateRequirenments();
		OutputPanel.outputarea.setText(output);
		
	}
}