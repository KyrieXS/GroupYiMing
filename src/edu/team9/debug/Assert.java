package edu.team9.debug;

import java.util.List;
import java.util.Map;

public class Assert {
	//检查传入引用不为null
	public static void notNull(Object obj, String msg){
		assert obj != null : msg;
	}
	//检查list不为空
	public static void notEmpty(Object obj, String msg){
		if(obj instanceof List){
			List l = (List) obj;
			assert !l.isEmpty() : msg;
		}
		if(obj instanceof Map){
			Map m = (Map)obj;
			assert !m.isEmpty() :msg;
		}
	}
}
