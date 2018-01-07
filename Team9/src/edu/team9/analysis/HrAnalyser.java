package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Const;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;
/**
 * ·ÖÎö·Ö¸ô·û
 * @author »¢ÊæÏè
 * 
 *
 */
public class HrAnalyser extends Analyser{

	@Override
	public List<Token> handle(List<Token> list) {
		List<Token> tempList = new LinkedList();
		for(int index = 0, size = list.size(); index < size; index++){
			Token currentToken = list.get(index);
			if(currentToken.isCommon()) {
				if(currentToken.getValue().getValue().trim().matches(Const.HR_REGEX)) {
					tempList.add(new Token(Type.HR,new Value(null)));
				}else {
					tempList.add(currentToken);
				}
			}else {
				tempList.add(currentToken);
			}
			
		}
		return tempList;
	}

}
