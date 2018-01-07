package test.team9.analysis;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.team9.analysis.HrAnalyser;
import edu.team9.analysis.TableAnalyser;
import edu.team9.element.Token;
import test.team9.testReader.Input;
/**
 * ²âÊÔ·Ö¸ô·ûµÄtoken
 * @author »¢ÊæÏè
 *
 */
public class HrAnalyserTest {
	List<Token> tokenList = null;
	HrAnalyser analyse;
	@Before
	public void setUp() throws Exception {
		Input input = new Input();
		tokenList = input.readMarkdownFile("./source/hr.txt");
		analyse = new HrAnalyser();
	}

	@Test
	public void test() {
		List<Token> list = analyse.handle(tokenList);
		for(Token token : list) {
			System.out.println(token);
		}
		
	}

}
