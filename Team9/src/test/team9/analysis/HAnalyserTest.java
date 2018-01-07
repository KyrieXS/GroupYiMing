package test.team9.analysis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.team9.analysis.H1Analyser;
import edu.team9.analysis.H2Analyser;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * HAnalyser类单元测试
 * @author 李志浩
 * @date 2018年1月6日
 */
public class HAnalyserTest {

	
	public static void main(String[] args) {
		testHandle();
	}
	
	

	public static void testHandle() {
		Token t1 = new Token(Type.QUOTE, new Value("common"));  //一个非common类型的token
		Token t2 = new Token(new Value("#h1"));  //一个common类型的token，符合h1标签
		Token t3 = new Token(new Value("##h2"));  //一个common类型的token，符合h2标签
		Token t4 = new Token(new Value("}"));  //一个common类型的token，不符合h标签
		Token t5 = new Token(new Value(""));
		Token t10 = new Token(new Value(""));
		Token t6 = new Token(new Value("=="));
		Token t7 = new Token(new Value("h2"));
		Token t8 = new Token(new Value("--"));
		Token t9 = new Token(new Value("# h1"));  //一个common类型的token，符合h1标签
		Token t0 = new Token(new Value("## h2"));  //一个common类型的token，符合h2标签
		List<Token> tokens = new ArrayList<Token>();
		tokens.add(t1);
		tokens.add(t2);
		tokens.add(t3);
		tokens.add(t4);
		tokens.add(t5);
		tokens.add(t10);
		tokens.add(t6);
		tokens.add(t7);
		tokens.add(t8);
		tokens.add(t9);
		tokens.add(t0);
		H1Analyser h1a = new H1Analyser();
		H2Analyser h2a = new H2Analyser();
		List<Token> tmptokens = h2a.handle(tokens);
		List<Token> newtokens = h1a.handle(tmptokens);
		System.out.println(newtokens.size());
		for(int i = 0; i < newtokens.size();i++) {
			System.out.println(newtokens.get(i).toString());
		}
		
	}

}
