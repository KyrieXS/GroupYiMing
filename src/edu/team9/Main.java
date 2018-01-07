package edu.team9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Value;
import edu.team9.io.Reader;

/**
 * main函数类
 * @author 全组
 *
 */
 
 //for test
public class Main {

	public static void main(String[] args) {
		Reader reader = new Reader();
		Lexer lexer = new Lexer(reader.readMarkdownFile("./source/file.md"));
		List<Token> tokenList = lexer.lex();
		Parser parser = new Parser(tokenList);
		BufferedReader br = new BufferedReader(new StringReader(parser.parse()));
		String line;
		try {
			while ((line = br.readLine()) != null) { 
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
