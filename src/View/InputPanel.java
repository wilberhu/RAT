package View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InputPanel extends JPanel {
	Font f;
	static JTextArea inputarea;
	JScrollPane input_scrollpane;
	JLabel lbl_input;
	public InputPanel(LayoutManager layout) {
		
		this.setLayout(layout);
		
		f=new Font("Arial",Font.BOLD,25);
		
		//creating label
		lbl_input = new JLabel("Input:");
		lbl_input.setFont(f);
		add(lbl_input,BorderLayout.NORTH);
			
		f=new Font("Arial",Font.PLAIN,15);
		
		//creating InputTextArea
		inputarea = new JTextArea("");
		inputarea.setLineWrap(true);
        inputarea.setWrapStyleWord(true);		
		inputarea.setFont(f);
		inputarea.addMouseListener(new InputMouseHandler(this));
		
		//creating input_scrollpane
		input_scrollpane = new JScrollPane(inputarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(input_scrollpane,BorderLayout.CENTER);		
		
	}
	public Insets getInsets() {
		return new Insets(10,10,10,10);
	}
	
}