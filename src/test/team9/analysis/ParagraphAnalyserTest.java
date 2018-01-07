package test.team9.analysis;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.team9.analysis.ParagraphAnalyser;
import edu.team9.analysis.QUOTEAnalyser;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

public class ParagraphAnalyserTest {
	List<Token> list = null;
	ParagraphAnalyser test;
	@Before
	public void setUp() throws Exception {
		list = new LinkedList<>();
		list.add(new Token(Type.COMMON,new Value("     ")));
		list.add(new Token(Type.COMMON,new Value("  231321   ")));
		list.add(new Token(Type.COMMON,new Value("    sdaa ")));
		list.add(new Token(Type.COMMON,new Value("     ")));
		list.add(new Token(Type.COMMON,new Value("     ")));
		list.add(new Token(Type.COMMON,new Value("  sdadsa   ")));
		list.add(new Token(Type.COMMON,new Value("   sadada  ")));
		test = new ParagraphAnalyser();
	}

	@Test
	public void testHandle() {
		list = test.handle(list);
		for(Token token : list) {
			System.out.println(token.getType() + "   " + token.getValue().getValue());
		}
		fail("Not yet implemented");
	}

}
