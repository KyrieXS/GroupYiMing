package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;
import jdk.nashorn.internal.parser.TokenKind;

/**
 * 引用标签的分析器，识别>关键字
 * @author 刘延祺
 * @date 2018年1月6日
 */
public class RefAnalyser extends Analyser {
	String value = "";
	int count = 0;
	@Override
	public List<Token> handle(List<Token> list) {
		LinkedList<Token> tokens = new LinkedList<Token>();
		for(int i = 0;i < list.size();i++) {
			Token token = list.get(i);
			if(token.isCommon()) {     //如果是common类型（即文本），则进行判断，否则直接add
				String str = token.getValue().getValue();
				if(str.contains("> ")) {
					value = str.substring(2,str.length()) + '\n';
					count++;
					//System.out.println("coming");
					int j;
					for(j = i + 1;j < list.size();j++) {
						Token tmp = list.get(j);
						if(tmp.getType() != Type.COMMON) {
							if(count == 1) {
								tokens.add(new Token(Type.REF,new Value(value)));
								count = 0;
								value = "";
							}
							tokens.add(tmp);
							break;
						}
						else if(isPra(tmp.getValue().getValue())) {
							if(count == 1) {
								tokens.add(new Token(Type.REF,new Value(value)));
								count = 0;
								value = "";
							}
							tokens.add(tmp);
							break;
						}
						else if(tmp.getValue().getValue().contains("> ")){
							String a = tmp.getValue().getValue();
							value = value + a.substring(2, a.length()) + '\n';
						}else {
							String a = tmp.getValue().getValue();
							value = value + a + '\n';
						}
							
					}
					if(count == 1)
						tokens.add(new Token(Type.REF,new Value(value)));
					i = j;
				} else {
					tokens.add(token);
				}
			} else {
				tokens.add(token);
			}
		}
		
		return tokens;
	}
	public boolean isPra(String str) {
		boolean flag = false;
		if(str.length() == 0 || str.matches("^[ ]+$") || str.equals('\n'))
			flag = true;
		else {
			for(int i = 0;i < str.length();i++) {
				if(str.charAt(i) != ' ' || str.charAt(i) != '\n') {
					return false;
				}
			}
			flag = true;
		}
		return flag;
	}
}
