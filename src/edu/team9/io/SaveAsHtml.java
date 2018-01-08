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
 * ������������Ϊhtml�����ļ�
 * @author ��˶
 * @date 2018-01-07
 */
public class SaveAsHtml {
	public void saveAsHtml(String content,File file) throws FileNotFoundException, UnsupportedEncodingException {
		//File dest = new File(dst);//ָ��·���򿪻��ߴ���һ���ļ�
		FileOutputStream fileOutput = new FileOutputStream(file);//�ֽ������
		
		//���ֽ�������ĳ��ַ������
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
			JOptionPane.showMessageDialog(null, "�ļ�����ɹ���", "��ʾ��", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
