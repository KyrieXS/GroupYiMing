package edu.team9.analysis;

import java.util.ArrayList;
import java.util.List;

import edu.team9.element.Token;


/**
 * @author XS
 *
 */
public abstract class Analyser {
	
	
	//public abstract List<Token> filt(List<String> list);
	
	/**
	 * 处理String类型的List,抽象类，不同的关键词对于不同的实现类handle
	 * @param list
	 * @return
	 */
	public abstract List<Token> handle(List<Token> list);
}
