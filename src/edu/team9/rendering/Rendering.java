package edu.team9.rendering;

import java.util.List;

import edu.team9.element.Token;

/**
 * 
 * 所有语法分析器的父类
 *
 */
public abstract class Rendering {
	public abstract void render(List<Token> tokenList, int index);
}
