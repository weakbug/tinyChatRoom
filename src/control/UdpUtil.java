package control;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import model.*;
import model.ThreadCallBack;

public class UdpUtil {
	
	public static final int EVERYONE 		= 1;
	public static final int LOGIN 				= 2;
	public static final int MESSAGE 			= 3;
	/** statement of EVERYONE */
	public static final int ONLINE_ASK 	= 11; //Please reply to me who is online
	public static final int ONLINE 			= 12; //Someone appeared
	public static final int OFFLINE 			= 13; //Someone disappeared
	public static final int ONLINE_FB 		= 14; //I am online
	/** statement of LOGIN */
	public static final int REQUEST 			= 21; //Request to login
	public static final int FEEDBACK 		= 22; //Feedback results
	/** statement of MESSAGE */
	public static final int PUBLIC 				= 31; //Everyone can see this information
	public static final int PRIVATE 			= 32; //Only one person can see this information
	
	private static final String multicastHost="224.0.0.1";
	private static MulticastSocket ds;
	private InetAddress address;
	private ThreadCallBack tCallBack;
	
	private UdpUtil() {
		try {
			address = InetAddress.getByName(multicastHost);  
			ds = new MulticastSocket(8004);
			ds.setTimeToLive(4);
			ds.joinGroup(address);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 实例初始化时传入接口实例
	 * @param tcb ThreadCallBack接口的实例
	 */
	public UdpUtil(ThreadCallBack tcb) {
		this();
		tCallBack = tcb;
		startUdpReceive();
	}
	
	public void breakThread() {
		if (!ds.isClosed()) {
			ds.close();
		}
	}
	
	/**
	 * 开始阻塞监听udp广播
	 */
	private void startUdpReceive() {
		try {  
            new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					byte buf[] = new byte[1024];  
			        DatagramPacket dp = new DatagramPacket(buf, 1024);  
			        while (true) {  
			            try {  
			            	ds.receive(dp); //阻塞型监听
			            	String msg = new String(buf, 0, dp.getLength());
			            	tCallBack.receiveMessage(msg); //回调接收到的消息
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
	/**
	 * 发送Udp广播
	 * @param msg 需要发送的字符串信息
	 */
	public void sendUdpPacket(String msg) {
		byte[] data = msg.getBytes();
		final DatagramPacket dataPacket = new DatagramPacket(data, data.length, address, 8004);
		new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ds.send(dataPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
	}

}
