package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class BusinessProcess extends Description implements IString{
	
	private int priority;
	private String myString;
	private Map<Integer,Step> stepMap;
	private Step step;
	
	public BusinessProcess(String bPName, String bPpriority, String actorStr,
			String verbStr, String nounStr) {

		super(actorStr,verbStr,nounStr)	;
		
		this.priority = Integer.valueOf(bPpriority);
		this.myString = bPName ;
		
	}
	
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	


	public Map<Integer,Step> getStepMap() {
		if(stepMap == null) {
			stepMap = new HashMap<Integer,Step>();
		}
		return stepMap;
	}


	public void setStepMap(Map<Integer,Step> stepMap) {
		
		this.stepMap = stepMap;
	}
	

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return myString;
	}


	@Override
	public void setString(String myString) {
		// TODO Auto-generated method stub
		this.myString = myString;
	}


	public void createStep(String stpName, String stpPriority, String actorStr, String verbStr, String nounStr) {
		// TODO Auto-generated method stub
		step = new Step(stpName,stpPriority,actorStr,verbStr,nounStr);
		saveStep(Integer.parseInt(stpPriority),step);
	};
	
	private void saveStep(Integer stpPriority,Step step) {
			this.getStepMap().put(stpPriority,step);		
	}
	
	public List<String> getSteps() {
		List<String> listSTP = new ArrayList<String>();
		for(Entry entry: stepMap.entrySet()){
			Step stepObj = (Step)entry.getValue();
			listSTP.add(entry.getKey().toString() + " " + stepObj.getString());
		}
		
		return listSTP;
	}


	public Step getStep(int stpPriority) {
		// TODO Auto-generated method stub
		return stepMap.get(stpPriority);
		
	}
}
