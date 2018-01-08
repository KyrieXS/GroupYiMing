package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * 继承Analyser，分析List<Token>对符合链接条件的Token进行赋值
 * @author 刘洋
 *
 */
public class LinkAnalyser extends Analyser{

	@Override
	public List<Token> handle(List<Token> list) {
		List<Token> newList = new LinkedList();
		String val = null;
		String href = null;
		boolean found1 = false;
		boolean found2 = false;
		int key = 0;
		for(int i = 0;i < list.size();i++){
			if(!list.get(i).isCommon()){
				newList.add(list.get(i));
			}else if(list.get(i).getValue().getValue().equals("")){
				newList.add(list.get(i));
			}else{
				String str = list.get(i).getValue().getValue();
				if(str.charAt(0) == '['){
					for(int temp = 0;temp < str.length();temp++){
						if(str.charAt(temp) == ']'){
							val = str.substring(1, temp);
							key = temp;
							found1 = true;
						}
						if(str.charAt(temp) == ')'){
							href = str.substring(key+2, temp);
							found2 = true;
						}
					}
					if(found1 && found2){
						newList.add(new Token(Type.LINK,new Value(href.concat(";").concat(val))));
					}else{
						newList.add(list.get(i));
					}
					
				}else{
					newList.add(list.get(i));
				}
			}
		}
		return newList;
	}
	
}
