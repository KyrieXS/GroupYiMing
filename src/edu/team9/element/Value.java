package edu.team9.element;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author 虎舒翔
 * 基本的数据类型,存储文本内容
 * 以后文本内容属性可能有添加
 *
 */

public class Value {
	String value;
	List valueList = null;
	int elseInfo;

	public Value(String value) {
		super();
		this.value = value;
	}
	public Value() {
		super();
	}

	public void addValueList(Object value){
		if(valueList == null){
			valueList = new LinkedList();
		}
		valueList.add(value);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List getValueList() {
		return valueList;
	}
	public void setValueList(List valueList) {
		this.valueList = valueList;
	}
	public int getElseInfo() {
		return elseInfo;
	}
	public void setElseInfo(int elseInfo) {
		this.elseInfo = elseInfo;
	}
	@Override
	public String toString() {
		return "Value [value=" + value + ", valueList=" + valueList + ", elseInfo=" + elseInfo + "]";
	}
	
}
