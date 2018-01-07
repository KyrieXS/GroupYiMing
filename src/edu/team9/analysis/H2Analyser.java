package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * h2��ǩ�ķ�������ʶ��## ��- �ؼ���
 * 
 * @author ��־��
 * @date 2018��1��6��
 */
public class H2Analyser extends Analyser {

	/**
	 * ����h2�Ĺؼ���
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
				if (str.startsWith("## ")) { // ��������
					String value = str.substring(3, str.length());
					Token h2Token = new Token(Type.TITLE_TWO, new Value(value));
					tokens.add(h2Token);
				} else if (str.startsWith("##")) { // ��������
					String value = str.substring(2, str.length());
					Token h2Token = new Token(Type.TITLE_TWO, new Value(value));
					tokens.add(h2Token);
				} else if (i < list.size() - 1 && list.get(i + 1).isCommon() 
						&& list.get(i + 1).getValue().getValue().matches("^[-]+$")) { // ��������
					Token h2Token = new Token(Type.TITLE_TWO, new Value(str));
					tokens.add(h2Token);
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
