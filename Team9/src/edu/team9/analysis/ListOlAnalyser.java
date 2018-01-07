package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * �̳�Analyser������List<Token>�Է��������б�������Token���и�ֵ
 * @author ����
 * 
 */
public class ListOlAnalyser extends Analyser{

	@Override
	public List<Token> handle(List<Token> list) {
		List<Token> newList = new LinkedList();
		String val = null;
		for(int i = 0;i < list.size();i++){
			if(!list.get(i).isCommon()){
				newList.add(list.get(i));
			}else if(list.get(i).getValue().getValue().equals("") || list.get(i).getValue().getValue().length() < 3){
				newList.add(list.get(i));
			}else{
				String str = list.get(i).getValue().getValue();
				if(isListOl(str)){
					if(str.charAt(2) == ' '){
						val = str.substring(3, str.length());
					}else{
						val = str.substring(2,str.length());
					}
					newList.add(new Token(Type.LIST_OL,new Value(val)));
				}else{
					newList.add(list.get(i));
				}
			}
		}
		return newList;
	}
	
	/**
	 * �ж��ַ����Ƿ���������б������
	 * @param str
	 * @return boolean
	 */
	private boolean isListOl(String str){
		if(Character.isDigit(str.charAt(0)) && (str.charAt(1) == '.' || str.substring(1,3).equals(". "))){
			return true;
		}
		return false;
	}
}
