package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Value;

/**
 * img标签的语法分析器
 * @author 刘洋
 *
 */
public class ImgRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		Token token = tokenList.get(index);
		String val[] = token.getValue().getValue().split(";");
		token.setValue(new Value("<img src='" + val[0] + "' alt='" + val[1] + "' />"));
	}

}
