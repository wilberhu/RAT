package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Controller.BPController;

public class Step extends Description implements IString{
	
	private String myString;
	private Map<Integer,Action> actionMap;	
	private int stepPriority;

	
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
	
	public void createAction(String actName,String actPriority,String actorStr,String verbStr, String nounStr) {
		Action action = new Action(actName,actPriority,actorStr,verbStr,nounStr);
		saveAction(Integer.valueOf(actPriority),action);
	}
	
	private void saveAction(Integer actPriority,Action action) {
		this.getActionMap().put(actPriority,action);
	}

	public Action getAction(int keyACT) {

		
		return actionMap.get(keyACT);
	}
	

}
