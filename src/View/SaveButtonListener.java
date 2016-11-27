package View;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFrame;

public class SaveButtonListener implements ActionListener {
	FileDialog _save;
	String output;

	public void actionPerformed(ActionEvent e) {
		_save = new FileDialog(new JFrame(), "save", FileDialog.SAVE);
		_save.setVisible(true);
		try {
			File f1 = new File(_save.getDirectory(), _save.getFile());
			FileWriter fw = new FileWriter(f1);
			BufferedWriter bw = new BufferedWriter(fw);
			output = OutputPanel.outputarea.getText();
			bw.write(output, 0, output.length());
			bw.flush();
			fw.close();
		} catch (Exception e2) {
		}
	}
}