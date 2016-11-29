package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Model.Action;
import Model.BusinessProcess;
import Model.Step;

public class BPController {
	
	private BusinessProcess businessProcess;
	private Step step;
	private Action action;
	
	private static Map<Integer,BusinessProcess> bpMap = new HashMap<Integer,BusinessProcess>();

	public static Map<Integer, BusinessProcess> getBpMap() {
		return bpMap;
	}

	
	public void createBusinessProcess(String bPName, String bPpriority, String actorStr,
			String verbStr, String nounStr) {
		
		businessProcess = new BusinessProcess(bPName,bPpriority,actorStr,verbStr,nounStr);
		saveBusinessProcess(Integer.valueOf(bPpriority),businessProcess);
		
	}
	public void createStep(String bpPriority, String stpName,String stpPriority,String actorStr,String verbStr, String nounStr) {
		
		BusinessProcess bpObj = getBP(Integer.parseInt(bpPriority));
		bpObj.createStep(stpName,stpPriority,actorStr,verbStr,nounStr);
	}
	public void createAction(String bpPriority,String stpPriority,String actName,String actPriority,String actorStr,String verbStr, String nounStr) {
		BusinessProcess bpObj = getBP(Integer.parseInt(bpPriority));
		Step stepObj = bpObj.getStep(Integer.parseInt(stpPriority));
		stepObj.createAction(actName,actPriority,actorStr,verbStr,nounStr);
	}

	private void saveBusinessProcess(Integer bPpriority,BusinessProcess businessProcess) {

		bpMap.put(bPpriority, businessProcess);		
		
	}
	
	BusinessProcess getBP(int bpPriority) {
		return bpMap.get(bpPriority);
	}
	
	public List<String> getAllBP() {
		
		List<String> listBP = new ArrayList<String>();
		
		for (Entry entry : bpMap.entrySet()) {

			BusinessProcess bpObj = (BusinessProcess) entry.getValue();

			listBP.add(entry.getKey().toString() + " " + bpObj.getString());
		}
		
		return listBP;
	}
	
	public List<String> getAllStep(int keyBP) {
		
		BusinessProcess bpObj = bpMap.get(keyBP);
		List<String> listSTP = bpObj.getSteps();
		return listSTP;
	}
	
}
