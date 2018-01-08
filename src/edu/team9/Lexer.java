package edu.team9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.omg.CosNaming.NamingContextPackage.NotEmpty;

import edu.team9.analysis.Analyser;
import edu.team9.analysis.CodeAnalyser;
import edu.team9.analysis.H1Analyser;
import edu.team9.analysis.H2Analyser;
import edu.team9.analysis.HAnalyser;
import edu.team9.analysis.HrAnalyser;
import edu.team9.analysis.ImgAnalyser;
import edu.team9.analysis.LinkAnalyser;
import edu.team9.analysis.ListOlAnalyser;
import edu.team9.analysis.ListUlAnalyser;
import edu.team9.analysis.ParagraphAnalyser;
import edu.team9.analysis.QUOTEAnalyser;
import edu.team9.analysis.RefAnalyser;
import edu.team9.analysis.StrongAnalyser;
import edu.team9.analysis.TableAnalyser;
import edu.team9.debug.Assert;
import edu.team9.debug.DebugConfig;
import edu.team9.element.Token;
import edu.team9.element.Value;

public class Lexer {
	private String content;
	private static List<Token> lines;
	private static List<Analyser> analyserList = null;
	
	public static List<Token> getLines() {
		return lines;
	}

	public static void setLines(List<Token> lines) {
		Lexer.lines = lines;
	}

	public Lexer(String content){
		List<Token> lines = new LinkedList<Token>();
		BufferedReader reader = new BufferedReader(new StringReader(content));
		String line;
		try {
			while ((line = reader.readLine()) != null) { 
				lines.add(new Token(new Value(line)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(DebugConfig.CHECK_LEXER){
			Assert.notNull(lines, "传入的List<Token>为空");
		}
		this.lines = lines;
	}
	
	static void init(){
		if(analyserList == null){
			analyserList = new LinkedList();
		}
		analyserList.add(new QUOTEAnalyser());
		analyserList.add(new TableAnalyser());
		analyserList.add(new CodeAnalyser());
		analyserList.add(new StrongAnalyser());
		analyserList.add(new ListUlAnalyser());
		analyserList.add(new ListOlAnalyser());
		analyserList.add(new H2Analyser());
		analyserList.add(new H1Analyser());
		analyserList.add(new LinkAnalyser());
		analyserList.add(new ImgAnalyser());
		analyserList.add(new HrAnalyser());
		analyserList.add(new RefAnalyser());
		analyserList.add(new ParagraphAnalyser());
	}
	
	public static List<Token> lex() {
		init();
		if(DebugConfig.CHECK_LEXER){
			Assert.notEmpty(analyserList, "List<Analyser>为空");
		}
		for(int i = 0, size = analyserList.size(); i < size; i++){
			Analyser analyser = analyserList.get(i);
			lines = analyser.handle(lines);
		}
		if(DebugConfig.CHECK_LEXER){
			Assert.notNull(lines,"传入的List<Token>为空");
			
		}
		return lines;
	}
	
	public void clear(){
		this.lines = null;
		this.analyserList = null;
	}
}
