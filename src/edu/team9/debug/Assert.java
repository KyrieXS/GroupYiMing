package edu.team9.debug;

import java.util.List;
import java.util.Map;

public class Assert {
	//��鴫�����ò�Ϊnull
	public static void notNull(Object obj, String msg){
		assert obj != null : msg;
	}
	//���list��Ϊ��
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
