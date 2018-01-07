package test.team9.rendering;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.team9.analysis.RefAnalyser;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;
import edu.team9.rendering.RefRendering;

public class RefRenderingTest {
	List<Token> list = new LinkedList<>();
	RefRendering test = new RefRendering();
	@Before
	public void setUp() throws Exception {
		list.add(new Token(Type.REF,new Value("123456\n")));
		list.add(new Token(Type.REF,new Value("123456\n")));
		list.add(new Token(Type.REF,new Value("123456\n")));
		list.add(new Token(Type.REF,new Value("123456\n")));
		for(int i = 0;i < list.size();i++) {
			test.render(list, i);
		}
	}

	@Test
	public void testRender() {
		for(Token token : list) {
			System.out.println(token);
		}
		fail("Not yet implemented");
	}

}
