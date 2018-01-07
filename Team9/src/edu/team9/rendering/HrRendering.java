package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Value;
/**
 * 
 * @author 虎舒翔
 *分隔符的语法分析
 */

public class HrRendering extends Rendering{
		

	@Override
	public void render(List<Token> tokenList, int index) {
		Token tempToken = tokenList.get(index);
		tokenList.get(index).setValue(new Value("<hr />"));
	}
	
}
