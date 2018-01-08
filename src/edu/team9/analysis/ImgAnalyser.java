package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.debug.Assert;
import edu.team9.debug.DebugConfig;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;

/**
 * 继承Analyser，分析List<Token>对符合图片条件的Token进行赋值
 * @author 刘洋
 *
 */
public class ImgAnalyser extends Analyser{

	@Override
	public List<Token> handle(List<Token> list) {
		if(DebugConfig.CHECK_ANALYSER_IMG){
			Assert.notNull(list, "传入的List<Token>为空");
		}
		List<Token> newList = new LinkedList();
		String val = null;
		String src = null;
		boolean found1 = false;
		boolean found2 = false;
		int key = 0;
		for(int i = 0;i < list.size();i++){
			if(!list.get(i).isCommon()){
				newList.add(list.get(i));
			}else if(list.get(i).getValue().getValue().equals("") || list.get(i).getValue().getValue().length() < 3){
				newList.add(list.get(i));
			}else{
				String str = list.get(i).getValue().getValue();
				if(str.substring(0,2).equals("![")){
					for(int temp = 0;temp < str.length();temp++){
						if(str.charAt(temp) == ']'){
							val = str.substring(2, temp);
							key = temp;
							found1 = true;
						}
						if(str.charAt(temp) == ')'){
							src = str.substring(key+2, temp);
							found2 = true;
						}
					}
					if(found1 && found2){
						newList.add(new Token(Type.IMG,new Value(src.concat(";").concat(val))));
					}else{
						newList.add(list.get(i));
					}
				}else{
					newList.add(list.get(i));
				}
			}
		}
		if(DebugConfig.CHECK_RENDERING_IMG){
			Assert.notNull(newList, "传出的List<Token>为空");
		}
		return newList;
	}

}
