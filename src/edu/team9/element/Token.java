package edu.team9.element;

/**
 * 
 * @author ������
 * ��װ�����ͺ�����,�Ǵʷ�����֮��Ļ�����λ
 *
 */
public class Token {
	Type type = Type.COMMON;
	Value value;	
	
	public Token() {
		super();
	}
	public Token(Type type, Value value) {
		super();
		this.type = type;
		this.value = value;
	}
	public Token(Value value){
		this.value = value;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public boolean isCommon(){
		if(this.type == Type.COMMON){
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Token [type=" + type + ", value=" + value + "]";
	}
}
