package edu.team9.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.JOptionPane;

import edu.team9.Lexer;
import edu.team9.Parser;
import edu.team9.element.Token;

/**
 * 将输出结果保存为html类型文件
 * @author 许硕
 * @date 2018-01-07
 */
public class SaveAsHtml {
	public void saveAsHtml(String content,File file) throws FileNotFoundException, UnsupportedEncodingException {
		//File dest = new File(dst);//指定路径打开或者创建一个文件
		FileOutputStream fileOutput = new FileOutputStream(file);//字节输出流
		
		//将字节输出流改成字符输出流
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutput, "utf-8");
		try {
			outputStreamWriter.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outputStreamWriter.close();
			fileOutput.close();
			JOptionPane.showMessageDialog(null, "文件保存成功！", "提示框", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
