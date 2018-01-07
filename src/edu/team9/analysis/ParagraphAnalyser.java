package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * @author 许硕
 * @description 对段落语法关键字进行过滤
 * @Date 2018-01-16
 */
public class ParagraphAnalyser extends Analyser{
	LinkedList<Token> resultList = new LinkedList<>();
	int count = 0;
	int line = 0;
	@Override
	public List<Token> handle(List<Token> list) {
		String value = "";
		for(int i = 0;i < list.size();i++) {
			if(list.get(i).getType() == Type.COMMON) {
				String str = list.get(i).getValue().getValue();
				if(isPra(str)) {
					resultList.add(new Token(Type.PARAGRAPH,new Value("")));
				}else {
					resultList.add(new Token(Type.COMMON,new Value(str)));
				}
			}else {
				resultList.add(list.get(i));
			}
		}
		return resultList;
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
