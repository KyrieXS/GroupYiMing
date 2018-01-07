package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Const;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;
/**
 * 
 * @author 虎舒翔
 * 2018年1月6日16:56:24
 * 用来分析table内容
 *
 */

public class TableAnalyser extends Analyser{

	@Override
	public List<Token> handle(List<Token> list) {
		List<Token> tempList = new LinkedList();
		StringBuffer innerValue = new StringBuffer("");
		for(int index = 0, size = list.size(); index < size; index++){
			Token currentToken = list.get(index);
			if(currentToken.isCommon()){
				String tokenValue = currentToken.getValue().getValue();
				boolean hasTable = false;
				//检查开头
				if(tokenValue.indexOf(Const.ANALYSE_TABLE_TELL) > -1){
					hasTable = true;
					if(tokenValue.startsWith(Const.ANALYSE_TABLE_TELL)){
						tokenValue.substring(1);
					}
					if(tokenValue.endsWith(Const.ANALYSE_TABLE_TELL)){
						tokenValue.substring(0, tokenValue.length() - 1);
					}
					String[] part = tokenValue.split(Const.ANALYSE_TABLE_TELL);
				}else{
					hasTable = false;
				}
				//System.out.println("hastable checkpoint1 " + hasTable);
				//检查中间行 |--|--|类似
				if(hasTable){
					if(index + 1 < size){
						String nextLine = list.get(index + 1).getValue().getValue();
						//System.out.println("newline " + nextLine);
						String[] part = nextLine.split("\\|");
						//System.out.println("part length" + part.length);
						for(int i = 0; i < part.length; i++){
							//System.out.println("part" + part[i]);
							part[i] = part[i].trim().replaceAll("-", "");
							//System.out.println(part[i]);
							if(part[i].length() > 0){
								hasTable = false;
								break;
							}
						}
					}else{
						hasTable = false;
					}
					
				}
				//System.out.println("hastable checkpoint2 " + hasTable);
				if(hasTable){
					List<List<String>> tableList = new LinkedList();
					int jumpNum = index + 1;
					//将table信息装入List中,遇到非table结束
					int tempListIndex;
					for( tempListIndex = index; tempListIndex < size; tempListIndex++){
						//跳过第二行的内容
						if(tempListIndex == jumpNum){
							continue;
						}
						
						String eachLine = list.get(tempListIndex).getValue().getValue();
						String[] partInfo = eachLine.split("\\|");
						//System.out.println("partInfo size " + partInfo.length);
						if(partInfo.length > 1){//判断是否是内容
							List<String> infoList = new LinkedList();
							for(int partInfoIndex = 0; partInfoIndex < partInfo.length; partInfoIndex++){
								if(partInfo[partInfoIndex].trim().equals("")){
									continue;
								}
								infoList.add(partInfo[partInfoIndex].trim());
							}
							tableList.add(infoList);
							if(tempListIndex == size - 1){
								//判断是否是最后一次
								Value tempValue = new Value();
								tempValue.setValueList(tableList);
								tempList.add(new Token(Type.TABLE, tempValue));
								//System.out.println("add part 1");
								index = tempListIndex;
								break;
							}
						}else {
							//不是table:将已有的内容放入lis,重置index,跳出循环
							Value tempValue = new Value();
							tempValue.setValueList(tableList);
							Token tempToken = new Token(Type.TABLE,tempValue);
							tempList.add(tempToken);
							//System.out.println("add part 2");
							index = tempListIndex - 1;
							break;
						}
					}
					
				}else{
					//不是table,将当前token放入
					tempList.add(currentToken);
					//System.out.println("add part 3");
				}
			}else{
				//已经分析过的内容直接放入当前list
				tempList.add(currentToken);
				//System.out.println("add part 4");
			}
			
		}
		return tempList;
	}

}
