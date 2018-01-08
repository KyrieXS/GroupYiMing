package edu.team9.rendering;

import java.util.List;

import edu.team9.debug.Assert;
import edu.team9.debug.DebugConfig;
import edu.team9.element.Token;
import edu.team9.element.Value;

/**
 * a标签的语法分析器
 * @author 刘洋
 *
 */
public class LinkRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		if(DebugConfig.CHECK_RENDERING_LINK){
			Assert.notEmpty(tokenList, "传入的List<Token>为空");
		}
		Token token = tokenList.get(index);
		String val[] = token.getValue().getValue().split(";");
		token.setValue(new Value("<a href='" + val[0] + "' >" + val[1] + "</a>"));
	}
	
}
