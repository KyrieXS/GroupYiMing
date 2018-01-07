package edu.team9.rendering;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Value;

/**
 * 
 * @author 虎舒翔
 *用于将token的value转化成html的内容
 */

public class TableRendering extends Rendering {

	@Override
	public void render(List<Token> tokenList, int index) {
		Token tempToken = tokenList.get(index);
		List<List<List>> dimension2List = tempToken.getValue().getValueList();
		StringBuffer htmlString = new StringBuffer();
		htmlString.append("<table> ");
		for(int row = 0; row < dimension2List.size(); row++){
			if(row == 0){
				htmlString.append("<tr> ");
				for(int column = 0; column < dimension2List.get(row).size(); column++ ){
					htmlString.append("<th> ");
					htmlString.append(dimension2List.get(row).get(column) + " ");
					htmlString.append("</th> ");
				}
				htmlString.append("</tr> ");
				continue;
			}
			htmlString.append("<tr> ");
			for(int column = 0; column < dimension2List.get(row).size(); column++){
				htmlString.append("<td> ");
				htmlString.append(dimension2List.get(row).get(column) + " ");
				htmlString.append("</td> ");
			}
			htmlString.append("</tr>");
		}
		htmlString.append("</table> ");
		tokenList.get(index).setValue(new Value(htmlString.toString()));
	}
	
}
