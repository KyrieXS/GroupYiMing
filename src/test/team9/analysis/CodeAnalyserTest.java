package test.team9.analysis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.team9.analysis.CodeAnalyser;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * CodeAnalyser类单元测试
 * @author 李志浩
 * @date 2018年1月6日
 */
public class CodeAnalyserTest {

	
	public static void main(String[] args) {
		testHandle();
	}
	
	

	public static void testHandle() {
		Token t1 = new Token(new Value("`code`"));  
		Token t2 = new Token(new Value("not"));  
		Token t3 = new Token(new Value("`code`not`code`"));  
		List<Token> tokens = new ArrayList<Token>();
		tokens.add(t1);
		tokens.add(t2);
		tokens.add(t3);
		CodeAnalyser ca = new CodeAnalyser();
		List<Token> newtokens = ca.handle(tokens);
		System.out.println(newtokens.size());
		for(int i = 0; i < newtokens.size();i++) {
			System.out.println(newtokens.get(i).toString());
		}
		
	}

}
