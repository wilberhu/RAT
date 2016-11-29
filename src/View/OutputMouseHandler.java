package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import Controller.BPController;
import Controller.EditController;
import Model.Action;
import Model.BusinessProcess;
import Model.Step;


public class OutputMouseHandler extends MouseAdapter {
	OutputPanel handler_parent;
	JPopupMenu popMenu;
	JMenuItem edit_item,delete_item;
	List<String> editList;
	EditController editController ;
	
	public OutputMouseHandler(OutputPanel handler_parent) {
		this.handler_parent = handler_parent;
		editController = new EditController();
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
				jdlg.ok.setVisible(false);
				jdlg.edit.setVisible(true);
				
				editList = editController.getBPContent(keyBP[0]);
				
				//mapBP = BPController.getBpMap();
				//bpObj = mapBP.get(keyBP[0]); 
				jdlg.setComponents(editList.get(0), editList.get(1), editList.get(2),editList.get(3),editList.get(4));
			}
			else if (OutputPanel.outputarea.getSelectedText().contains("STP")) {
				keySTP = extractPriority(OutputPanel.outputarea.getSelectedText());
				JSTPDialog jdlg = new JSTPDialog((JFrame) SwingUtilities.getRoot(handler_parent),OutputPanel.outputarea.getSelectedText());
				jdlg.setVisible(true);
				
				jdlg.ok.setVisible(false);
				jdlg.edit.setVisible(true);
				
				editList = editController.getSTPContent(keySTP[0],keySTP[1]);
				
				
				jdlg.setComponents(Integer.toString(keySTP[0])+" "+editList.get(0),editList.get(1), Integer.toString(keySTP[1]), editList.get(2),editList.get(3),editList.get(4));
			}
			else if (OutputPanel.outputarea.getSelectedText().contains("ACT")) {
				keyACT = extractPriority(OutputPanel.outputarea.getSelectedText());
				JACTDialog jdlg = new JACTDialog((JFrame) SwingUtilities.getRoot(handler_parent),OutputPanel.outputarea.getSelectedText());
				jdlg.setVisible(true);
				jdlg.ok.setVisible(false);
				jdlg.edit.setVisible(true);
				
				editList = editController.getACTContent(keyACT[0],keyACT[1],keyACT[2]);
				
				jdlg.setComponents(Integer.toString(keyACT[0])+" "+editList.get(0), Integer.toString(keyACT[1])+" "+editList.get(1),editList.get(2), Integer.toString(keyACT[2]),editList.get(3),editList.get(4),editList.get(5));
				
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