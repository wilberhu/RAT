package View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OutputPanel extends JPanel {
	Font f;
	static JTextArea outputarea;
	JScrollPane output_scrollpane;
	JLabel lbl_output;
	public OutputPanel(LayoutManager layout) {
		this.setLayout(layout);
		
		f=new Font("Arial",Font.BOLD,25);

		lbl_output = new JLabel("Output:");
		lbl_output.setFont(f);
		add(lbl_output,BorderLayout.NORTH);
			
		f=new Font("Arial",Font.PLAIN,15);
		
		outputarea = new JTextArea("");
		outputarea.setLineWrap(true);
        outputarea.setWrapStyleWord(true);		
		outputarea.setFont(f);
		outputarea.addMouseListener(new OutputMouseHandler(this));
		
		output_scrollpane = new JScrollPane(outputarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//add(outputarea,BorderLayout.CENTER);
		add(output_scrollpane,BorderLayout.CENTER);
	}
	public Insets getInsets() {
		return new Insets(10,10,10,10);
	}
}