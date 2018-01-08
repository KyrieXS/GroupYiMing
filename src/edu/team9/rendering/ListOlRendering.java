package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * ol标签的语法分析器
 * @author 刘洋
 *
 */
public class ListOlRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		if(index == 0){
			renderA(tokenList,index);
		}else{
			renderB(tokenList,index);
		}
		
	}

	public void renderA(List<Token> tokenList, int index){
		Token token = tokenList.get(index);
		token.setValue(new Value("<ol><li>" + token.getValue().getValue() + "</li></ol>"));
	}
	
	public void renderB(List<Token> tokenList, int index){
		Token token = tokenList.get(index);
		Token formerToken = tokenList.get(index-1);
		if(formerToken.getType() == Type.LIST_OL){
			String str = formerToken.getValue().getValue();
			str = str.replace("</ol>", "");
			formerToken.setValue(new Value(str));
			token.setValue(new Value("<li>" + token.getValue().getValue() + "</li></ol>"));
		}else{
			token.setValue(new Value("<ol><li>" + token.getValue().getValue() + "</li></ol>"));
		}
	}
}
