package Model;

public class Actor implements IString{
	
	private String myString;

	public Actor(String actorStr) {

		this.myString = actorStr;
	}

	@Override
	public String getString() {

		return myString;
	}

	@Override
	public void setString(String myString) {

		this.myString = myString;
	}

}
