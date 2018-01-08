package edu.team9.element;

/**
 * 
 * @author 虎舒翔
 * 用来表示Token的类型枚举,每位成员完成的分析类型在这里添加
 *
 */
public enum  Type {
		COMMON, //默认的tokentype,如果token不是commom,不用进行filt分析
		PARAGRAPH, //段落
		NEWLINE, //换行
		TITLE_ONE, //一级标题
		TITLE_TWO,  //二级标题
		LIST_OL,  //有序列表
		LIST_UL,  //无序列表
		CODE,  //代码行
		REF, //引用
		IMG, //图片
		LINK, //连接
		EM,  //斜体强调
		STRONG,//粗体强调
		EMSTRONG,//斜体粗体强调
		QUOTE,  //代码块
		TABLE,  //表格
		HR  //分割线
		

}
