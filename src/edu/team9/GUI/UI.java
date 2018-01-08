package edu.team9.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		final int height = 1000;
		frame = new JFrame();
		frame.setTitle("Markdown Editor");
		frame.setBounds(100, 100, 1200, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		frame.setResizable(false);
		
		//���˵���
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(leftPanel);
		leftPanel.setPreferredSize(new Dimension(195, height));
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		
		//��Ӱ�ť
		JButton btnNewFile = new JButton("New file");
		btnNewFile.setBackground(SystemColor.activeCaptionBorder);
		btnNewFile.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		btnNewFile.setPreferredSize(new Dimension(195, 40));
		leftPanel.add(btnNewFile);
		btnNewFile.setBorder(null);
		btnNewFile.setFocusPainted(false);
		btnNewFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textPane.setText("");
				
			}
		});
		
		//��ֹ����
		JPanel nonePanel = new JPanel();
		nonePanel.setBackground(SystemColor.control);
		frame.getContentPane().add(nonePanel);
		nonePanel.setPreferredSize(new Dimension(15, height));

		//������
		JScrollPane textScroll = new JScrollPane();
		textScroll.setPreferredSize(new Dimension(492, height-120));
		frame.getContentPane().add(textScroll);
		JScrollPane editorScroll = new JScrollPane();
		editorScroll.setPreferredSize(new Dimension(492, height-120));
		frame.getContentPane().add(editorScroll);
		//textScroll.setVerticalScrollBar(editorScroll.getVerticalScrollBar());
		
		//�����
		textPane = new JTextPane();
		textPane.setBackground(SystemColor.control);
		textPane.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		//textPane.setPreferredSize(new Dimension(492, height));
		//textScroll.setViewportView(textPane);
		textScroll.setViewportView(textPane);
		//ע���¼�
		textPane.getDocument().addDocumentListener(new Md2Html());
		//������ק������
		DropListener dropListener = new DropListener();
		//��textPane��ע����ק������
		DropTarget target = new DropTarget(textPane, DnDConstants.ACTION_COPY_OR_MOVE, dropListener, true);

		
		//�����
		editorPane = new JEditorPane();
		editorPane.setBackground(SystemColor.controlHighlight);
		//����JEditorPane��ʾ��ʽΪhtml  
        editorPane.setContentType("text/html"); 
		editorPane.setEditable(false);
		//editorPane.setPreferredSize(new Dimension(492, height));
		editorScroll.setViewportView(editorPane);
		editorPane.addHyperlinkListener(new LinkListener());
		
		//�˵���
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//�ļ��˵�
		JMenu fileMenu = new JMenu("�ļ�(F)"); 
		fileMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(fileMenu);
		//���ÿ�ݼ�ΪF
		fileMenu.setMnemonic('F'); 
		
		//���ļ�
		JMenuItem openFileItme = new JMenuItem("��(O)");
		openFileItme.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		fileMenu.add(openFileItme);
		openFileItme.addActionListener(new openListener());
		//���ÿ�ݼ�ΪCtrl+O
		KeyStroke openKey = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK);
		openFileItme.setAccelerator(openKey);
		
		//�����ļ�
		JMenuItem saveFileItem = new JMenuItem("����(S)");
		saveFileItem.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		fileMenu.add(saveFileItem);
		saveFileItem.addActionListener(new SaveListener());
		//���ÿ�ݼ�ΪCtrl+S
		KeyStroke saveKey = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK);
		saveFileItem.setAccelerator(saveKey);
		
		//�����˵�
		JMenu helpMenu = new JMenu("����(H)");
		helpMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(helpMenu);
		//���ÿ�ݼ�ΪH
		helpMenu.setMnemonic('H');

		//��ӱ߿�
		LineBorder border = new LineBorder(Color.LIGHT_GRAY);
		textScroll.setBorder(null);
		editorScroll.setBorder(null);
		menuBar.setBorder(border);
		
		JLabel textLabel = new JLabel("����");
		textLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		textLabel.setOpaque(true);
		textLabel.setBackground(SystemColor.control);
		textScroll.setColumnHeaderView(textLabel);
		JLabel editorLabel = new JLabel("���");
		editorLabel.setBackground(SystemColor.controlHighlight);
		editorLabel.setOpaque(true);
		editorLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		editorScroll.setColumnHeaderView(editorLabel);
		
	}
	
	/**
	 * ʵ����ק�ļ��򿪵ļ������ڲ���
	 * @author ��־��
	 * @date 2018��1��8��
	 */
	class DropListener implements DropTargetListener {

		@Override
		public void dragEnter(DropTargetDragEvent arg0) {
			System.out.println("Ŀ���������");
		}

		@Override
		public void dragExit(DropTargetEvent arg0) {
			System.out.println("Ŀ���뿪����");
		}

		@Override
		public void dragOver(DropTargetDragEvent arg0) {
			System.out.println("Ŀ�����������ƶ�");
		}

		@Override
		public void drop(DropTargetDropEvent e) {
			System.out.println("Ŀ�����������ͷ�");
			if (e.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                // ����Ŀ������
                e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                //isAccept = true;

             // ��ȡFile����
				try {
					List<File> files = (List<File>) e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					File file = files.get(0);
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line = "";
					String lines = "";
					while ((line = br.readLine())!=null) {
						lines += (line + "\n");
					}
					textPane.setText(lines);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

            }
            //��ק���
            e.dropComplete(true);
        }

		@Override
		public void dropActionChanged(DropTargetDragEvent arg0) {
			System.out.println("��ǰdrop�������޸�");
		}
		
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
		html.append("border-color:grey;");
		html.append("border-spacing:2px;");
		html.append("border-collapse:separete;");
		html.append("box-sizing:border-box;");
		html.append("border-bottom-width: 1px;");
		html.append("border-bottom-style: solid;");
		html.append("border-bottom-color: rgb(230, 189, 189);}");
		html.append("tr{");
		html.append("display:table-row;");
		html.append("vertical-align:inherit;");
		html.append("border-color:inherit;");
		html.append("border-top-width: 1px;");
		html.append("border-top-style: solid;");
		html.append("border-top-color: rgb(230, 189, 189);}");
		html.append("th{");
		html.append("padding:8px;");
		html.append("font-size: 12px;");
		html.append("line-height:1.5;");
		html.append("text-align:left;");
		html.append("color: rgb(177, 106, 104);}");
		html.append("tr:nth-child(even) {");
		html.append("background: rgb(238, 211, 210)}");
		html.append("tr:nth-child(odd) {");
		html.append("background: #FFF}");
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
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
