package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Value;
/**
 * 
 * @author ������
 *�ָ������﷨����
 */

public class HrRendering extends Rendering{
		

	@Override
	public void render(List<Token> tokenList, int index) {
		Token tempToken = tokenList.get(index);
		tokenList.get(index).setValue(new Value("<hr />"));
	}
	
}
