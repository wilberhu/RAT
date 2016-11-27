package Controller;

import java.util.HashMap;
import java.util.Map;

import Model.Step;

public class STPController {

	private Step step;
	
	private static Map<Integer,Step> stepMap = new HashMap<Integer,Step>();
	
	public static Map<Integer, Step> getStepMap() {
		return stepMap;
	}

	public Step createStep(String sTPName, String sTPpriority, String actorStr,
			String verbStr, String nounStr) {

		step = new Step(sTPName,sTPpriority,actorStr,verbStr,nounStr);
		saveStep(Integer.valueOf(sTPpriority),step);
		return step;
		}

	private void saveStep(Integer sTPpriority,
			Step step) {

		stepMap.put(sTPpriority, step);
		
		
	}
}
