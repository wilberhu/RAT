package Controller;

import Strategy.Context;
import Strategy.SaveAsBinary;
import Strategy.Strategy;

public class ExportController {
	Strategy strategy;
	public void use(Strategy s){
		strategy = s;
		Context context = new Context();
//		strategy = new SaveAsBinary();
		strategy.setContext(context);
		strategy.save();
		
		
	}

}
