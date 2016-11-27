package View;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Map;

import javax.swing.*;

import Model.Action;
import Controller.BPController;
import Model.BusinessProcess;
import Model.Step;


public class OutputMouseHandler extends MouseAdapter {
	OutputPanel handler_parent;
	JPopupMenu popMenu;
	JMenuItem edit_item,delete_item;
	
	public OutputMouseHandler(OutputPanel handler_parent) {
		this.handler_parent = handler_parent;
	}
	
	public void mousePressed(MouseEvent e){
		if (e.getButton() == MouseEvent.BUTTON3) {
			//check if selected word is not null	
			if (handler_parent.outputarea.getSelectedText() != null) {
				popMenu = new JPopupMenu();
	
				edit_item = new JMenuItem("Edit");
				delete_item = new JMenuItem("Delete");
				
				edit_item.addActionListener(new EditMenuItemListener());
				delete_item.addActionListener(new DeleteMenuItemListener());
				
				
				popMenu.add(edit_item);
				popMenu.add(delete_item);
				
				popMenu.show(e.getComponent(), e.getX(), e.getY());
			}					
		}
	}
	
	public int[] extractPriority(String str) {
		String priority = str.replaceAll("[^\\d.]","");
		return Arrays.stream(priority.split("\\.")).mapToInt(Integer::parseInt).toArray();
		
	}
	
	public class EditMenuItemListener implements ActionListener {
		BusinessProcess bpObj;
		Step stpObj;
		Action actObj;
		Map<Integer, BusinessProcess> mapBP;
		Map<Integer, Step> mapSTP;
		Map<Integer, Action> mapACT;
		int keyBP[],keySTP[],keyACT[];
		public void actionPerformed(ActionEvent e) {
			if (OutputPanel.outputarea.getSelectedText().contains("BP")) {
				keyBP = extractPriority(OutputPanel.outputarea.getSelectedText());
				JBPDialog jdlg = new JBPDialog((JFrame) SwingUtilities.getRoot(handler_parent),OutputPanel.outputarea.getSelectedText());
				jdlg.setVisible(true);
				mapBP = BPController.getBpMap();
				bpObj = mapBP.get(keyBP[0]); 
				jdlg.setComponents(bpObj.getString(), Integer.toString(bpObj.getPriority()), bpObj.getActor().getString(),bpObj.getVerb().getString(),bpObj.getNoun().getString());
			}
			else if (OutputPanel.outputarea.getSelectedText().contains("STP")) {
				keySTP = extractPriority(OutputPanel.outputarea.getSelectedText());
				JSTPDialog jdlg = new JSTPDialog((JFrame) SwingUtilities.getRoot(handler_parent),OutputPanel.outputarea.getSelectedText());
				jdlg.setVisible(true);
				mapBP = BPController.getBpMap();
				bpObj = mapBP.get(keySTP[0]); 
				mapSTP = bpObj.getStepMap();
				stpObj = mapSTP.get(keySTP[1]);
				jdlg.setComponents(Integer.toString(keySTP[0])+" "+bpObj.getString(),stpObj.getString(), Integer.toString(keySTP[1]), stpObj.getActor().getString(),stpObj.getVerb().getString(),stpObj.getNoun().getString());
			}
			else if (OutputPanel.outputarea.getSelectedText().contains("ACT")) {
				keyACT = extractPriority(OutputPanel.outputarea.getSelectedText());
				JACTDialog jdlg = new JACTDialog((JFrame) SwingUtilities.getRoot(handler_parent),OutputPanel.outputarea.getSelectedText());
				jdlg.setVisible(true);
				mapBP = BPController.getBpMap();
				bpObj = mapBP.get(keyACT[0]); 
				mapSTP = bpObj.getStepMap();
				stpObj = mapSTP.get(keyACT[1]);
				mapACT = stpObj.getActionMap();
				actObj = mapACT.get(keyACT[2]);
				jdlg.setComponents(Integer.toString(keyACT[0])+" "+actObj.getString(), Integer.toString(keyACT[1])+" "+actObj.getString(), actObj.getString(),Integer.toString(keyACT[2]),actObj.getActor().getString(),actObj.getVerb().getString(),actObj.getNoun().getString());
				
			}
			
		}
		
	}
	public class DeleteMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JSTPDialog jdlg = new JSTPDialog((JFrame) SwingUtilities.getRoot(handler_parent),OutputPanel.outputarea.getSelectedText());
			jdlg.setVisible(true);
		}						
	}
}