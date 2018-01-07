package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Value;


/**
 * h1标签的渲染器
 * @author 李志浩
 * @date 2018年1月7日
 */
public class H1Rendering extends Rendering {
	
	/**
	 * 拼接<h1></h1>标签
	 * 
	 * @param tokenList 要渲染的token的list
	 * 		  index 符合h1的token在list里的index
	 */
	@Override
	public void render(List<Token> tokenList, int index) {
		String value = tokenList.get(index).getValue().getValue();
		String newValue = "<h1>" + value + "</h1>";
		tokenList.get(index).setValue(new Value(newValue));
	}
	
}
