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
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.BPController;
import Model.BusinessProcess;
import Model.Step;

public class JSTPDialog extends JDialog {

	private BPController bPController;
	private BusinessProcess bpObj;

	JLabel name, belong, actor, verb, noun, priority;
	JTextField _actor, _verb, _noun, _priority;
	String STPname, STPActor, STPverb, STPnoun, STPpriority,STPbelong;
	JTextArea _name;
	JComboBox _belong;
	JButton cancel, ok;
	Font f;
	JPanel header, footer, center;
	JTextField _requirement_id;

	JSTPDialog(JFrame parent_frame, String value) {
		super(parent_frame, "Step - " + value, false);
		setLayout(new BorderLayout(20, 20));
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
		header.add(_name);

		belong = new JLabel("belong: ");
		//ParseBP.parseBP();
		_belong = new JComboBox();

		// Iterate through the existing business processes
		Map<Integer, BusinessProcess> mapBP = BPController.getBpMap();
		BusinessProcess bpObj;

		for (Entry entry : mapBP.entrySet()) {

			bpObj = (BusinessProcess) entry.getValue();

			_belong.addItem(entry.getKey().toString() + " " + bpObj.getString());

		}

		// _belong.removeAllItems();
		/*
		 * for (String sset : ParseBP.BPIdList) {
		 * 
		 * List list =ParseBP.BPmap.get(sset); //String s =
		 * (String)list.get(1)+" "+list.get(0);
		 * _belong.addItem((String)list.get(0)); }
		 */
		_belong.setSize(150, 50);
		header.add(belong);
		header.add(_belong);
		add(header, BorderLayout.NORTH);

		_name.add(pMenu); // Ã¥Â¯Â®Ã§â€˜Â°Ã¥Å¡Â­Ã¥Â¯Â®?Ã¯Â¿Â½Ã¦Â»Æ’?Ã¦â€ºÅ¾Ã¥Â§Å¾Ã©ï¿½ï¿½Ã£Æ’Â¥Ã¥Å¸Å’Ã©ï¿½â€šÃ¥â€ºÂ¨Ã¦Â¹Â°Ã¥Â¦â€�?Ã¥â€�?â€¢Ã¨â€¦â€˜Ã©â€�â€ºÃ¥Â±Â½?Ã¯Â¹â‚¬Ã¥Å¾Â¯Ã¦Â¶â€œ?Ã©â€˜Â³Ã¨Å Â¥Ã¦Â¨â€°Ã§Â»â‚¬Ã¯Â¿Â½
		_name.addMouseListener(mouseAdapter); // Ã©ï¿½â€šÃ¥â€ºÂ¨Ã¦Â¹Â°Ã¥Â¦â€�?Ã¥â€�?â€�?Ã¥Â§Å¾Ã©ï¿½ï¿½Ã£Æ’Â©Ã§Â´Â¶Ã©ï¿½ï¿½Ã¥â€ºÂ©Ã¦Â´Æ’Ã¯Â¿Â½Ã®â€žâ‚¬Ã¦Â«â€™
		pMenu.add(mActor); // Ã¯Â¿Â½Ã¦Â»Æ’?Ã¦â€ºÂ¢Ã£â‚¬ï¿½Ã©ï¿½Â¨Ã¥â€¹Â«?Ã¦â€ºÅ¾Ã¥Å¡Â®Ã¦ÂµÅ“Ã¥Â¬Â©Ã¦Â¬Â¢Ã©ï¿½Â©Ã¦Ë†ï¿½?Ã®â€žâ‚¬Ã¦Â«â€™
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
		// Actor.setVisible(false);
		// _Actor.setVisible(false);
		center.add(actor);
		center.add(_actor);

		verb = new JLabel("verb: ");
		_verb = new JTextField(10);
		// verb.setVisible(false);
		// _verb.setVisible(false);
		center.add(verb);
		center.add(_verb);

		noun = new JLabel("noun: ");
		_noun = new JTextField(10);
		// noun.setVisible(false);
		// _noun.setVisible(false);
		center.add(noun);
		center.add(_noun);

		center.setSize(300, 350);
		center.setVisible(true);

		add(center, BorderLayout.CENTER);

		footer = new JPanel();
		cancel = new JButton("Cancel");
		ok = new JButton("OK");
		cancel.addActionListener(new CancelButtonListener());
		ok.addActionListener(new OkButtonListener());
		footer.add(cancel);
		footer.add(ok);
		add(footer, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(parent_frame);
	}
	
	public void setComponents (String belong,String name, String priority, String actor, String verb, String noun) {
		_belong.removeAllItems();
		Map<Integer, BusinessProcess> mapBP = BPController.getBpMap();
		BusinessProcess bpObj;

		for (Entry entry : mapBP.entrySet()) {

			bpObj = (BusinessProcess) entry.getValue();

			_belong.addItem(entry.getKey().toString() + " " + bpObj.getString());

		}
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
			STPbelong = _belong.getSelectedItem().toString();
			STPname = _name.getText();
			STPpriority = _priority.getText();
			STPActor = _actor.getText();
			STPverb = _verb.getText();
			STPnoun = _noun.getText();
			String _bpPriority = ""+STPbelong.charAt(0);
			try {
				
				bPController = new BPController();
				
				//create New BusinessProcess
				Step stpObj = bPController.createStep(_bpPriority,_name.getText(),_priority.getText(), _actor.getText(), _verb.getText(),_noun.getText());
				
				//Show Step
				OutputPanel.outputarea.append("STP " + _bpPriority +"." +  _priority.getText()	+ ": " + _verb.getText() + "( "  + _actor.getText() + ", " + _noun.getText() + " ) " + ".\r\n");

				// RAT.addSTP(STPbelong, STPname, STPpriority, STPActor,
				// STPverb, STPnoun);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispose();

		}
	}

	PopupMenu pMenu = new PopupMenu(); // Ã©ï¿½â€™Ã¦Â¶ËœÃ§Â¼â€œÃ¥Â¯Â®Ã§â€˜Â°Ã¥Å¡Â­Ã¥Â¯Â®?Ã¯Â¿Â½Ã¦Â»Æ’?Ã¦â€ºÂªÃ§Â´ï¿½Ã¦Â¶â€œÃ¥Â¬Â®?Ã®ï¿½Â­Ã§Â¬ï¿½Ã¦Â¤Â¤Ã¨Â§â€žÃ¦Â§Â¸Ã¯Â¿Â½Ã¦Â»Æ’?Ã¦â€ºÂ¢Ã£â‚¬ï¿½

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
			STPname = _name.getText();
			if (item == mActor) {
				int start = _name.getSelectionStart();
				int end = _name.getSelectionEnd();
				String substring = STPname.substring(start, end);
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
				String substring = STPname.substring(start, end);
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
				String substring = STPname.substring(start, end);
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