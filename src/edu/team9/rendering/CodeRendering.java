package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Value;


/**
 * code��ǩ����Ⱦ��
 * @author ��־��
 * @date 2018��1��7��
 */
public class CodeRendering extends Rendering {
	
	/**
	 * ƴ��<code></code>��ǩ
	 * 
	 * @param tokenList Ҫ��Ⱦ��token��list
	 * 		  index ����code��token��list���index
	 */
	@Override
	public void render(List<Token> tokenList, int index) {
		String value = tokenList.get(index).getValue().getValue();
		String newValue = "<code>" + value + "</code>";
		tokenList.get(index).setValue(new Value(newValue));
	}
	
}
