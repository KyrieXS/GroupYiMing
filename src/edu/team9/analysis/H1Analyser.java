package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * h1标签的分析器，识别#，= 关键字
 * 
 * @author 李志浩
 * @date 2018年1月6日
 */
public class H1Analyser extends Analyser {

	/**
	 * 处理h1的关键字
	 * 
	 * @param list 要处理的token的list
	 * @return tokens 经过处理之后生成的token的list
	 */
	@Override
	public List<Token> handle(List<Token> list) {
		LinkedList<Token> tokens = new LinkedList<Token>();
		
		for (int i = 0; i < list.size(); i++) {
			Token token = list.get(i);
			
			// 如果是common类型（即文本），则进行判断，否则直接add
			if (token.isCommon()) { 
				String str = token.getValue().getValue();
				if (str.startsWith("# ")) { // 一级标题
					String value = str.substring(2, str.length());
					Token h1Token = new Token(Type.TITLE_ONE, new Value(value));
					tokens.add(h1Token);
				} else if (str.startsWith("#")) { // 一级标题
					String value = str.substring(1, str.length());
					Token h1Token = new Token(Type.TITLE_ONE, new Value(value));
					tokens.add(h1Token);
				} else if (i < list.size() - 1 && list.get(i + 1).isCommon() 
						&& list.get(i + 1).getValue().getValue().matches("^[=]+$")) { // 一级标题
					Token h1Token = new Token(Type.TITLE_ONE, new Value(str));
					tokens.add(h1Token);
					i++;
				} else {      //未匹配到关键字
					tokens.add(token);
				}
			} else {
				tokens.add(token);
			}
		}

		return tokens;
	}

}
