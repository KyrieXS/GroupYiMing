package test.team9.rendering;

import java.util.ArrayList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;
import edu.team9.rendering.CodeRendering;
import edu.team9.rendering.H1Rendering;
import edu.team9.rendering.H2Rendering;

/**
 * CodeRendering的单元测试
 * @author 李志浩
 * @date 2018年1月7日
 */
public class CodeRenderTest {
	
	public static void main(String[] args) {
		Token t1 = new Token(Type.CODE, new Value("code"));
		
		List<Token> tokens = new ArrayList<Token>();
		tokens.add(t1);;
		
		CodeRendering cr = new CodeRendering();
		cr.render(tokens, 0);
		
		System.out.println(tokens.get(0).getValue().getValue());
		
	}
	
}
