package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * code��ǩ�ķ�������ʶ��` `�ؼ���
 * @author ��־��
 * @date 2018��1��6��
 */
public class CodeAnalyser extends Analyser {

	
	/**
	 * ����code�Ĺؼ���
	 * 
	 * @param list Ҫ�����token��list
	 * @return tokens ��������֮�����ɵ�token��list
	 */
	@Override
	public List<Token> handle(List<Token> list) {
		LinkedList<Token> tokens = new LinkedList<Token>();
		boolean inCode = false;  //�жϴ˿��Ƿ��ڴ�����	
		
		for(int i = 0;i < list.size();i++) {
			Token token = list.get(i);
			
			//�����common���ͣ����ı�����������жϣ�����ֱ��add
			if(token.isCommon()) {     
				String str = token.getValue().getValue();
				int index = str.indexOf('`');
				if(index == -1 && inCode == false) {	//δƥ�䵽�ؼ���
					tokens.add(token);
					str = "";
				}
				int codeBegin = 0;
				int codeEnd = str.length();
				while(index != -1) {
					inCode = !inCode;
					if(inCode) {		//�����������Ҫ��֮ǰ�����ݱ��common
						codeBegin = index;
						String value = str.substring(0, codeBegin);
						if(!value.equals("")) {
							tokens.add(new Token(new Value(value)));
						}						
						str = str.substring(codeBegin+1, codeEnd);
						codeEnd -= (codeBegin+1);
						codeBegin = 0;
						index = str.indexOf('`');
					} else {      		//�뿪��������Ҫ��ǰ������ݱ��code
						codeEnd = index;
						String value = str.substring(codeBegin, codeEnd);
						tokens.add(new Token(Type.CODE, new Value(value)));
						str = str.substring(codeEnd+1, str.length());
						codeEnd = str.length();
						codeBegin = 0;
						index = str.indexOf('`');
					}
				}
				if(!str.equals("")) {   //��������common����Ҫadd
					tokens.add(new Token(new Value(str)));
				}
			} else {
				tokens.add(token);
			}
		}
		
		return tokens;
	}
	
}
