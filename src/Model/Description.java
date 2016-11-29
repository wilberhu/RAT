package Model;

public class Description {
	
	private Actor actor;
	private Verb verb;
	private Noun noun;

	public Description(String actorStr, String verbStr, String nounStr) {
			
		actor = new Actor(actorStr);
		verb = new Verb(verbStr);
		noun = new Noun(nounStr);
	}
	
	
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	public Verb getVerb() {
		return verb;
	}
	public void setVerb(Verb verb) {
		this.verb = verb;
	}
	public Noun getNoun() {
		return noun;
	}
	public void setNoun(Noun noun) {
		this.noun = noun;
	}
	
	

}
