package Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Model.BusinessProcess;
import Model.Step;
import Model.Action;

public class BPController {
	
	private BusinessProcess businessProcess;
	private Step step;
	private Action action;
	
	private static Map<Integer,BusinessProcess> bpMap = new HashMap<Integer,BusinessProcess>();

	public static Map<Integer, BusinessProcess> getBpMap() {
		return bpMap;
	}

	
	public BusinessProcess createBusinessProcess(String bPName, String bPpriority, String actorStr,
			String verbStr, String nounStr) {

		businessProcess = new BusinessProcess(bPName,bPpriority,actorStr,verbStr,nounStr);
		saveBusinessProcess(Integer.valueOf(bPpriority),businessProcess);
		return businessProcess;
	}
	public Step createStep(String bpPriority, String stpName,String stpPriority,String actorStr,String verbStr, String nounStr) {
		step = new Step(stpName,stpPriority,actorStr,verbStr,nounStr);
		saveStep(Integer.valueOf(bpPriority),Integer.valueOf(stpPriority),step);
		return step;
	}
	public Action createAction(String bpPriority,String stpPriority,String actName,String actPriority,String actorStr,String verbStr, String nounStr) {
		action = new Action(actName,actPriority,actorStr,verbStr,nounStr);
		saveAction(Integer.valueOf(bpPriority),Integer.valueOf(stpPriority),Integer.valueOf(actPriority),action);
		return action;
	}

	private void saveBusinessProcess(Integer bPpriority,BusinessProcess businessProcess) {

		bpMap.put(bPpriority, businessProcess);		
		
	}
	
	private void saveStep(Integer bpPriority, Integer stpPriority,Step step) {
		Map<Integer, BusinessProcess> mapBP = BPController.getBpMap();
		BusinessProcess bpObj = mapBP.get(bpPriority);
		bpObj.getStepMap().put(Integer.valueOf(stpPriority),step);		
		
	}
	
	private void saveAction(Integer bpPriority,Integer stpPriority,Integer actPriority,Action action) {

		Map<Integer, BusinessProcess> mapBP = BPController.getBpMap();
		BusinessProcess bpObj = mapBP.get(bpPriority);
		
		for(Entry entry: bpObj.getStepMap().entrySet()){
			
			if(Integer.parseInt((entry.getKey().toString())) == stpPriority)
			{
				Step stepObj = (Step)entry.getValue();
				//add action to this step
				stepObj.getActionMap().put(Integer.valueOf(actPriority),action);
				break;
				
			}	
		}
	}
}
