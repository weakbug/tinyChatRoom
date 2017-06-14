package server;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import model.*;

public class Backstage {
	
	private static MulticastSocket ds;  
    static String multicastHost="224.0.0.1";
    static InetAddress receiveAddress;  
	
	public Backstage() {
		initialize();
	}
	
	private void initialize() {
		
	}
	
	/**
	 * 给定请求中的参数 'account' 'password'，查询数据库和登录表，判断是否允许登录。
	 * 对于新条目，添加到数据库和登录表并允许登录。（即新建账号操作）
	 * @param account 请求登录的账号名。
	 * @param password 请求登录的密码（md5）
	 * @return 是否允许登录：true - 允许 / false - 不允许
	 */
	private boolean isLogin(String account, String password) {
		
		return false;
	}
	
	private void startUdpReceive() {
		try {  
            ds = new MulticastSocket(8004);  
            receiveAddress=InetAddress.getByName(multicastHost);  
            ds.joinGroup(receiveAddress);  
            new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					byte buf[] = new byte[1024];  
			        DatagramPacket dp = new DatagramPacket(buf, 1024);  
			        while (true) {  
			            try {  
			                ds.receive(dp);  
			                System.out.println(new String(buf, 0, dp.getLength())); 
//			                break;
			            } catch (Exception e) {  
			                e.printStackTrace();  
			            }  
			        }  
				}
			}).start();  
        } catch (Exception e1) {  
            // TODO Auto-generated catch block
            e1.printStackTrace();  
        }
	}
}
