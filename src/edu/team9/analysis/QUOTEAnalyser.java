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
 * @author 许硕
 * @description 对代码块语法关键字进行过滤
 * @Date 2018-01-16
 */
public class QUOTEAnalyser extends Analyser{

	@Override
	public List<Token> handle(List<Token> list) {
		// TODO Auto-generated method stub
		LinkedList<Token> resultList = new LinkedList<>();
		String value = "";
		int count = 0;
		//如果Token的类型为COMMON，代表未分析或者上一个过滤器为分析出来，则需在该过滤器尝试进行分析
		for(int i = 0;i < list.size();i++) {
			//System.out.println(list.get(i).getValue().getValue());
			if(list.get(i).getType() == Type.COMMON) {
				String str = list.get(i).getValue().getValue();
				if(isQuote(str)) {
					count++;
					//代表这是代码块关键字的头关键字
					str = str.replaceAll("```","");
					value += str;
					if(count % 2  == 0 && count != 0) {
						resultList.add(new Token(Type.QUOTE,new Value(value)));
						value = "";
					}
				}else if(count % 2 == 1 && count != 0){
					value += str;
					value += '\n';
				}else {
					resultList.add(list.get(i));
				}
			}else {
				resultList.add(list.get(i));
			}
		}
		if(count % 2 == 1) {
			resultList.add(new Token(Type.COMMON,new Value(value)));
		}
		return resultList;
	}
	
	//判断当前Token中是否含有代码块关键字：```
	public boolean isQuote(String str){
		int index = str.indexOf("```");
		boolean flag = false;
		if(index == -1) {
			flag = false;
		}else {
			for(int i = 0;i < index;i++) {
				if(str.charAt(i) != ' ') {
					flag = false;
					break;
				}
			}
			flag = true;
		}
		return flag;
	}
}
