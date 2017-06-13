package client;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainWindow {

	private JFrame frmTinychatroom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

}
