package test.team9.analysis;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import edu.team9.analysis.LinkAnalyser;
import edu.team9.element.Token;
import edu.team9.element.Value;

/**
 * ��LinkAnalyser���ܵĵ�Ԫ����
 * @author ����
 *
 */
public class LinkAnalyserTest {
	LinkedList<Token> tokenList = null;
	LinkAnalyser analyser;
	@Before
	public void setUp() throws Exception {
		tokenList = new LinkedList();
		tokenList.add(new Token(new Value("[����](http://example.com/ 'Title')")));
		tokenList.add(new Token(new Value("[This link](http://example.net/)")));
		analyser = new LinkAnalyser();
	}

	@Test
	public void testHandle() {
		tokenList = (LinkedList<Token>) analyser.handle(tokenList);
		for(Token token:tokenList){
			System.out.println(token);
		}
	}

}
