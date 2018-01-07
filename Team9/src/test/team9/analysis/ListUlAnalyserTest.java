package test.team9.analysis;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import edu.team9.analysis.ListUlAnalyser;
import edu.team9.element.Token;
import edu.team9.element.Value;

/**
 * 对ListUlAnalyser功能的单元测试
 * @author 刘洋
 *
 */
public class ListUlAnalyserTest {

	LinkedList<Token> tokenList = null;
	ListUlAnalyser analyser;

	@Before
	public void setUp() throws Exception {
		tokenList = new LinkedList();
		tokenList.add(new Token(new Value("")));
		tokenList.add(new Token(new Value("- 123")));
		tokenList.add(new Token(new Value("+ 123")));
		tokenList.add(new Token(new Value("*123")));
		tokenList.add(new Token(new Value("-123")));
		tokenList.add(new Token(new Value("+123")));
		analyser = new ListUlAnalyser();
	}

	@Test
	public void test() {
		tokenList = (LinkedList<Token>) analyser.handle(tokenList);
		for(Token token:tokenList){
			System.out.println(token.getType());
			System.out.println(token.getValue().getValue());
		}
	}

}
