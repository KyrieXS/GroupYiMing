package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * ul��ǩ���﷨������
 * @author ����
 *
 */
public class ListUlRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		Token token = tokenList.get(index);
		Token latterToken = tokenList.get(index+1);
		if(index == 0){
			if(latterToken.getType() == Type.LIST_UL){
				token.setValue(new Value("<ul><li>" + token.getValue().getValue() + "</li>"));
			}else{
				token.setValue(new Value("<ul><li>" + token.getValue().getValue() + "</li></ul>"));
			}
		}else{
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

}
