package Model;


public class Action extends Description{
	private String myString;
	private int actPriority;
	
	
	public String getString() {
		return myString;
	}

	public Action(String actName,String actPriority,String actorStr, String verbStr, String nounStr) {
		super(actorStr, verbStr, nounStr);
			
		this.actPriority = Integer.valueOf(actPriority);
		this.myString = actName ;
	}

	private int actionPriority;

	public int getActionPriority() {
		return actionPriority;
	}

	public void setActionPriority(int actionPriority) {
		this.actionPriority = actionPriority;
	}
	
	
	

}
