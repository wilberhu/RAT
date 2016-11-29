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

public class JACTDialog extends JDialog {
	private BPController bpController;
	
	JLabel name, belong, actor, verb, noun, priority,belongstp;
	JTextField _actor, _verb, _noun, _priority;
	String stpBelong,actBelong,actName, actActor, actVerb, actNoun, actPriority;
	JTextArea _name;
	JComboBox _belong,_belongstp;
	JButton cancel,ok,edit;
	Font f;
	JPanel header,footer,center;
	JTextField _requirement_id;
	JScrollPane act_scrollpane;
	
	List<String> listBP,listSTP;
	
	JACTDialog(JFrame parent_frame, String value) {
		
		super(parent_frame, "Action - " + value, false);
		setLayout(new BorderLayout(500,150));
		bpController = new BPController();

		
		f=new Font("Arial",Font.PLAIN,25);
		
       //ParseBP.parseBP();
		header = new JPanel();
		header.setLayout(new FlowLayout());
		name = new JLabel("Name: ");
		_name = new JTextArea(value);
		_name.setSize(280, 50);
		_name.setLineWrap(true);
		header.add(name);
		act_scrollpane = new JScrollPane(_name,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		header.add(act_scrollpane);
		
		belong = new JLabel("belong: ");
		
		belongstp = new JLabel("belongstp: ");
		_belongstp = new JComboBox();
		_belongstp.removeAllItems();
		
		_belong = new JComboBox();
		_belong.removeAllItems();
		listBP = bpController.getAllBP();
		for(String bp : listBP) {
			_belong.addItem(bp);
		}
		_belong.addActionListener(new BelongActionListener());			
		
		_belong.setSize(150, 50);
		header.add(belong);
		header.add(_belong);
		add(header,BorderLayout.NORTH);
		
		_belongstp.setSize(150, 90);
		header.add(belongstp);
		header.add(_belongstp);
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
		
		actor = new JLabel("Actor: ");
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
	
	public void setComponents (String belong,String belongstp, String name, String actPriority, String actor, String verb, String noun) {
		
		_belong.setSelectedItem(belong);
		_belongstp.setSelectedItem(belongstp);
		_name.setText(name);
		_priority.setText(actPriority);
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
		public void actionPerformed(ActionEvent ae) {
			stpBelong = _belong.getSelectedItem().toString(); 
			actBelong = _belongstp.getSelectedItem().toString();
			actName = _name.getText();
			actPriority = _priority.getText();
			actActor = _actor.getText();
			actVerb = _verb.getText();
			actNoun = _noun.getText();
			String bpPriority = ""+stpBelong.charAt(0);
			String stpPriority = ""+actBelong.charAt(0);
			try {
				
				bpController = new BPController();
				
				//Creating Action
				bpController.createAction(bpPriority, stpPriority, actName, actPriority, actActor,actVerb, actNoun);				
				//Showing On Output Panel
				OutputPanel.outputarea.append("ACT " + bpPriority + "." + stpPriority + "." +actPriority	+ ": " + actVerb + "( "  + actActor + ", " + actNoun + " ) " + ".\r\n");
				
				
				//RAT.addACT(stpBelong, actName, STPpriority, STPActor, STPverb, STPnoun,ACTbelong);
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
				actBelong = _belongstp.getSelectedItem().toString();
				actName = _name.getText();
				actPriority = _priority.getText();
				actActor = _actor.getText();
				actVerb = _verb.getText();
				actNoun = _noun.getText();
				String bpPriority = ""+stpBelong.charAt(0);
				String stpPriority = ""+actBelong.charAt(0);
				String selectedText = OutputPanel.outputarea.getSelectedText();
				
				try {
					
					
					//edit New BusinessProcess
					editController.editAction(bpPriority,stpPriority,actName,actPriority,actActor,actVerb, actNoun );
					
					OutputPanel.outputarea.setText(OutputPanel.outputarea.getText().replace(selectedText, "ACT " + bpPriority + "." + stpPriority + "." +actPriority	+ ": " + actVerb + "( "  + actActor + ", " + actNoun + " ) " + ".\r\n"));
					
					//AddtoXML.addBP(bpName, BPpriority, BPactor, BPverb, BPnoun);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				
				
			}
	}
			
	public class BelongActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			stpBelong = _belong.getSelectedItem().toString();
			
			char keyChar = stpBelong.charAt(0);
			int keyBP = Character.getNumericValue(keyChar);
			
			listSTP = bpController.getAllStep(keyBP);
			for(String step : listSTP) {
				_belongstp.addItem(step);
			}
			
			
		}
	}
	
	
	PopupMenu pMenu = new PopupMenu(); 
	
	MenuItem mActor = new MenuItem("Actor");
	MenuItem mVerb = new MenuItem("verb");
	MenuItem mNoun = new MenuItem("noun");
	MouseAdapter mouseAdapter = new MouseAdapter()// Ã©ï¿½Â©Ã¦Ë†ï¿½?Ã®â€žâ€žÃ§Â´Â¶Ã©ï¿½ï¿½Ã¥â€ºÂ¦Ã§Â°Â¨Ã¦ÂµÂ Ã¯Â¿Â½
	{
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON3)// Ã¯Â¿Â½Ã®ï¿½â€žÃ¯Â¿Â½Ã¦ï¿½Â´Ã¦â€�?Ë†Ã§Â´Â¶Ã©ï¿½ï¿½Ã¥â€ºÂ§?Ã¦Å Â½Ã¦â€¢Â­Ã¯Â¿Â½Ã¦â€ºÅ¾Ã¥Å¡Â®Ã¦ÂµÅ“Ã¥Â¬Â©Ã¦Â¬Â¢
			{
				pMenu.show(_name, event.getX(), event.getY());// Ã©ï¿½Â¦Ã£â€žÂ©Ã§Â´Â¶Ã©ï¿½ï¿½Ã¥â€ºÂ¦Ã¯Â¿Â½Ã§Â¼Æ’Ã®â€ Â½Ã¦Â¨â€°Ã§Â»â‚¬Ã¥â€œâ€žÃ¨â€žÅ Ã©ï¿½â€˜Ã¥â€œâ€žÃ¯Â¿Â½Ã¯Â¿Â½Ã¦Â»Æ’?Ã¯Â¿Â½
			}
		}
	};
	ActionListener menuAction = new ActionListener()// Ã©ï¿½ï¿½?Ã¦ï¿½Â´Ã¦â€“Â¿?Ã¦â€ºÅ¾Ã¥Å¡Â®Ã¯Â¿Â½Ã¦Â»Æ’?Ã¦â€ºÂ¢Ã£â‚¬ï¿½Ã©ï¿½Â¨Ã¥â€¹ÂªÃ§Â°Â¨Ã¦ÂµÂ Ã¯Â¿Â½
	{// Ã©ï¿½ï¿½Ã¨Å“â€šÃ§Â¶â€¹Ã©ï¿½ï¿½Ã¥â€˜Â­Ã®â€ ï¿½Ã¯Â¿Â½Ã®Ë†ï¿½Ã¥Å¡Å“Ã¥Â®Â¸Ã¨Â¾Â©Ã§Â´ÂªÃ©ï¿½ï¿½Ã¯Â¿Â½
		public void actionPerformed(ActionEvent e) {
			MenuItem item = (MenuItem) e.getSource();
			actName = _name.getText();
			if (item == mActor){
				int start = _name.getSelectionStart();
				int end = _name.getSelectionEnd();
				String substring = actName.substring(start, end);
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
				String substring = actName.substring(start, end);
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
				String substring = actName.substring(start, end);
				try {
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