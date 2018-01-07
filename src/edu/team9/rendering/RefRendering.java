package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Value;

/**
 * @author 许硕
 * @Description 对词法分析后的引用关键字进行语法分析
 */
public class RefRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		// TODO Auto-generated method stub
		Token token  = tokenList.get(index);
		String str = token.getValue().getValue();
		String result = "";
		result += "<blockquote>";
		for(int i = 0;i < str.length();i++) {
			if(str.charAt(i) == '\n')
				result += "<br>";
			else
				result += str.charAt(i);
		}
		result += "</blockquote>";
		tokenList.get(index).setValue(new Value(result));
		//token.getValue().setValue(result);
	}

}
