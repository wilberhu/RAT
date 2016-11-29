package Strategy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import Controller.BPController;
import Model.Action;
import Model.BusinessProcess;
import Model.Step;

public class SaveAsXML extends Strategy{
	String name, priority, actor, verb, noun,stepPriority,actionPriority;
	BusinessProcess bpObj;
	Step stepObj;
	Action actionObj;
	Map<Integer,BusinessProcess> bpMap;
	@Override
	public void save() {
		// TODO Auto-generated method stub
		try {

			String actor,verb,noun,priority;
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
		
	}

}
