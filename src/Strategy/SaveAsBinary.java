package Strategy;

import java.awt.FileDialog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFrame;

import View.OutputPanel;

public class SaveAsBinary extends Strategy{
	FileDialog _save;
	String output;
	@Override
	public void save() {
		_save = new FileDialog(new JFrame(), "save", FileDialog.SAVE);
		_save.setVisible(true);
		try {
			File f1 = new File(_save.getDirectory(), _save.getFile());
			FileWriter fw = new FileWriter(f1);
			BufferedWriter bw = new BufferedWriter(fw);
//			output = OutputPanel.outputarea.getText();
			output=context.getContextData();
			bw.write(output, 0, output.length());
			bw.flush();
			fw.close();
		} catch (Exception e2) {
		}
		
		
	}

}
