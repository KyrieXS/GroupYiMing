package edu.team9.rendering;

import java.util.List;

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
		if(index == 0){
			renderA(tokenList,index);
		}else{
			renderB(tokenList,index);
		}
	}
	
	public void renderA(List<Token> tokenList, int index){
		Token token = tokenList.get(index);
		Token latterToken = tokenList.get(index+1);
		if(latterToken.getType() == Type.LIST_UL){
			token.setValue(new Value("<ul><li>" + token.getValue().getValue() + "</li>"));
		}else{
			token.setValue(new Value("<ul><li>" + token.getValue().getValue() + "</li></ul>"));
		}
	}
	
	public void renderB(List<Token> tokenList, int index){
		Token token = tokenList.get(index);
		Token latterToken = tokenList.get(index+1);
		Token formerToken = tokenList.get(index-1);
		if(formerToken.getType() == Type.LIST_UL && latterToken.getType() == Type.LIST_UL){
			token.setValue(new Value("<li>" + token.getValue().getValue() + "</li>"));
		}else if(formerToken.getType() == Type.LIST_UL && latterToken.getType() != Type.LIST_UL){
			token.setValue(new Value("<li>" + token.getValue().getValue() + "</li></ul>"));
		}else if(formerToken.getType() != Type.LIST_UL && latterToken.getType() == Type.LIST_UL){
			token.setValue(new Value("<ul><li>" + token.getValue().getValue() + "</li>"));
		}else{
			token.setValue(new Value("<ul><li>" + token.getValue().getValue() + "</li></ul>"));
		}
	}
}
