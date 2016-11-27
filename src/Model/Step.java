package Model;

import java.util.HashMap;
import java.util.Map;

public class Step extends Description implements IString{
	
	private String myString;
	private Map<Integer,Action> actionMap;
	
	private int stepPriority;

	public Step() {
		
	}
	
	public Step(String sTPName, String stepPriority, String actorStr, String verbStr, String nounStr) {
		super(actorStr, verbStr, nounStr);
		
		this.stepPriority = Integer.valueOf(stepPriority);
		this.myString = sTPName ;
	}
	
	public Map<Integer,Action> getActionMap() {
		if(actionMap == null)
		{
			return actionMap = new HashMap<Integer,Action>();
		}
		
		return actionMap;
	}
	
	public void setActionMap(Map<Integer,Action> actionMap) {
		this.actionMap = actionMap;
	}
	
	
	
	public int getStepPriority() {
		return stepPriority;
	}
	public void setStepPriority(int stepPriority) {
		this.stepPriority = stepPriority;
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
	
	
	

}
