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
		Token token = tokenList.get(index);
		Token latterToken = tokenList.get(index+1);
		if(index == 0){
			if(latterToken.getType() == Type.LIST_OL){
				token.setValue(new Value("<ol><li>" + token.getValue().getValue() + "</li>"));
			}else{
				token.setValue(new Value("<ol><li>" + token.getValue().getValue() + "</li></ol>"));
			}
		}else{
			Token formerToken = tokenList.get(index-1);
			if(formerToken.getType() == Type.LIST_OL && latterToken.getType() == Type.LIST_OL){
				token.setValue(new Value("<li>" + token.getValue().getValue() + "</li>"));
			}else if(formerToken.getType() == Type.LIST_OL && latterToken.getType() != Type.LIST_OL){
				token.setValue(new Value("<li>" + token.getValue().getValue() + "</li></ol>"));
			}else if(formerToken.getType() != Type.LIST_OL && latterToken.getType() == Type.LIST_OL){
				token.setValue(new Value("<ol><li>" + token.getValue().getValue() + "</li>"));
			}else{
				token.setValue(new Value("<ol><li>" + token.getValue().getValue() + "</li></ol>"));
			}
		}
		
	}

}
