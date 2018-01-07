package edu.team9.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder; 
import javax.swing.border.LineBorder;
import javax.swing.JList;
import java.awt.SystemColor; 

/**
 * �������
 * @author ��־��
 * @date 2018��1��7��
 */
public class UI {

	private JFrame frame;

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
		
		JTextPane textPane = new JTextPane();
		frame.getContentPane().add(textPane);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBackground(SystemColor.control);
		editorPane.setEditable(false);
		frame.getContentPane().add(editorPane);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("�ļ�");
		menuBar.add(fileMenu);
		
		JMenuItem openFileItme = new JMenuItem("��");
		fileMenu.add(openFileItme);
		
		JMenuItem saveFileItem = new JMenuItem("����");
		fileMenu.add(saveFileItem);
		
		JMenu helpMenu = new JMenu("����");
		menuBar.add(helpMenu);
		
		textPane.setPreferredSize(new Dimension(480, 618));
		editorPane.setPreferredSize(new Dimension(480, 618));
		
		//��ӱ߿�
		LineBorder border = new LineBorder(Color.gray);
		textPane.setBorder(border);
		editorPane.setBorder(border);
		menuBar.setBorder(border);
	}

}
