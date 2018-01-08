package edu.team9.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder; 
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.team9.Lexer;
import edu.team9.Parser;
import edu.team9.element.Token;
import edu.team9.io.SaveAsHtml;

import javax.swing.JList;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List; 

/**
 * �������
 * @author ��־��
 * @date 2018��1��7��
 */
public class UI {

	private JFrame frame;
	private JTextPane textPane;
	private JEditorPane editorPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Markdown Editor");
		frame.setBounds(100, 100, 1000, 708);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout());
		
		//������
		JScrollPane textScroll = new JScrollPane();
		textScroll.setPreferredSize(new Dimension(480, 618));
		frame.getContentPane().add(textScroll);
		JScrollPane editorScroll = new JScrollPane();
		editorScroll.setPreferredSize(new Dimension(480, 618));
		frame.getContentPane().add(editorScroll);
		editorScroll.setVerticalScrollBar(textScroll.getVerticalScrollBar());
		
		//�����
		textPane = new JTextPane();
		textPane.setPreferredSize(new Dimension(475, 610));
		textScroll.setViewportView(textPane);
		//ע���¼�
		textPane.getDocument().addDocumentListener(new Md2Html());

		
		//�����
		editorPane = new JEditorPane();
		editorPane.setBackground(SystemColor.control);
		//����JEditorPane��ʾ��ʽΪhtml  
        editorPane.setContentType("text/html"); 
		editorPane.setEditable(false);
		editorPane.setPreferredSize(new Dimension(475, 610));
		editorScroll.setViewportView(editorPane);
		editorPane.addHyperlinkListener(new LinkListener());
		
		//�˵���
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("�ļ�");
		menuBar.add(fileMenu);
		
		JMenuItem openFileItme = new JMenuItem("��");
		fileMenu.add(openFileItme);
		openFileItme.addActionListener(new openListener());
		
		JMenuItem saveFileItem = new JMenuItem("����");
		fileMenu.add(saveFileItem);
		saveFileItem.addActionListener(new SaveListener());
		
		JMenu helpMenu = new JMenu("����");
		menuBar.add(helpMenu);

		//��ӱ߿�
		LineBorder border = new LineBorder(Color.LIGHT_GRAY);
		textScroll.setBorder(border);
		editorScroll.setBorder(border);
		menuBar.setBorder(border);
	}
	
	/**
	 * 
	 * ����̨������HTML��䲹����������ʾ��JEditorPane��
	 * @author ����
	 * @param text
	 * @param jfc
	 * @return String
	 */
	private String getHtmlText(String text,String filename){
		StringBuffer html = new StringBuffer(); 
		html.append("<!DOCTYPE html>");
		html.append("<html>");
		html.append("<head>");
		html.append("<title>"+ filename + "</title>");
		html.append("<link type='text/css' rel='stylesheet' href='./source/main.css'/>");
		html.append("<style type='text/css'>");
//		html.append("table{border: solid 1px #000000;}");
//		html.append("td{border: solid 1px #000000;}");
		html.append("blockquote{");
		html.append("padding:10px 20px;");
		html.append("margin:0 0 20px;");
		html.append("font-size:17.5px;");
		html.append("border-left:5px solid #eee;}");
		html.append("pre{");
		html.append("display:block;");
		html.append("padding:9.5px;");
		html.append("margin:0 0 10px;");
		html.append("font-size:13px;");
		html.append("line-height:1.5;");
		html.append("color:#333;");
		html.append("word-break:break-all;");
		html.append("word-wrap:bread-word;");
		html.append("background-color:#f5f5f5;");
		html.append("border:1px solid #ccc;");
		html.append("border-radius:4px;");
		html.append("font-family:monospace;");
		html.append("white-space:pre;");
		html.append("margin:1em 0px;}");
		html.append("code{");
		html.append("font-family:monospace;");
		html.append("padding:2px 4px;");
		html.append("fond-size:90%;");
		html.append("background-color:#f9f2f4;");
		html.append("border-radius:4px;}");
		html.append("table{");
		html.append("display:table;");
		html.append("width:100%;");
		html.append("max-width:100%;");
		html.append("margin-bottom:20px;");
		html.append("background-color:transparent;");
		html.append("border: solid 1px grey;");
		html.append("border-spacing:2px;");
		html.append("border-collapse:separete;");
		html.append("box-sizing:border-box;}");
		html.append("tr{");
		html.append("display:table-row;");
		html.append("vertical-align:inherit;");
		html.append("border-color:inherit;}");
		html.append("th{");
		html.append("padding:8px;");
		html.append("line-height:1.5;");
		html.append("text-align:left;}");
		html.append("</style>");
		html.append("</head>");
		html.append("<body>");
		BufferedReader br = new BufferedReader(new StringReader(text));
		String line;
		try {
			while ((line = br.readLine()) != null) { 
				html.append(line);
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		html.append("</body>");
		html.append("</html>");
		
		return html.toString();
	}
	
	/**
	 * ���ļ��������ڲ��������
	 * @author ����
	 * �����е�md�ļ���ʾ��JTextPane��
	 * ͬʱ������õ�HTML��ʾ��JEditorPane��
	 *
	 */
	class openListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc=new JFileChooser();  
	        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showDialog(new JLabel(), "ѡ��");  
	        File file=jfc.getSelectedFile();  
	        StringBuffer sb = new StringBuffer();
	        if(file != null){
	        	if(file.isDirectory()){  
		            System.out.println("�ļ���:"+file.getAbsolutePath());  
		        }else if(file.isFile()){  
		            System.out.println("�ļ�:"+file.getAbsolutePath());  
		        }  
		        System.out.println(jfc.getSelectedFile().getName());  
		        try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String lineStr = null;
					while ((lineStr = reader.readLine())!=null) {
						sb.append(lineStr).append("\n");
					}
					textPane.setText(sb.toString());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
//		        Lexer lexer = new Lexer(sb.toString());
//		        List<Token> tokenList = lexer.lex();
//				Parser parser = new Parser(tokenList);
//				String result = parser.parse();
//				
//				editorPane.setText(getHtmlText(result, jfc.getSelectedFile().getName()));
	        }	
		}
		
	}
	
	/**
	 * @autor ��˶
	 * @Date 2018-01-07
	 */
	class SaveListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			String content = editorPane.getText();
			//���.html�ļ����ͺ�׺ѡ���
			FileNameExtensionFilter htmlFilter = new FileNameExtensionFilter(  
		            "html�ļ�(*.html)", "html");  
		    chooser.setFileFilter(htmlFilter);
		    chooser.setFileFilter(htmlFilter);
		    
			int select = chooser.showSaveDialog(new Component() {
			});
			File file = null;//�û�ѡȡ������ļ�
			if(select == chooser.APPROVE_OPTION) {
				System.out.println("coming");
				file = chooser.getSelectedFile();
				String fname = chooser.getName(file);
				if(fname.indexOf(".html") == -1) {
					file = new File(chooser.getCurrentDirectory(),fname+".html");
				}
				if(file.exists()) {
					int i = JOptionPane.showConfirmDialog(null, "���ļ��Ѿ����ڣ�ȷ��Ҫ������");
					if(i == JOptionPane.YES_OPTION) {
						SaveAsHtml save = new SaveAsHtml();
						try {
							save.saveAsHtml(content, file);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}else {
						
					}
				}else{
					SaveAsHtml save = new SaveAsHtml();
					try {
						save.saveAsHtml(content, file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				System.out.println(file.getName());
			}
		}

		
	}
	
	/**
	 * ���û�����������Ⱦ��ʾ
	 * @author ������
	 *
	 */
	class Md2Html implements DocumentListener{

		@Override
		public synchronized void insertUpdate(DocumentEvent e) {
			show();
		}

		@Override
		public synchronized void removeUpdate(DocumentEvent e) {
			show();
		}

		@Override
		public synchronized void changedUpdate(DocumentEvent e) {
			show();
		}
		
		void show() {
			String innerValue = null;				
			innerValue = textPane.getText();
			Lexer lexer = new Lexer(innerValue);
			List<Token> tokenList = lexer.lex();
			Parser parser = new Parser(tokenList);
			String html =  parser.parse();
			lexer.clear();
			parser.clear();
			editorPane.setText(getHtmlText(html, "Md2Html"));
		}
		
	}

	/**
	 * ��Ӧ���ӵļ�����
	 * @author ����
	 *
	 */
	class LinkListener implements HyperlinkListener{

		@Override
		public void hyperlinkUpdate(HyperlinkEvent e) {
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			 try {
		            String command = "explorer.exe "
		                + e.getURL().toString();
		            Runtime.getRuntime().exec(command);
		          }
		          catch (Exception ex) {
		            ex.printStackTrace();
		          }
			}
			
		}
		
	}
}
