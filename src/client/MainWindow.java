package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;

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
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 149, SpringLayout.WEST, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -96, SpringLayout.SOUTH, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, frmTinychatroom.getContentPane());
		frmTinychatroom.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_1, 10, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_1, -10, SpringLayout.SOUTH, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_1, -109, SpringLayout.EAST, frmTinychatroom.getContentPane());
		frmTinychatroom.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_1, 6, SpringLayout.EAST, scrollPane_2);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_2, 10, SpringLayout.NORTH, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_2, -10, SpringLayout.SOUTH, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_2, 10, SpringLayout.WEST, frmTinychatroom.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_2, -6, SpringLayout.WEST, scrollPane);
		frmTinychatroom.getContentPane().add(scrollPane_2);
		
		JList member_list = new JList();
		scrollPane_2.setViewportView(member_list);
		
		JButton btnNewButton = new JButton("New button");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 0, SpringLayout.NORTH, scrollPane_1);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 6, SpringLayout.EAST, scrollPane_1);
		frmTinychatroom.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 0, SpringLayout.SOUTH, scrollPane_1);
		
		JTextArea client_inputArea = new JTextArea();
		scrollPane_1.setViewportView(client_inputArea);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, 0, SpringLayout.EAST, scrollPane);
		frmTinychatroom.getContentPane().add(btnNewButton_1);
	}
}
