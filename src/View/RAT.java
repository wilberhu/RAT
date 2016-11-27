package View;

import java.awt.*;
import java.io.FileWriter;
import java.util.List;

import javax.swing.JFrame;


public class RAT {
	public static void main(String args[]) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		
		GUI gui = new GUI("RAT");
		gui.setSize(screenSize.width,screenSize.height);
		gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
		gui.setVisible(true);
	}
}