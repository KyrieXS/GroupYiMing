package edu.team9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.LinkRef;

import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.rendering.CodeRendering;
import edu.team9.rendering.CommonRendering;
import edu.team9.rendering.H1Rendering;
import edu.team9.rendering.H2Rendering;
import edu.team9.rendering.HrRendering;
import edu.team9.rendering.ImgRendering;
import edu.team9.rendering.LinkRendering;
import edu.team9.rendering.ListOlRendering;
import edu.team9.rendering.ListUlRendering;
import edu.team9.rendering.ParagraphRendering;
import edu.team9.rendering.QuoteRendering;
import edu.team9.rendering.Rendering;
import edu.team9.rendering.TableRendering;
/**
 *�﷨������ 
 * @author ȫ��
 *
 */
public class Parser {
	List<Token> tokenList = null;
	List<Rendering> renderingList = null;
	Map<Type,Rendering> parserMap = new HashMap();;
	public Parser(List<Token> tokenList){
		this.tokenList = tokenList;
	}
	
	
	/**
	 * ��ʼ��rendering��list
	 */
	private void init(){
		parserMap.put(Type.QUOTE,new QuoteRendering());
		parserMap.put(Type.TABLE, new TableRendering());
		parserMap.put(Type.CODE, new CodeRendering());
		parserMap.put(Type.LIST_UL, new ListUlRendering());
		parserMap.put(Type.LIST_OL, new ListOlRendering());
		parserMap.put(Type.TITLE_ONE, new H1Rendering());
		parserMap.put(Type.TITLE_TWO, new H2Rendering());
		parserMap.put(Type.LINK, new LinkRendering());
		parserMap.put(Type.IMG, new ImgRendering());
		parserMap.put(Type.HR, new HrRendering());
		parserMap.put(Type.PARAGRAPH, new ParagraphRendering());
		parserMap.put(Type.COMMON, new CommonRendering());
	}
	
	
	/**
	 * �﷨������
	 * @return ����html��String
	 */
	public String parse(){
		init();
		StringBuffer result = new StringBuffer();
		
		for(int i = 0, size = tokenList.size(); i < size; i++){
			Rendering render = parserMap.get(tokenList.get(i).getType());
			render.render(tokenList, i);
			result.append(tokenList.get(i).getValue().getValue()).append("\n");
		}
		
		return result.toString();
	}

}
