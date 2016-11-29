package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




import Controller.BPController;
import Controller.EditController;
import Model.BusinessProcess;



public class JBPDialog extends JDialog {
	
	private BPController bPController;
	private EditController editController;
	
	JLabel name, actor, verb, noun, priority;
	JTextField _actor, _verb, _noun, _priority;
	String bpName, bpActor, bpVerb, bpNoun, bpPriority;
	JTextArea _name;
	JButton cancel,ok,edit;
	Font f;
	JPanel header,footer,center;
	JTextField _requirement_id;
	JScrollPane bp_scrollpane;
	
	
	JBPDialog(JFrame parent_frame,String value) {
		super (parent_frame,"BusinessProcess - "+value,false);
		setLayout(new BorderLayout(300,150));
		//setSize(400,600);
		
		f=new Font("Arial",Font.PLAIN,25);
		
		header = new JPanel();
		header.setLayout(new FlowLayout());
		name = new JLabel("Name: ");
		_name = new JTextArea(value);
		_name.setSize(280, 50);
		_name.setLineWrap(true);
		header.add(name);
		header.add(_name);
		bp_scrollpane = new JScrollPane(_name,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		header.add(bp_scrollpane);
		add(header,BorderLayout.NORTH);
		
		
		_name.add(pMenu);
		_name.addMouseListener(mouseAdapter);
		pMenu.add(mActor);
		mActor.addActionListener(menuAction);
		pMenu.add(mVerb);
		mVerb.addActionListener(menuAction);
		pMenu.add(mNoun);
		mNoun.addActionListener(menuAction);
		
		
		
		center = new JPanel();
		center.setLayout(new FlowLayout());
		
		
		
		priority = new JLabel("priority: ");
		_priority = new JTextField (10);
		priority.setVisible(true);
		_priority.setVisible(true);
		center.add(priority);
		center.add(_priority);
		
		actor = new JLabel("actor: ");
		_actor = new JTextField (10);
		center.add(actor);
		center.add(_actor);
		
		verb = new JLabel("verb: ");
		_verb = new JTextField (10);
		center.add(verb);
		center.add(_verb);
		
		noun = new JLabel("noun: ");
		_noun = new JTextField (10);
		center.add(noun);
		center.add(_noun);
        center.setVisible(true);
		
		add(center,BorderLayout.CENTER);
		
		footer = new JPanel();
		cancel = new JButton("Cancel");
		ok = new JButton("OK");
		edit = new JButton("EDIT");
		cancel.addActionListener(new CancelButtonListener());
		ok.addActionListener(new OkButtonListener());		
		edit.addActionListener(new EditButtonListener());
		footer.add(cancel);
		footer.add(ok);
		footer.add(edit);
		edit.setVisible(false);
		add(footer,BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(parent_frame);
	}
	public void setComponents (String name, String priority, String actor, String verb, String noun) {
		_name.setText(name);
		_priority.setText(priority);
		_actor.setText(actor);
		_verb.setText(verb);
		_noun.setText(noun);
	}
	public Insets getInsets() {
		return new Insets(50,50,50,50);
	}
	
	public class CancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			dispose();
		}
	}
	
	public class OkButtonListener implements ActionListener {
		String actor,verb,noun,priority;
		public void actionPerformed(ActionEvent ae) {
			bpName = _name.getText();
			bpPriority = _priority.getText();
			bpActor = _actor.getText();
			bpVerb = _verb.getText();
			bpNoun = _noun.getText();
			try {
				
				bPController = new BPController();
				
				//create New BusinessProcess
				bPController.createBusinessProcess(bpName,bpPriority,bpActor,bpVerb, bpNoun );
				
				OutputPanel.outputarea.append("BP " + bpPriority	+ ": " + bpVerb + "( "  + bpActor + ", " + bpNoun + " ) " + ".\r\n");
				
				//AddtoXML.addBP(bpName, BPpriority, BPactor, BPverb, BPnoun);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispose();
			
		}
	}
	
	public class EditButtonListener implements ActionListener {
		
		EditController editController = new EditController();
		
			@Override
			public void actionPerformed(ActionEvent e) {

				bpName = _name.getText();
				bpPriority = _priority.getText();
				bpActor = _actor.getText();
				bpVerb = _verb.getText();
				bpNoun = _noun.getText();
				String selectedText = OutputPanel.outputarea.getSelectedText();
				
				try {
					
					
					//edit New BusinessProcess
					editController.editBusinessProcess(bpName,bpPriority,bpActor,bpVerb, bpNoun );
					
					OutputPanel.outputarea.setText(OutputPanel.outputarea.getText().replace(selectedText, "BP " + bpPriority	+ ": " + bpVerb + "( "  + bpActor + ", " + bpNoun + " ) " + ".\r\n"));
					
					//AddtoXML.addBP(bpName, BPpriority, BPactor, BPverb, BPnoun);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				
				
			}
			
	
	}
	
	
	PopupMenu pMenu = new PopupMenu(); // 创建弹出�?�?��?�，下�?�三项是�?��?�项
	
	MenuItem mActor = new MenuItem("actor");
	MenuItem mVerb = new MenuItem("verb");
	MenuItem mNoun = new MenuItem("noun");
	MouseAdapter mouseAdapter = new MouseAdapter()// 监�?�鼠标事件
	{
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON3)// �?��?应鼠标�?�键�?�击事件
			{
				pMenu.show(_name, event.getX(), event.getY());// 在鼠标�?置显示弹出�?�?��?�
			}
		}
	};
	
	ActionListener menuAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			MenuItem item = (MenuItem) e.getSource();
			bpName = _name.getText();
			
			if (item == mActor){
				int start = _name.getSelectionStart();
				int end = _name.getSelectionEnd();
				String substring = bpName.substring(start, end);
				try {
					//addActor(cp);
					_actor.setText(substring);
					actor.setVisible(true);
					_actor.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} else if (item == mVerb){
				int start = _name.getSelectionStart();
				int end = _name.getSelectionEnd();
				String substring = bpName.substring(start, end);
				try {
					//addVerb(cp);
					_verb.setText(substring);
					verb.setVisible(true);
					_verb.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// System.out.println(cp);

			} else if (item == mNoun) {
				int start = _name.getSelectionStart();
				int end = _name.getSelectionEnd();
				String substring = bpName.substring(start, end);
				try {
					//addNoun(cp);
					
					_noun.setText(substring);
					noun.setVisible(true);
					_noun.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	};

}