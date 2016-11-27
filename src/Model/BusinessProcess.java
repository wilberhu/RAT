package Model;

import java.util.HashMap;
import java.util.Map;

public class BusinessProcess extends Description implements IString{
	
	private int priority;
	private String myString;
	private Map<Integer,Step> stepMap;
	
	
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
	};
	
	
	

}
