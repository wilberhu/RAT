package View;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import com.sun.javafx.binding.StringFormatter;


public class InputMouseHandler extends MouseAdapter {
	InputPanel handler_parent;
	JPopupMenu popMenu;
	JMenuItem bp_item,stp_item,act_item;
	
	public InputMouseHandler(InputPanel handler_parent) {
		this.handler_parent = handler_parent;
	}
	
	public void mousePressed(MouseEvent e){
		if (e.getButton() == MouseEvent.BUTTON3) {
			//check if selected word is not null	
			if (handler_parent.inputarea.getSelectedText() != null) {
				popMenu = new JPopupMenu();
	
				bp_item = new JMenuItem("BusinessProcess");
				stp_item = new JMenuItem("Step");
				act_item = new JMenuItem("Action");
				
				bp_item.addActionListener(new BPMenuItemListener());
				stp_item.addActionListener(new STPMenuItemListener());
				act_item.addActionListener(new ACTMenuItemListener());
				
				popMenu.add(bp_item);
				popMenu.add(stp_item);
				popMenu.add(act_item);
				
				popMenu.show(e.getComponent(), e.getX(), e.getY());
			}					
		}
	}
	
	public static String getRequirementStatement(){
		String text =  InputPanel.inputarea.getText();
		int indexOf_businessProcess = InputPanel.inputarea.getSelectionStart();
		int statement_start = text.lastIndexOf(".",indexOf_businessProcess)+1; 
		int statement_end = text.indexOf(".",indexOf_businessProcess)+1;
		if (statement_start == -1) { statement_start = 0; }
		if (statement_end == -1) { statement_end = indexOf_businessProcess+InputPanel.inputarea.getSelectedText().length(); }
		String requirement_statement = text.substring(statement_start,statement_end);
		return requirement_statement;
	}
	
	public class BPMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/*String requirement_statement = InputMouseHandler.getRequirementStatement();
			JBPDialog jdlg = new JBPDialog((JFrame) SwingUtilities.getRoot(handler_parent),requirement_statement);*/
			JBPDialog jdlg = new JBPDialog((JFrame) SwingUtilities.getRoot(handler_parent),InputPanel.inputarea.getSelectedText());
			jdlg.setVisible(true);
		}						
	}
	public class STPMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/*String requirement_statement = InputMouseHandler.getRequirementStatement();
			JSTPDialog jdlg = new JSTPDialog((JFrame) SwingUtilities.getRoot(handler_parent),requirement_statement);*/
			JSTPDialog jdlg = new JSTPDialog((JFrame) SwingUtilities.getRoot(handler_parent),InputPanel.inputarea.getSelectedText());
			jdlg.setVisible(true);
		}						
	}
	public class ACTMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/*String requirement_statement = InputMouseHandler.getRequirementStatement();
			JACTDialog jdlg = new JACTDialog((JFrame) SwingUtilities.getRoot(handler_parent),requirement_statement);*/
			JACTDialog jdlg = new JACTDialog((JFrame) SwingUtilities.getRoot(handler_parent),InputPanel.inputarea.getSelectedText());
			jdlg.setVisible(true);
		}						
	}
}