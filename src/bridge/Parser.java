package bridge;

import javax.swing.text.Highlighter;

public class Parser {
	ParserInterface im = new NLPParser();
	public void parse(Highlighter highlighter,String input){
		im.parse(highlighter,input);
	}

}
