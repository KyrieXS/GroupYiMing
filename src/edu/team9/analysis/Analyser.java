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
	 * ����String���͵�List,�����࣬��ͬ�Ĺؼ��ʶ��ڲ�ͬ��ʵ����handle
	 * @param list
	 * @return
	 */
	public abstract List<Token> handle(List<Token> list);
}
