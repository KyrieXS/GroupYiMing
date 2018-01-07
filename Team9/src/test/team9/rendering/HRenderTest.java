package test.team9.rendering;

import java.util.ArrayList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;
import edu.team9.rendering.H1Rendering;
import edu.team9.rendering.H2Rendering;

/**
 * H1Rendering和H2Rendering的单元测试
 * @author 李志浩
 * @date 2018年1月7日
 */
public class HRenderTest {
	
	public static void main(String[] args) {
		Token t1 = new Token(Type.TITLE_ONE, new Value("h1"));
		Token t2 = new Token(Type.TITLE_TWO, new Value("h2"));
		
		List<Token> tokens = new ArrayList<Token>();
		tokens.add(t1);
		tokens.add(t2);
		
		H1Rendering h1r = new H1Rendering();
		h1r.render(tokens, 0);
		H2Rendering h2r = new H2Rendering();
		h2r.render(tokens, 1);
		
		for(int i = 0; i < 2; i++) {
			System.out.println(tokens.get(i).getValue().getValue());
		}
	}
	
}
