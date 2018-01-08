package edu.team9.rendering;

import java.util.List;

import edu.team9.debug.Assert;
import edu.team9.debug.DebugConfig;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * ul标签的语法分析器
 * @author 刘洋
 *
 */
public class ListUlRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		if(DebugConfig.CHECK_RENDERING_LISTUL){
			Assert.notEmpty(tokenList, "传入的List<Token>为空");
		}
		if(index == 0){
			renderA(tokenList,index);
		}else{
			renderB(tokenList,index);
		}
	}
	
	public void renderA(List<Token> tokenList, int index){
		Token token = tokenList.get(index);
		token.setValue(new Value("<ul><li>" + token.getValue().getValue() + "</li></ul>"));
	}
	
	public void renderB(List<Token> tokenList, int index){
		Token token = tokenList.get(index);
		Token formerToken = tokenList.get(index-1);
		if(formerToken.getType() == Type.LIST_UL){
			String str = formerToken.getValue().getValue();
			str = str.replace("</ul>", "");
			formerToken.setValue(new Value(str));
			token.setValue(new Value("<li>" + token.getValue().getValue() + "</li></ul>"));
		}else{
			token.setValue(new Value("<ul><li>" + token.getValue().getValue() + "</li></ul>"));
		}
	}
}
