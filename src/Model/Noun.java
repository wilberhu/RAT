package Model;

public class Noun implements IString{

	private String myString;

	public Noun(String nounStr) {

		this.myString = nounStr;
		
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
