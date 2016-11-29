package Controller;

import java.util.Map;
import java.util.Map.Entry;

import Model.Action;
import Model.BusinessProcess;
import Model.Step;

public class GenerateController {
	String name, priority, actor, verb, noun,stepPriority,actionPriority;
	BusinessProcess bpObj;
	Step stepObj;
	Action actionObj;
	Map<Integer,BusinessProcess> bpMap;
	
	
	
	public String generateRequirenments() {
		StringBuffer strBuffer = new StringBuffer();
		bpMap = BPController.getBpMap();
		
		for (Entry entry : bpMap.entrySet()) {
			priority = entry.getKey().toString();
			bpObj = (BusinessProcess) entry.getValue();
			actor = bpObj.getActor().getString();
			verb = bpObj.getVerb().getString();
			noun= bpObj.getNoun().getString();
			strBuffer.append("R." + priority + ". The system must allow " + actor + " to " + verb + " "	+ noun + ".\r\n");
			for(Entry entryStep : bpObj.getStepMap().entrySet() ) {
				stepObj = (Step) entryStep.getValue();
				stepPriority = Integer.toString(stepObj.getStepPriority());
				actor = stepObj.getActor().getString();
				verb = stepObj.getVerb().getString();
				noun= stepObj.getNoun().getString();
				strBuffer.append("\tR." + priority + "." + stepPriority
						+ ". The system must allow " + actor + " to " + verb + " "
						+ noun + ".\r\n");
				for(Entry entryAction : stepObj.getActionMap().entrySet() ) {
					actionObj = (Action)entryAction.getValue();
					actionPriority = Integer.toString(actionObj.getActionPriority());
					actor = actionObj.getActor().getString();
					verb = actionObj.getVerb().getString();
					noun= actionObj.getNoun().getString();
					strBuffer.append("\t\tR." + priority + "." + stepPriority +
							"." + actionPriority
							+ ". The system must allow " + actor + " to " + verb + " "
							+ noun + ".\r\n");
				}
			}
		}
		
		return strBuffer.toString();
	}
	
}
