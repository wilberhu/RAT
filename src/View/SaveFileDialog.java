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

public class SaveFileDialog  extends JDialog {

	FileDialog _save;
	String output;
	
	
	
	String name, priority, actor, verb, noun,stepPriority,actionPriority;
	BusinessProcess bpObj;
	Step stepObj;
	Action actionObj;
	Map<Integer,BusinessProcess> bpMap;
	
	
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
		String actor,verb,noun,priority;
		public void actionPerformed(ActionEvent ae) {
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
			dispose();
			
		}
	}
	
	public class Save_xmlButtonListener implements ActionListener {
		String actor,verb,noun,priority;
		public void actionPerformed(ActionEvent ae) {
			try {

				Element Requirment = new Element("Requirment");
				Document doc = new Document(Requirment);
				doc.setRootElement(Requirment);
				
				bpMap = BPController.getBpMap();
				

				for (Entry entry : bpMap.entrySet()) {
					priority = entry.getKey().toString();
					bpObj = (BusinessProcess) entry.getValue();
					actor = bpObj.getActor().getString();
					verb = bpObj.getVerb().getString();
					noun= bpObj.getNoun().getString();
					
					Element bpXML = new Element("BusinessProcess");
					bpXML.setAttribute(new Attribute("priority", priority));
					bpXML.setAttribute(new Attribute("actor", actor));
					bpXML.setAttribute(new Attribute("verb", verb));
					bpXML.setAttribute(new Attribute("noun", noun));

					doc.getRootElement().addContent(bpXML);
					
					for(Entry entryStep : bpObj.getStepMap().entrySet() ) {
						stepObj = (Step) entryStep.getValue();
						stepPriority = Integer.toString(stepObj.getStepPriority());
						actor = stepObj.getActor().getString();
						verb = stepObj.getVerb().getString();
						noun= stepObj.getNoun().getString();
						
						Element stpXML = new Element("Step");
						stpXML.setAttribute(new Attribute("priority", priority));
						stpXML.setAttribute(new Attribute("actor", actor));
						stpXML.setAttribute(new Attribute("verb", verb));
						stpXML.setAttribute(new Attribute("noun", noun));

						bpXML.addContent(stpXML);
						
						for(Entry entryAction : stepObj.getActionMap().entrySet() ) {
							actionObj = (Action)entryAction.getValue();
							actionPriority = Integer.toString(actionObj.getActionPriority());
							actor = actionObj.getActor().getString();
							verb = actionObj.getVerb().getString();
							noun= actionObj.getNoun().getString();
							Element actXML = new Element("Action");
							actXML.setAttribute(new Attribute("priority", priority));
							actXML.setAttribute(new Attribute("actor", actor));
							actXML.setAttribute(new Attribute("verb", verb));
							actXML.setAttribute(new Attribute("noun", noun));

							stpXML.addContent(actXML);
						}
					}
				}

				// new XMLOutputter().output(doc, System.out);
				XMLOutputter xmlOutput = new XMLOutputter();

				// display nice nice
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(doc, new FileWriter("Requirment.xml"));

				System.out.println("File Saved!");
			  } catch (IOException io) {
				System.out.println(io.getMessage());
			  }
			dispose();
			
		}
	}

}
