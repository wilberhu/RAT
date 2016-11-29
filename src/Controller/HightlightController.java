package Controller;

import javax.swing.text.Highlighter;

import bridge.Parser;

public class HightlightController {
	Parser parser;
	public void hightlight(Highlighter highlighter, String input){
		parser = new Parser();
		parser.parse(highlighter,input);
	}
}
