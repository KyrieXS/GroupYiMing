package test.team9.analysis;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.team9.analysis.RefAnalyser;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

public class RefAnalyserTest {
	List<Token> list = null;
	RefAnalyser test = new RefAnalyser();
	@Before
	public void setUp() throws Exception {
		list = new LinkedList<>();
		//list = new LinkedList<>();
		list.add(new Token(Type.COMMON,new Value("> 123")));
		list.add(new Token(Type.COMMON,new Value(" 123")));
		list.add(new Token(Type.COMMON,new Value(" 123")));
		list.add(new Token(Type.CODE,new Value("123")));
		list.add(new Token(Type.COMMON,new Value("123")));
		list.add(new Token(Type.COMMON,new Value("> ")));
		list.add(new Token(Type.COMMON,new Value("```")));
		list.add(new Token(Type.COMMON,new Value("> 123")));
		list.add(new Token(Type.COMMON,new Value("123")));
		list.add(new Token(Type.COMMON,new Value("")));
		list.add(new Token(Type.COMMON,new Value("123")));
	}

	@Test
	public void testHandle() {
		list = test.handle(list);
		//System.out.println(list.size());
		for(Token token:list) {
			System.out.println(token);
		}
		fail("Not yet implemented");
	}

}
