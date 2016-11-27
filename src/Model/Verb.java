package Model;

public class Verb implements IString{

	private String myString;

	public Verb(String verbStr) {
		
		this.myString = verbStr;
	
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
