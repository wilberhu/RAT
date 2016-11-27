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
import javax.swing.JTextArea;
import javax.swing.JTextField;




import Controller.BPController;
import Model.BusinessProcess;



public class JBPDialog extends JDialog {
	
	private BPController bPController;
	
	JLabel name, actor, verb, noun, priority;
	JTextField _actor, _verb, _noun, _priority;
	String BPname, BPactor, BPverb, BPnoun, BPpriority;
	JTextArea _name;
	JButton cancel,ok;
	Font f;
	JPanel header,footer,center;
	JTextField _requirement_id;
	JBPDialog(JFrame parent_frame,String value) {
		super (parent_frame,"BusinessProcess - "+value,false);
		setLayout(new BorderLayout(20,20));
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
		add(header,BorderLayout.NORTH);
		
		
		_name.add(pMenu); // å¼¹å‡ºå¼?è?œå?•åŠ å…¥åˆ°æ–‡æœ¬æ¡†ä¸­ï¼Œå?¦åˆ™ä¸?èƒ½æ˜¾ç¤º
		_name.addMouseListener(mouseAdapter); // æ–‡æœ¬æ¡†åŠ å…¥é¼ æ ‡ç›‘å?¬å™¨
		pMenu.add(mActor); // è?œå?•é¡¹çš„å?•å‡»äº‹ä»¶ç›‘å?¬å™¨
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
		//actor.setVisible(false);
		//_actor.setVisible(false);
		center.add(actor);
		center.add(_actor);
		
		verb = new JLabel("verb: ");
		_verb = new JTextField (10);
		//verb.setVisible(false);
		//_verb.setVisible(false);
		center.add(verb);
		center.add(_verb);
		
		noun = new JLabel("noun: ");
		_noun = new JTextField (10);
		//noun.setVisible(false);
		//_noun.setVisible(false);
		center.add(noun);
		center.add(_noun);

        center.setSize(300,350);
        center.setVisible(true);
		
		add(center,BorderLayout.CENTER);
		
		footer = new JPanel();
		cancel = new JButton("Cancel");
		ok = new JButton("OK");
		cancel.addActionListener(new CancelButtonListener());
		ok.addActionListener(new OkButtonListener());		
		footer.add(cancel);
		footer.add(ok);
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
			BPname = _name.getText();
			BPpriority = _priority.getText();
			BPactor = _actor.getText();
			BPverb = _verb.getText();
			BPnoun = _noun.getText();
			try {
				
				bPController = new BPController();
				
				//create New BusinessProcess
				BusinessProcess bpObj = bPController.createBusinessProcess(_name.getText(),_priority.getText(),_actor.getText(),
						_verb.getText(), _noun.getText() );
				
				//show on output panel
				priority = Integer.toString(bpObj.getPriority());
				actor = bpObj.getActor().getString();
				verb = bpObj.getVerb().getString();
				noun= bpObj.getNoun().getString();
				OutputPanel.outputarea.append("BP " + priority	+ ": " + verb + "( "  + actor + ", " + noun + " ) " + ".\r\n");
				
				//AddtoXML.addBP(BPname, BPpriority, BPactor, BPverb, BPnoun);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispose();
			
		}
	}
	
	
	PopupMenu pMenu = new PopupMenu(); // åˆ›å»ºå¼¹å‡ºå¼?è?œå?•ï¼Œä¸‹é?¢ä¸‰é¡¹æ˜¯è?œå?•é¡¹
	
	MenuItem mActor = new MenuItem("actor");
	MenuItem mVerb = new MenuItem("verb");
	MenuItem mNoun = new MenuItem("noun");
	MouseAdapter mouseAdapter = new MouseAdapter()// ç›‘å?¬é¼ æ ‡äº‹ä»¶
	{
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON3)// å?ªå“?åº”é¼ æ ‡å?³é”®å?•å‡»äº‹ä»¶
			{
				pMenu.show(_name, event.getX(), event.getY());// åœ¨é¼ æ ‡ä½?ç½®æ˜¾ç¤ºå¼¹å‡ºå¼?è?œå?•
			}
		}
	};
	ActionListener menuAction = new ActionListener()// å“?åº”å?•å‡»è?œå?•é¡¹çš„äº‹ä»¶
	{// å…·ä½“å†…å®¹å?¯è‡ªå·±ç¼–å†™
		public void actionPerformed(ActionEvent e) {
			MenuItem item = (MenuItem) e.getSource();
			BPname = _name.getText();
			if (item == mActor){
				int start = _name.getSelectionStart();
				int end = _name.getSelectionEnd();
				String substring = BPname.substring(start, end);
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
				String substring = BPname.substring(start, end);
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
				String substring = BPname.substring(start, end);
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