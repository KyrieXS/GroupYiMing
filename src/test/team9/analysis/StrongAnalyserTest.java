package test.team9.analysis;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.team9.analysis.StrongAnalyser;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

public class StrongAnalyserTest {
	List<Token> list = new LinkedList<>();
	StrongAnalyser test = new StrongAnalyser();
	
	@Before
	public void setUp() throws Exception {
		list.add(new Token(Type.CODE,new Value("123")));
		list.add(new Token(Type.COMMON,new Value("123***12****12***12**123")));
		list.add(new Token(Type.COMMON,new Value("**12***12****12***")));
		list.add(new Token(Type.CODE,new Value("123")));
		list.add(new Token(Type.COMMON,new Value("123")));
		list.add(new Token(Type.CODE,new Value("> ")));
		list = test.handle(list);
	}

	@Test
	public void testHandle() {
		for(Token token : list) {
			System.out.println(token);
		}
		fail("Not yet implemented");
	}

}
