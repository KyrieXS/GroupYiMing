package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;

public class ParagraphRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		Token currentToken = tokenList.get(index);
		String str = currentToken.getValue().getValue();
		str = "<br>";
		tokenList.get(index).getValue().setValue(str);
	}
	
}
