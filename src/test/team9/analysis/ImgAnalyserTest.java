package test.team9.analysis;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import edu.team9.analysis.ImgAnalyser;
import edu.team9.analysis.LinkAnalyser;
import edu.team9.element.Token;
import edu.team9.element.Value;

/**
 * 对ImgAnalyser功能的单元测试
 * @author 刘洋
 *
 */
public class ImgAnalyserTest {
	LinkedList<Token> tokenList = null;
	ImgAnalyser analyser;

	@Before
	public void setUp() throws Exception {
		tokenList = new LinkedList();
		tokenList.add(new Token(new Value("![Alt text](/path/to/img.jpg)")));
		tokenList.add(new Token(new Value("![Alt text](/path/to/img.jpg 'Optional title')")));
		analyser = new ImgAnalyser();
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
