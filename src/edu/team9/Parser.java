package edu.team9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.LinkRef;

import edu.team9.debug.Assert;
import edu.team9.debug.DebugConfig;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.rendering.CodeRendering;
import edu.team9.rendering.CommonRendering;
import edu.team9.rendering.EMRendering;
import edu.team9.rendering.EmStrongRendering;
import edu.team9.rendering.H1Rendering;
import edu.team9.rendering.H2Rendering;
import edu.team9.rendering.HrRendering;
import edu.team9.rendering.ImgRendering;
import edu.team9.rendering.LinkRendering;
import edu.team9.rendering.ListOlRendering;
import edu.team9.rendering.ListUlRendering;
import edu.team9.rendering.ParagraphRendering;
import edu.team9.rendering.QuoteRendering;
import edu.team9.rendering.RefRendering;
import edu.team9.rendering.Rendering;
import edu.team9.rendering.StrongRendering;
import edu.team9.rendering.TableRendering;
/**
 *语法分析器 
 * @author 全组
 *
 */
public class Parser {
	List<Token> tokenList = null;
	List<Rendering> renderingList = null;
	Map<Type,Rendering> parserMap = new HashMap();;
	public Parser(List<Token> tokenList){
		if(DebugConfig.CHECK_RENDERING_TABLE){
			Assert.notNull(tokenList, "传入的List<Token>为空");
		}
		this.tokenList = tokenList;
	}
	
	
	/**
	 * 初始化rendering的list
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
		parserMap.put(Type.REF, new RefRendering());
		parserMap.put(Type.PARAGRAPH, new ParagraphRendering());
		parserMap.put(Type.EM, new EMRendering());
		parserMap.put(Type.STRONG, new StrongRendering());
		parserMap.put(Type.EMSTRONG, new EmStrongRendering());
		parserMap.put(Type.COMMON, new CommonRendering());
		
	}
	
	
	/**
	 * 语法分析器
	 * @return 返回html的String
	 */
	public String parse(){
		init();
		StringBuffer result = new StringBuffer();
		if(DebugConfig.CHECK_PARSER){
			Assert.notNull(tokenList, "传入的List<Token>为空");
			Assert.notEmpty(parserMap, "传入的Map为空");
		}
		for(int i = 0, size = tokenList.size(); i < size; i++){
			Rendering render = parserMap.get(tokenList.get(i).getType());
			render.render(tokenList, i);
			
		}
		for(int i = 0, size = tokenList.size(); i < size; i++){
			result.append(tokenList.get(i).getValue().getValue()).append("\n");
		}
		if(DebugConfig.CHECK_PARSER){
			Assert.notNull(result.toString(), "传出的StringBuffer为空");
		}
		return result.toString();
	}

	public void clear(){
		this.tokenList = null;
		this.renderingList = null;
		this.parserMap = null;
	}
}
