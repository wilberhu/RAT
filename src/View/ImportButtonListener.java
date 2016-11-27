package View;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;

public class ImportButtonListener implements ActionListener {
	FileDialog _open;
	String input;
	public void actionPerformed(ActionEvent e) {
		_open = new FileDialog(new JFrame(), "import", FileDialog.LOAD);
		_open.setVisible(true);
		try {
			File f1 = new File(_open.getDirectory(), _open.getFile());
			FileReader fr = new FileReader(f1);
			BufferedReader br = new BufferedReader(fr);
			InputPanel.inputarea.setText("");
			while ((input = br.readLine()) != null)
				InputPanel.inputarea.append(input + '\n');
			fr.close();
		} catch (Exception e1) {
		}
		
	}
}