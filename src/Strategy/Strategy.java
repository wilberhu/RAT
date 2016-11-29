package Strategy;

public abstract class Strategy {
	Context context;
	public void setContext(Context c){
		context = c;
	}
	
	public abstract void save();

}
