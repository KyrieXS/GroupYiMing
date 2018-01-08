package edu.team9.analysis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.team9.Lexer;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;
import edu.team9.io.Reader;

/**
 * ǿ����ǩ�ķ�������ʶ��* _�ؼ���
 * 
 * @author ������
 * @date 2018��1��8��
 */
public class StrongAnalyser extends Analyser {

	@Override
	public List<Token> handle(List<Token> list) {
		LinkedList<Token> tokens = new LinkedList<Token>();
		int[] count = new int[3];
		String value = "";
		for (int i = 0; i < list.size(); i++) {
			Token token = list.get(i);
			if (token.isCommon()) { // common���ʹ���δ�������ϲ������δ������������Ҫ���з���������ֱ��add
				String str = token.getValue().getValue();
				for(Token element : findEm(str)) {
					tokens.add(element);
				}
			}else {
				tokens.add(token);
			}
		}
		return tokens;
	}

	public List<Token> findEm(String str) {
		List<Token> array = new ArrayList<>();
		String[] flag = {"*","**","***"};
		Type[] type = {Type.EM,Type.STRONG,Type.EMSTRONG};
		int index = str.indexOf('*');
		if(index != -1) {
			array.add(new Token(Type.COMMON,new Value(str.substring(0, index))));
			str = str.substring(index);
		}
		while(true) {
			int em = str.indexOf('*');
			if(em == -1) {
				array.add(new Token(Type.COMMON,new Value(str)));
				return array;
			}else {
				str = str.substring(em);
				int level = findMoreEm(str);
				if(level == 1)
					str = str.replaceFirst("\\*", "");
				else if(level == 2)
					str = str.replaceFirst("\\*\\*", "");
				else if(level == 3)
					str = str.replaceFirst("\\*\\*\\*", "");
				int end = str.indexOf(flag[level-1]);
				if(end != -1) {
					array.add(new Token(type[level-1],new Value(str.substring(0,end))));
					str = str.substring(end+level,str.length());
				}else {
					array.add(new Token(Type.COMMON,new Value(flag[level-1] + str.substring(0))));
					str = "";
				}
			}
		}
	}
	
	public int findMoreEm(String str) {
		int i = 0;
		for(i = 0;i < str.length();i++) {
			if(str.charAt(i) != '*') {
				return i;
			}
		}
		return i;
	}
}
