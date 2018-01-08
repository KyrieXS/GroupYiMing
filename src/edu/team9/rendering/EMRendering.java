package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;

/**
 * @author ÐíË¶
 * @Date 2018-01-08
 */
public class EMRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		Token token = tokenList.get(index);
		String str = "<em>" + token.getValue().getValue() + "</em>";
		tokenList.get(index).getValue().setValue(str);
	}
	
}
