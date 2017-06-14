package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 * @author Shinrai
 * 客户端聊天窗口
 */
public class MainWindow {

	private JFrame frmTinychatroom;

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
					window.frmTinychatroom.setVisible(true);
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
		frmTinychatroom = new JFrame();
		frmTinychatroom.setTitle("tinyChatRoom");
		frmTinychatroom.setBounds(100, 100, 700, 400);
		frmTinychatroom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmTinychatroom.getContentPane().setLayout(springLayout);
		
		JScrollPane chat_window_scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, chat_window_scrollPane, 10, SpringLayout.NORTH, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, chat_window_scrollPane, 149, SpringLayout.WEST, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, chat_window_scrollPane, -96, SpringLayout.SOUTH, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, chat_window_scrollPane, -10, SpringLayout.EAST, frmTinychatroom.getContentPane());
		frmTinychatroom.getContentPane().add(chat_window_scrollPane);
		
		JScrollPane inputArea_scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, inputArea_scrollPane, 10, SpringLayout.SOUTH, chat_window_scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, inputArea_scrollPane, -10, SpringLayout.SOUTH, frmTinychatroom.getContentPane());
		frmTinychatroom.getContentPane().add(inputArea_scrollPane);
		
		JScrollPane member_list_scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.WEST, inputArea_scrollPane, 6, SpringLayout.EAST, member_list_scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, member_list_scrollPane, 10, SpringLayout.NORTH, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, member_list_scrollPane, -10, SpringLayout.SOUTH, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, member_list_scrollPane, 10, SpringLayout.WEST, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, member_list_scrollPane, -6, SpringLayout.WEST, chat_window_scrollPane);
		frmTinychatroom.getContentPane().add(member_list_scrollPane);
		
		JList member_list = new JList();
		member_list_scrollPane.setViewportView(member_list);
		
		JButton settings_button = new JButton("Settings");
		springLayout.putConstraint(SpringLayout.NORTH, settings_button, 11, SpringLayout.SOUTH, chat_window_scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, inputArea_scrollPane, -6, SpringLayout.WEST, settings_button);
		springLayout.putConstraint(SpringLayout.EAST, settings_button, 0, SpringLayout.EAST, chat_window_scrollPane);
		frmTinychatroom.getContentPane().add(settings_button);
		
		JButton send_button = new JButton("Send");
		springLayout.putConstraint(SpringLayout.SOUTH, send_button, -10, SpringLayout.SOUTH, frmTinychatroom.getContentPane());
		
		JTextArea client_inputArea = new JTextArea();
		inputArea_scrollPane.setViewportView(client_inputArea);
		springLayout.putConstraint(SpringLayout.EAST, send_button, 0, SpringLayout.EAST, chat_window_scrollPane);
		frmTinychatroom.getContentPane().add(send_button);
	}
}
