package edu.team9.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	/**
     * 通过文件名读取一个 markdown 文件
     * @author 刘洋
     * @param File文件
     * @return String
     */
    public static String readMarkdownFile(String fileName) {
    	try (BufferedReader reader = new BufferedReader(new FileReader(fileName));){
			String lineStr = null;
			
			StringBuffer sb = new StringBuffer();
			while ((lineStr = reader.readLine())!=null) {
				sb.append(lineStr).append("\n");
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
}
