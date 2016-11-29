package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import Model.Step;

public class JSTPDialog extends JDialog {

	private BPController bpController;
	private BusinessProcess bpObj;

	JLabel name, belong, actor, verb, noun, priority;
	JTextField _actor, _verb, _noun, _priority;
	String stpName, stpActor, stpVerb, stpNoun, stpPriority,stpBelong;
	JTextArea _name;
	JComboBox _belong;
	JButton cancel, ok,edit;
	List<String> listBP;
	Font f;
	JPanel header, footer, center;
	JTextField _requirement_id;
	JScrollPane stp_scrollpane;

	JSTPDialog(JFrame parent_frame, String value) {
		super(parent_frame, "Step - " + value, false);
		
		bpController = new BPController();
		setLayout(new BorderLayout(300,150));
		setLocationRelativeTo(parent_frame);
		// setSize(400,600);

		f = new Font("Arial", Font.PLAIN, 25);

		header = new JPanel();
		header.setLayout(new FlowLayout());
		name = new JLabel("Name: ");
		_name = new JTextArea(value);
		_name.setSize(280, 50);
		_name.setLineWrap(true);
		header.add(name);
		stp_scrollpane = new JScrollPane(_name,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		header.add(stp_scrollpane);

		belong = new JLabel("belong: ");
		//ParseBP.parseBP();
		_belong = new JComboBox();
		_belong.removeAllItems();
		listBP = bpController.getAllBP();
		for(String bp : listBP) {
			_belong.addItem(bp);
		}
		
		_belong.setSize(150, 50);
		header.add(belong);
		header.add(_belong);
		add(header, BorderLayout.NORTH);

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
		_priority = new JTextField(10);
		priority.setVisible(true);
		_priority.setVisible(true);
		center.add(priority);
		center.add(_priority);

		actor = new JLabel("Actor: ");
		_actor = new JTextField(10);
		center.add(actor);
		center.add(_actor);

		verb = new JLabel("verb: ");
		_verb = new JTextField(10);
		center.add(verb);
		center.add(_verb);

		noun = new JLabel("noun: ");
		_noun = new JTextField(10);
		center.add(noun);
		center.add(_noun);
		center.setVisible(true);
		add(center, BorderLayout.CENTER);

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
		add(footer, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(parent_frame);
	}
	
	public void setComponents (String belong,String name, String priority, String actor, String verb, String noun) {
		
		_belong.setSelectedItem(belong);
		_name.setText(name);
		_priority.setText(priority);
		_actor.setText(actor);
		_verb.setText(verb);
		_noun.setText(noun);
	}
	
	public Insets getInsets() {
		return new Insets(50, 50, 50, 50);
	}

	public class CancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			dispose();
		}
	}

	public class OkButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			stpBelong = _belong.getSelectedItem().toString();
			stpName = _name.getText();
			stpPriority = _priority.getText();
			stpActor = _actor.getText();
			stpVerb = _verb.getText();
			stpNoun = _noun.getText();
			String bpPriority = ""+stpBelong.charAt(0);
			try {
				
				bpController = new BPController();
				
				//create New BusinessProcess
				bpController.createStep(bpPriority,stpName,stpPriority, stpActor, stpVerb,stpNoun);
				
				//Show Step
				OutputPanel.outputarea.append("STP " + bpPriority +"." + stpPriority+ ": " + stpVerb + "( "  + stpActor + ", " + stpNoun + " ) " + ".\r\n");

				// RAT.addSTP(STPbelong, stpName, STPpriority, STPActor,
				// STPverb, STPnoun);
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

				stpBelong = _belong.getSelectedItem().toString();
				stpName = _name.getText();
				stpPriority = _priority.getText();
				stpActor = _actor.getText();
				stpVerb = _verb.getText();
				stpNoun = _noun.getText();
				String bpPriority = ""+stpBelong.charAt(0);
				String selectedText = OutputPanel.outputarea.getSelectedText();
				
				try {
					
					
					//edit New BusinessProcess
					editController.editStep(bpPriority,stpName,stpPriority,stpActor,stpVerb, stpNoun );
					
					OutputPanel.outputarea.setText(OutputPanel.outputarea.getText().replace(selectedText, "STP " + bpPriority +"." + stpPriority+ ": " + stpVerb + "( "  + stpActor + ", " + stpNoun + " ) " + ".\r\n"));
					
					//AddtoXML.addBP(bpName, BPpriority, BPactor, BPverb, BPnoun);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				
				
			}
			
	
	}

	PopupMenu pMenu = new PopupMenu();

	MenuItem mActor = new MenuItem("Actor");
	MenuItem mVerb = new MenuItem("verb");
	MenuItem mNoun = new MenuItem("noun");
	MouseAdapter mouseAdapter = new MouseAdapter()
	{
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON3)
			{
				pMenu.show(_name, event.getX(), event.getY());
			}
		}
	};
	ActionListener menuAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			MenuItem item = (MenuItem) e.getSource();
			stpName = _name.getText();
			if (item == mActor) {
				int start = _name.getSelectionStart();
				int end = _name.getSelectionEnd();
				String substring = stpName.substring(start, end);
				try {
					// addActor(cp);
					_actor.setText(substring);
					actor.setVisible(true);
					_actor.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (item == mVerb) {
				int start = _name.getSelectionStart();
				int end = _name.getSelectionEnd();
				String substring = stpName.substring(start, end);
				try {
					// addVerb(cp);
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
				String substring = stpName.substring(start, end);
				try {
					// addNoun(cp);

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