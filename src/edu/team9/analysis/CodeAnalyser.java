package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * code标签的分析器，识别` `关键字
 * @author 李志浩
 * @date 2018年1月6日
 */
public class CodeAnalyser extends Analyser {

	
	/**
	 * 处理code的关键字
	 * 
	 * @param list 要处理的token的list
	 * @return tokens 经过处理之后生成的token的list
	 */
	@Override
	public List<Token> handle(List<Token> list) {
		LinkedList<Token> tokens = new LinkedList<Token>();
		boolean inCode = false;  //判断此刻是否处于代码区	
		
		for(int i = 0;i < list.size();i++) {
			Token token = list.get(i);
			
			//如果是common类型（即文本），则进行判断，否则直接add
			if(token.isCommon()) {     
				String str = token.getValue().getValue();
				int index = str.indexOf('`');
				if(index == -1 && inCode == false) {	//未匹配到关键字
					tokens.add(token);
					str = "";
				}
				int codeBegin = 0;
				int codeEnd = str.length();
				while(index != -1) {
					inCode = !inCode;
					if(inCode) {		//进入代码区，要把之前的内容变成common
						codeBegin = index;
						String value = str.substring(0, codeBegin);
						if(!value.equals("")) {
							tokens.add(new Token(new Value(value)));
						}						
						str = str.substring(codeBegin+1, codeEnd);
						codeEnd -= (codeBegin+1);
						codeBegin = 0;
						index = str.indexOf('`');
					} else {      		//离开代码区，要把前面的内容变成code
						codeEnd = index;
						String value = str.substring(codeBegin, codeEnd);
						tokens.add(new Token(Type.CODE, new Value(value)));
						str = str.substring(codeEnd+1, str.length());
						codeEnd = str.length();
						codeBegin = 0;
						index = str.indexOf('`');
					}
				}
				if(!str.equals("")) {   //如果最后还有common，需要add
					tokens.add(new Token(new Value(str)));
				}
			} else {
				tokens.add(token);
			}
		}
		
		return tokens;
	}
	
}
