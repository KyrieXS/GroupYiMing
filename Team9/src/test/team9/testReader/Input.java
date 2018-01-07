package test.team9.testReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import edu.team9.element.Token;
import edu.team9.element.Value;

/**
 * 
 * @author »¢ÊæÏè
 * 2018Äê1ÔÂ6ÈÕ20:26:02
 *
 */

public class Input {
	
	public static List<Token> readMarkdownFile(String fileName) {
    	try (BufferedReader reader = new BufferedReader(new FileReader(fileName));){
			String lineStr = null;
			
			List<Token> lines = new LinkedList<Token>();
			while ((lineStr = reader.readLine())!=null) {
				lines.add(new Token(new Value(lineStr)));
			}
			return lines;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
}
