package View;

import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font f;
	JButton btn_import, btn_highlight, btn_generate, btn_save, btn_close;
	public ActionPanel(LayoutManager layout) {
		this.setLayout(layout);
		
		f=new Font("Arial",Font.PLAIN,15);
		
		btn_import = new JButton("import");
		btn_highlight = new JButton("highlight");
		btn_generate = new JButton("generate");
		btn_save = new JButton("save");
		btn_close = new JButton("close");
		
		btn_import.setFont(f);
		btn_highlight.setFont(f);
		btn_generate.setFont(f);
		btn_save.setFont(f);
		btn_close.setFont(f);
		
		btn_import.addActionListener(new ImportButtonListener());
		btn_highlight.addActionListener(new HighlightButtonListener());
		btn_generate.addActionListener(new GenerateButtonListener());
		btn_save.addActionListener(new SaveButtonListener());
		btn_close.addActionListener(new CloseButtonListener());
		
		add(btn_import);
		add(btn_highlight);
		add(btn_generate);
		add(btn_save);
		add(btn_close);

	}
	public Insets getInsets() {
		return new Insets(10,10,10,10);
	}
}