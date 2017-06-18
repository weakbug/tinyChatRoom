package server;

import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import control.Backstage;
import model.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

/**
 * @author Shinrai
 * 服务端显示窗口。
 */
public class MainWindow implements WindowCallBack {

	private JFrame 		frmTinychatroomServer;
	private JTable 			member_detail_table;
	private JScrollPane 	outputArea_scrollPane;
	private JTextArea 	server_outputArea;
	private JTextField 	system_broadcast_inputArea;
	private JButton 		system_broadcast_button;
	private Backstage 	backstage;

	/**
	 * Launch the application.
	 */
	public static void _main() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		
		//实例化后台类并自动初始化Udp
		backstage = new Backstage(Backstage.SERVER_MODE, this);
		
		Vector<String> colHeader = new Vector<String>(); 
        colHeader.add("nickname"); 
        colHeader.add("password"); 
        colHeader.add("ip"); 
        colHeader.add("publicKey"); //预留，用于加密私聊
        colHeader.add("online?");
        
        Vector<Vector<String>> dataVec = new Vector<Vector<String>>(); 
		
		JScrollPane member_scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, member_scrollPane, 10, SpringLayout.NORTH, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, member_scrollPane, 10, SpringLayout.WEST, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, member_scrollPane, 220, SpringLayout.NORTH, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, member_scrollPane, -10, SpringLayout.EAST, frmTinychatroomServer.getContentPane());
		frmTinychatroomServer.getContentPane().add(member_scrollPane);
		
		member_detail_table = new JTable(dataVec, colHeader);
		member_scrollPane.setViewportView(member_detail_table);
		
		outputArea_scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, outputArea_scrollPane, 6, SpringLayout.SOUTH, member_scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, outputArea_scrollPane, 10, SpringLayout.WEST, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, outputArea_scrollPane, -41, SpringLayout.SOUTH, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, outputArea_scrollPane, -10, SpringLayout.EAST, frmTinychatroomServer.getContentPane());
		frmTinychatroomServer.getContentPane().add(outputArea_scrollPane);
		
		server_outputArea = new JTextArea();
		server_outputArea.setLineWrap(true);
		outputArea_scrollPane.setViewportView(server_outputArea);
		
		system_broadcast_inputArea = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, system_broadcast_inputArea, 10, SpringLayout.SOUTH, outputArea_scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, system_broadcast_inputArea, 10, SpringLayout.WEST, frmTinychatroomServer.getContentPane());
		frmTinychatroomServer.getContentPane().add(system_broadcast_inputArea);
		system_broadcast_inputArea.setColumns(10);
		
		system_broadcast_button = new JButton("Send");
		springLayout.putConstraint(SpringLayout.SOUTH, system_broadcast_button, -10, SpringLayout.SOUTH, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, system_broadcast_inputArea, -6, SpringLayout.WEST, system_broadcast_button);
		springLayout.putConstraint(SpringLayout.EAST, system_broadcast_button, 0, SpringLayout.EAST, member_scrollPane);
		frmTinychatroomServer.getContentPane().add(system_broadcast_button);
		
	}

	@Override
	public void nextStep() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void textAreaAppend(String s) {
		// TODO Auto-generated method stub
		server_outputArea.append(s + "\n");
	}
}
