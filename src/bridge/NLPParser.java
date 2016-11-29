package bridge;

import java.awt.Color;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import View.InputPanel;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class NLPParser implements ParserInterface{
	
	private String input; 
	MaxentTagger tagger;
	Highlighter highlighter;
	
	@Override
	public void parse(Highlighter highlighter,String input) {
		
		this.highlighter = highlighter;
		this.input = input;
		
		
		try {
			
			tagger = new MaxentTagger("left3words-wsj-0-18.tagger");
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String taggerd = tagger.tagString(input);
		String[] split = taggerd.split(" ");
		
		for(String s:split){
			
			if(s.endsWith("NNP")){
				try {
					add_N(s.substring(0, (s.length()-4)));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(s.endsWith("NN")){
				try {
					add_N(s.substring(0, (s.length()-3)));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(s.endsWith("VB")){
				try {
					add_V(s.substring(0, (s.length()-3)));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
	}
	public void add_V(String s) throws BadLocationException{
		Pattern p = Pattern.compile(s);
		Matcher m = p.matcher(input);
		while(m.find()){
			highlighter.addHighlight(m.start(), m.end(),
					new DefaultHighlighter.DefaultHighlightPainter(Color.red));
		}
	}
	public void add_N(String s) throws BadLocationException{
		Pattern p = Pattern.compile(s);
		Matcher m = p.matcher(input);
		while(m.find()){
			highlighter.addHighlight(m.start(), m.end(),
					new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
		}
	}

}
