package test.team9.analysis;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.team9.analysis.TableAnalyser;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;
import test.team9.testReader.Input;

/**
 * 测试table的token是否分割正确
 * @author 虎舒翔
 *
 */
public class TableAnalyserTest {
	List<Token> tokenList = null;
	TableAnalyser analyse;
	@Before
	public void setUp() throws Exception {
		Input input = new Input();
		tokenList = input.readMarkdownFile("./source/tableExample/table.txt");
		analyse = new TableAnalyser();
	}

	@Test
	public void testHandle() {
		List<Token> list = analyse.handle(tokenList);
		for(Token obj : list){
			System.out.println(obj);
		}
		assertEquals(14,list.size());
		System.out.println(list.size());
	}

}
