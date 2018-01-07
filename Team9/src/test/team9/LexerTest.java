package test.team9;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.team9.Lexer;
import edu.team9.element.Token;
import edu.team9.io.Reader;

public class LexerTest {
	
	Lexer lexer;
	Reader reader;
	@Before
	public void setUp() throws Exception {
		reader = new Reader();
		lexer = new Lexer(reader.readMarkdownFile("./source/file.md"));
	}

	@Test
	public void testLexer() {
		List<Token> tokenList = lexer.lex();
		for(Token token:tokenList){
			System.out.println(token);
		}
	}

}
