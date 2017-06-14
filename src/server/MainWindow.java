package server;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

/**
 * @author Shinrai
 * 服务端显示窗口。
 */
public class MainWindow {

	private JFrame frmTinychatroomServer;
	private JTable member_detail_table;
	private JScrollPane scrollPane_1;
	private JTextArea server_outputArea;
	private JTextField system_broadcast_inputArea;
	private JButton system_broadcast_button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		Vector<String> colHeader = new Vector<String>(); 
        colHeader.add("account"); 
        colHeader.add("password"); 
        colHeader.add("nickname"); 
        colHeader.add("ip"); 
        colHeader.add("publicKey"); //预留，用于加密私聊
        
        Vector<Vector<String>> dataVec = new Vector<Vector<String>>(); 
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 220, SpringLayout.NORTH, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, frmTinychatroomServer.getContentPane());
		frmTinychatroomServer.getContentPane().add(scrollPane);
		
		member_detail_table = new JTable(dataVec, colHeader);
		scrollPane.setViewportView(member_detail_table);
		
		scrollPane_1 = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_1, 6, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_1, 10, SpringLayout.WEST, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_1, -41, SpringLayout.SOUTH, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_1, -10, SpringLayout.EAST, frmTinychatroomServer.getContentPane());
		frmTinychatroomServer.getContentPane().add(scrollPane_1);
		
		server_outputArea = new JTextArea();
		server_outputArea.setLineWrap(true);
		scrollPane_1.setViewportView(server_outputArea);
		
		system_broadcast_inputArea = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, system_broadcast_inputArea, 10, SpringLayout.SOUTH, scrollPane_1);
		springLayout.putConstraint(SpringLayout.WEST, system_broadcast_inputArea, 10, SpringLayout.WEST, frmTinychatroomServer.getContentPane());
		frmTinychatroomServer.getContentPane().add(system_broadcast_inputArea);
		system_broadcast_inputArea.setColumns(10);
		
		system_broadcast_button = new JButton("Send");
		springLayout.putConstraint(SpringLayout.SOUTH, system_broadcast_button, -10, SpringLayout.SOUTH, frmTinychatroomServer.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, system_broadcast_inputArea, -6, SpringLayout.WEST, system_broadcast_button);
		springLayout.putConstraint(SpringLayout.EAST, system_broadcast_button, 0, SpringLayout.EAST, scrollPane);
		frmTinychatroomServer.getContentPane().add(system_broadcast_button);
	}
}
