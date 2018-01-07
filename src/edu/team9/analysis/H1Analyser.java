package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * h1��ǩ�ķ�������ʶ��#��= �ؼ���
 * 
 * @author ��־��
 * @date 2018��1��6��
 */
public class H1Analyser extends Analyser {

	/**
	 * ����h1�Ĺؼ���
	 * 
	 * @param list Ҫ�����token��list
	 * @return tokens ��������֮�����ɵ�token��list
	 */
	@Override
	public List<Token> handle(List<Token> list) {
		LinkedList<Token> tokens = new LinkedList<Token>();
		
		for (int i = 0; i < list.size(); i++) {
			Token token = list.get(i);
			
			// �����common���ͣ����ı�����������жϣ�����ֱ��add
			if (token.isCommon()) { 
				String str = token.getValue().getValue();
				if (str.startsWith("# ")) { // һ������
					String value = str.substring(2, str.length());
					Token h1Token = new Token(Type.TITLE_ONE, new Value(value));
					tokens.add(h1Token);
				} else if (str.startsWith("#")) { // һ������
					String value = str.substring(1, str.length());
					Token h1Token = new Token(Type.TITLE_ONE, new Value(value));
					tokens.add(h1Token);
				} else if (i < list.size() - 1 && list.get(i + 1).isCommon() 
						&& list.get(i + 1).getValue().getValue().matches("^[=]+$")) { // һ������
					Token h1Token = new Token(Type.TITLE_ONE, new Value(str));
					tokens.add(h1Token);
					i++;
				} else {      //δƥ�䵽�ؼ���
					tokens.add(token);
				}
			} else {
				tokens.add(token);
			}
		}

		return tokens;
	}

}
