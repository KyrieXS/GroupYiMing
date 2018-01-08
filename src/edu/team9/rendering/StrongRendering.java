package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;

public class StrongRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		Token token = tokenList.get(index);
		String str = "<strong>" + token.getValue().getValue() + "</strong>";
		tokenList.get(index).getValue().setValue(str);
	}
	
}
