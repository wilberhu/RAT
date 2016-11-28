package View;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.BPController;
import Model.Action;
import Model.BusinessProcess;
import Model.Step;

import java.io.FileWriter;
import java.io.IOException;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import Command.CommandInterface;
import Command.SaveAsBinary;
import Command.SaveAsXML;

public class SaveFileDialog  extends JDialog {
	
	JButton cancel,save_binary, save_xml;
	Font f;
	JPanel header,footer,center;
	SaveFileDialog() {
		super ();
		setLayout(new BorderLayout(20,20));
		//setSize(400,600);
		
		f=new Font("Arial",Font.PLAIN,25);
		
		header = new JPanel();
		header.setLayout(new FlowLayout());
		add(header,BorderLayout.NORTH);
		
		
		center = new JPanel();
		center.setLayout(new FlowLayout());
		

        center.setSize(300,350);
        center.setVisible(true);
        cancel = new JButton("Cancel");
        save_binary = new JButton("save_binary");
        save_xml = new JButton("save_xml");
		cancel.addActionListener(new CancelButtonListener());
		save_binary.addActionListener(new Save_binaryButtonListener());
		save_xml.addActionListener(new Save_xmlButtonListener());
		center.add(cancel);
		center.add(save_binary);
		center.add(save_xml);
		add(center,BorderLayout.CENTER);
		
		footer = new JPanel();
		add(footer,BorderLayout.SOUTH);
		
		pack();
		//setLocationRelativeTo(parent_frame);
	}
	
	public class CancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			dispose();
		}
	}
	public class Save_binaryButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent ae) {
			
			CommandInterface save_Binary = new SaveAsBinary();
			save_Binary.execute();
			dispose();
			
		}
	}
	
	public class Save_xmlButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent ae) {
			
			CommandInterface save_XML = new SaveAsXML();
			save_XML.execute();
			
			dispose();
			
		}
	}

}
