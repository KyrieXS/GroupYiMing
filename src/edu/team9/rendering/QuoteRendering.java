package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;

/**
 * @author 许硕
 * @Date 2018-01-08
 * @Decription 对分析出的代码块语法进行语法分析
 */

public class QuoteRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		Token currentToken = tokenList.get(index);
		String str = currentToken.getValue().getValue();
		String result = "<p><code>";
		//"</code></p>"
		for(int i = 0;i < str.length();i++) {
			if(str.charAt(i) == '\n') {
				result += "<br>";
			}else {
				result += str.charAt(i);
			}
		}
		result += "</code></p>";
		tokenList.get(index).getValue().setValue(result);

	}

}
