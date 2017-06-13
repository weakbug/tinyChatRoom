package server;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class MainWindow {

	private JFrame frmTinychatroomServer;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmTinychatroomServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTinychatroomServer = new JFrame();
		frmTinychatroomServer.setTitle("tinyChatRoom Server");
		frmTinychatroomServer.setBounds(100, 100, 700, 400);
		frmTinychatroomServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmTinychatroomServer.getContentPane().setLayout(springLayout);
		
		Vector<String> colHeader = new Vector<String>(); 
        colHeader.add("account"); 
        colHeader.add("password"); 
        colHeader.add("nickname"); 
        colHeader.add("ip"); 
        colHeader.add("publicKey"); //‘§¡Ù£¨”√”⁄º”√‹ÀΩ¡ƒ
        
        Vector<Vector<String>> dataVec = new Vector<Vector<String>>(); 
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 184, SpringLayout.NORTH, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 684, SpringLayout.WEST, frmTinychatroomServer.getContentPane());
		frmTinychatroomServer.getContentPane().add(scrollPane);
		
		table = new JTable(dataVec, colHeader);
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_1, 6, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_1, 0, SpringLayout.WEST, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_1, 167, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_1, 0, SpringLayout.EAST, scrollPane);
		frmTinychatroomServer.getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane_1.setViewportView(textArea);
	}
}
