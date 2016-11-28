package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SaveButtonListener implements ActionListener {


	public void actionPerformed(ActionEvent e) {
		
		
		SaveFileDialog sdlg = new SaveFileDialog();
		sdlg.setVisible(true);
	}
}