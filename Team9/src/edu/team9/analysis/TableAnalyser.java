package edu.team9.analysis;

import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Const;
import edu.team9.element.Token;
import edu.team9.element.Type;
import edu.team9.element.Value;
/**
 * 
 * @author ������
 * 2018��1��6��16:56:24
 * ��������table����
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
				//��鿪ͷ
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
				//����м��� |--|--|����
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
					//��table��Ϣװ��List��,������table����
					int tempListIndex;
					for( tempListIndex = index; tempListIndex < size; tempListIndex++){
						//�����ڶ��е�����
						if(tempListIndex == jumpNum){
							continue;
						}
						
						String eachLine = list.get(tempListIndex).getValue().getValue();
						String[] partInfo = eachLine.split("\\|");
						//System.out.println("partInfo size " + partInfo.length);
						if(partInfo.length > 1){//�ж��Ƿ�������
							List<String> infoList = new LinkedList();
							for(int partInfoIndex = 0; partInfoIndex < partInfo.length; partInfoIndex++){
								if(partInfo[partInfoIndex].trim().equals("")){
									continue;
								}
								infoList.add(partInfo[partInfoIndex].trim());
							}
							tableList.add(infoList);
							if(tempListIndex == size - 1){
								//�ж��Ƿ������һ��
								Value tempValue = new Value();
								tempValue.setValueList(tableList);
								tempList.add(new Token(Type.TABLE, tempValue));
								//System.out.println("add part 1");
								index = tempListIndex;
								break;
							}
						}else {
							//����table:�����е����ݷ���lis,����index,����ѭ��
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
					//����table,����ǰtoken����
					tempList.add(currentToken);
					//System.out.println("add part 3");
				}
			}else{
				//�Ѿ�������������ֱ�ӷ��뵱ǰlist
				tempList.add(currentToken);
				//System.out.println("add part 4");
			}
			
		}
		return tempList;
	}

}
