package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Value;


/**
 * h1��ǩ����Ⱦ��
 * @author ��־��
 * @date 2018��1��7��
 */
public class H1Rendering extends Rendering {
	
	/**
	 * ƴ��<h1></h1>��ǩ
	 * 
	 * @param tokenList Ҫ��Ⱦ��token��list
	 * 		  index ����h1��token��list���index
	 */
	@Override
	public void render(List<Token> tokenList, int index) {
		String value = tokenList.get(index).getValue().getValue();
		String newValue = "<h1>" + value + "</h1>";
		tokenList.get(index).setValue(new Value(newValue));
	}
	
}
