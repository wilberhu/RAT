package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;




import Model.Action;
import Controller.BPController;
import Model.BusinessProcess;
import Model.Step;

public class GenerateButtonListener implements ActionListener {
	String name, priority, actor, verb, noun,stepPriority,actionPriority;
	BusinessProcess bpObj;
	Step stepObj;
	Action actionObj;
	Map<Integer,BusinessProcess> bpMap;
	
	public void actionPerformed(ActionEvent e) {
		//List list2;
		//ParseBP.parseBP();
		OutputPanel.outputarea.setText("");

		//Collections.sort(ParseBP.BPIdList, sortComparator);
		
		bpMap = BPController.getBpMap();
		

		for (Entry entry : bpMap.entrySet()) {
			priority = entry.getKey().toString();
			bpObj = (BusinessProcess) entry.getValue();
			actor = bpObj.getActor().getString();
			verb = bpObj.getVerb().getString();
			noun= bpObj.getNoun().getString();
			OutputPanel.outputarea.append("R." + priority
					+ ". The system must allow " + actor + " to " + verb + " "
					+ noun + ".\r\n");
			for(Entry entryStep : bpObj.getStepMap().entrySet() ) {
				stepObj = (Step) entryStep.getValue();
				stepPriority = Integer.toString(stepObj.getStepPriority());
				actor = stepObj.getActor().getString();
				verb = stepObj.getVerb().getString();
				noun= stepObj.getNoun().getString();
				OutputPanel.outputarea.append("\tR." + priority + "." + stepPriority
						+ ". The system must allow " + actor + " to " + verb + " "
						+ noun + ".\r\n");
				for(Entry entryAction : stepObj.getActionMap().entrySet() ) {
					actionObj = (Action)entryAction.getValue();
					actionPriority = Integer.toString(actionObj.getActionPriority());
					actor = actionObj.getActor().getString();
					verb = actionObj.getVerb().getString();
					noun= actionObj.getNoun().getString();
					OutputPanel.outputarea.append("\t\tR." + priority + "." + stepPriority +
							"." + actionPriority
							+ ". The system must allow " + actor + " to " + verb + " "
							+ noun + ".\r\n");
				}
			}
		}
	}
}