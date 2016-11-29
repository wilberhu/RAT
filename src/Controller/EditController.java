package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.Action;
import Model.BusinessProcess;
import Model.Step;

public class EditController {

	BPController bpController;
	BusinessProcess bpObj;
	List<String> editList;
	Step stpObj;
	Action actObj;
	
	
	public List<String> getBPContent(int keyBP) {
		
		bpController = new BPController();
		bpObj = bpController.getBP(keyBP);
				
		editList = new ArrayList<String>();
		editList.add(bpObj.getString());
		editList.add(String.valueOf(bpObj.getPriority()));
		editList.add(bpObj.getActor().getString());
		editList.add(bpObj.getVerb().getString());
		editList.add(bpObj.getNoun().getString());
		
		return editList;
	}

	public List<String> getSTPContent(int keyBP, int keySTP) {
		
		bpController = new BPController();
		bpObj = bpController.getBP(keyBP);
		stpObj = bpObj.getStep(keySTP);
		
		editList = new ArrayList<String>();
		editList.add(bpObj.getString());
		editList.add(stpObj.getString());
		editList.add(stpObj.getActor().getString());
		editList.add(stpObj.getVerb().getString());
		editList.add(stpObj.getNoun().getString());
		
		return editList;
	}
	
	public List<String> getACTContent(int keyBP, int keySTP, int keyACT) {
		
		bpController = new BPController();
		bpObj = bpController.getBP(keyBP);
		stpObj = bpObj.getStep(keySTP);
		actObj = stpObj.getAction(keyACT);
		
		editList = new ArrayList<String>();
		editList.add(bpObj.getString());
		editList.add(stpObj.getString());
		editList.add(actObj.getString());
		editList.add(actObj.getActor().getString());
		editList.add(actObj.getVerb().getString());
		editList.add(actObj.getNoun().getString());
		
		return editList;
	}

	public void editBusinessProcess(String bpName, String bpPriority, String bpActor, String bpVerb, String bpNoun) {

		Map bpMap = BPController.getBpMap();
		bpObj = (BusinessProcess) bpMap.get(Integer.parseInt(bpPriority));
		bpObj.setString(bpName);
		bpObj.getActor().setString(bpActor);
		bpObj.getVerb().setString(bpVerb);
		bpObj.getNoun().setString(bpNoun);
	}

	public void editStep(String bpPriority, String stpName, String stpPriority, String stpActor, String stpVerb, String stpNoun) {
		Map bpMap = BPController.getBpMap();
		bpObj = (BusinessProcess) bpMap.get(Integer.parseInt(bpPriority));
		stpObj = bpObj.getStep(Integer.parseInt(stpPriority));
		stpObj.setString(stpName);
		stpObj.getActor().setString(stpActor);
		stpObj.getVerb().setString(stpVerb);
		stpObj.getNoun().setString(stpNoun);
		
	}
	
	public void editAction(String bpPriority, String stpPriority, String actName, String actPriority, String actActor, String actVerb, String actNoun) {
		Map bpMap = BPController.getBpMap();
		bpObj = (BusinessProcess) bpMap.get(Integer.parseInt(bpPriority));
		stpObj = bpObj.getStep(Integer.parseInt(stpPriority));
		actObj = stpObj.getAction(Integer.parseInt(actPriority));
		stpObj.setString(actName);
		stpObj.getActor().setString(actActor);
		stpObj.getVerb().setString(actVerb);
		stpObj.getNoun().setString(actNoun);
		
	}
}





















