package edu.team9.rendering;

import java.util.List;

import edu.team9.debug.Assert;
import edu.team9.debug.DebugConfig;
import edu.team9.element.Token;
import edu.team9.element.Value;

/**
 * img��ǩ���﷨������
 * @author ����
 *
 */
public class ImgRendering extends Rendering{

	@Override
	public void render(List<Token> tokenList, int index) {
		if(DebugConfig.CHECK_RENDERING_IMG){
			Assert.notEmpty(tokenList, "�����List<Token>Ϊ��");
		}
		Token token = tokenList.get(index);
		String val[] = token.getValue().getValue().split(";");
		token.setValue(new Value("<img src='" + val[0] + "' alt='" + val[1] + "' />"));
	}

}
