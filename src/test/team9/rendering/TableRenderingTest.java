package test.team9.rendering;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.team9.analysis.TableAnalyser;
import edu.team9.element.Token;
import edu.team9.rendering.TableRendering;
import test.team9.testReader.Input;
/**
 * 
 * ²âÊÔtable htmlÊÇ·ñÆ´½ÓÍêÕû
 * @author »¢ÊæÏè
 *
 */
public class TableRenderingTest {
	List<Token> tokenList = null;
	TableAnalyser analyse;
	TableRendering tableRendering;
	@Before
	public void setUp() throws Exception {
		Input input = new Input();
		tokenList = input.readMarkdownFile("./source/tableExample/table.txt");
		analyse = new TableAnalyser();
		tableRendering = new TableRendering();
	}

	@Test
	public void testRender() {
		List<Token> list = analyse.handle(tokenList);
		tableRendering.render(list, 0);
		System.out.println(list.get(0));
		
	}

}
