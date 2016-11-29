package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.Highlighter;

import Controller.HightlightController;

public class HighlightButtonListener implements ActionListener {
	HightlightController hightlightController;
	Highlighter highlighter;
	String input;
   
	public void actionPerformed(ActionEvent e) {
		hightlightController = new HightlightController();
		
		highlighter = InputPanel.inputarea.getHighlighter();
		input =InputPanel.inputarea.getText();
		
		hightlightController.hightlight(highlighter,input);
		
	}
}