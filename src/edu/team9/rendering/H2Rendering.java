package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Value;


/**
 * h2��ǩ����Ⱦ��
 * @author ��־��
 * @date 2018��1��7��
 */
public class H2Rendering extends Rendering {
	
	/**
	 * ƴ��<h2></h2>��ǩ
	 * 
	 * @param tokenList Ҫ��Ⱦ��token��list
	 * 		  index ����h2��token��list���index
	 */
	@Override
	public void render(List<Token> tokenList, int index) {
		String value = tokenList.get(index).getValue().getValue();
		String newValue = "<h2>" + value + "</h2>";
		tokenList.get(index).setValue(new Value(newValue));
	}
	
}
