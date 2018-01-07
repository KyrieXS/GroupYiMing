package test.team9.rendering;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.team9.analysis.QUOTEAnalyser;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;
import edu.team9.rendering.QuoteRendering;

public class QuoteRenderingTest {
	List<Token> list = new LinkedList<>();
	QuoteRendering test = new QuoteRendering();
	@Before
	public void setUp() throws Exception {
		list.add(new Token(Type.QUOTE,new Value("123\n123\n123\n")));
		list.add(new Token(Type.QUOTE,new Value("1\n2\n3\n")));
		list.add(new Token(Type.QUOTE,new Value("123123123123")));
		//list.add(new Token(Type.QUOTE,new Value("123\n123\n123\n")));
		//list.add(new Token(Type.QUOTE,new Value("123\n123\n123\n")));
	}

	@Test
	public void testRender() {
		for(int i = 0;i < list.size();i++) {
			test.render(list, i);
			System.out.println(list.get(i).getValue().getValue());
		}
		fail("Not yet implemented");
	}

}
