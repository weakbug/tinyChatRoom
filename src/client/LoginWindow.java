package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import control.Backstage;
import model.WindowCallBack;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Shinrai
 * 客户端登录窗口
 */
public class LoginWindow implements WindowCallBack {

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton login_button;
	private JTextArea loginFeedbackTextArea;
	private Backstage backstage;

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
					LoginWindow window = new LoginWindow();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setAlwaysOnTop(true);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 320, 200);
		frmLogin.setResizable(false);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmLogin.getContentPane().setLayout(springLayout);
		
		//实例化后台类并自动初始化Udp
		backstage = new Backstage(Backstage.CLIENT_MODE, this);
		
		JLabel user_label = new JLabel("User name");
		springLayout.putConstraint(SpringLayout.NORTH, user_label, 10, SpringLayout.NORTH, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, user_label, 10, SpringLayout.WEST, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, user_label, -140, SpringLayout.SOUTH, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, user_label, -224, SpringLayout.EAST, frmLogin.getContentPane());
		frmLogin.getContentPane().add(user_label);
		
		JLabel pw_label = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.NORTH, pw_label, 23, SpringLayout.SOUTH, user_label);
		springLayout.putConstraint(SpringLayout.WEST, pw_label, 10, SpringLayout.WEST, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pw_label, -96, SpringLayout.SOUTH, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pw_label, -224, SpringLayout.EAST, frmLogin.getContentPane());
		frmLogin.getContentPane().add(pw_label);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 10, SpringLayout.NORTH, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, user_label);
		springLayout.putConstraint(SpringLayout.EAST, textField, -10, SpringLayout.EAST, frmLogin.getContentPane());
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -15, SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 0, SpringLayout.NORTH, pw_label);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, pw_label);
		springLayout.putConstraint(SpringLayout.SOUTH, passwordField, 29, SpringLayout.NORTH, pw_label);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, textField);
		frmLogin.getContentPane().add(passwordField);
		
		login_button = new JButton("Login");
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, login_button, -10, SpringLayout.SOUTH, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, login_button, -10, SpringLayout.EAST, frmLogin.getContentPane());
		frmLogin.getContentPane().add(login_button);
		
		loginFeedbackTextArea = new JTextArea();
		springLayout.putConstraint(SpringLayout.NORTH, loginFeedbackTextArea, 6, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, loginFeedbackTextArea, 10, SpringLayout.WEST, frmLogin.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, loginFeedbackTextArea, 0, SpringLayout.SOUTH, login_button);
		springLayout.putConstraint(SpringLayout.EAST, loginFeedbackTextArea, 218, SpringLayout.WEST, frmLogin.getContentPane());
		frmLogin.getContentPane().add(loginFeedbackTextArea);
	}

	@Override
	public void nextStep() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void textAreaAppend() {
		// TODO Auto-generated method stub
		
	}
}
