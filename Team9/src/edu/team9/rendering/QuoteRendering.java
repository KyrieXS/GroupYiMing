package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;

public class QuoteRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		// TODO Auto-generated method stub
		Token currentToken = tokenList.get(index);
		String str = currentToken.getValue().getValue();
		str = "<p><code>" + str + "</code></p>";
		tokenList.get(index).getValue().setValue(str);
	}

}
