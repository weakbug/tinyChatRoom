package control;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import model.*;
import model.ThreadCallBack;

public class UdpUtil {
	
	private static MulticastSocket ds;
	private static final String multicastHost="224.0.0.1";
	private static InetAddress receiveAddress;
	private ThreadCallBack tCallBack;
	
	public UdpUtil() {
		try {
			ds = new MulticastSocket(8004);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public UdpUtil(ThreadCallBack tcb) {
		super();
		tCallBack = tcb;
	}
	
	public void breakThread() {
		if (!ds.isClosed()) {
			ds.close();
		}
	}
	
	public void startUdpReceive() {
		try {  
            ds = new MulticastSocket(8004);  
            receiveAddress = InetAddress.getByName(multicastHost);  
            ds.joinGroup(receiveAddress);  
            new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					byte buf[] = new byte[1024];  
			        DatagramPacket dp = new DatagramPacket(buf, 1024);  
			        while (true) {  
			            try {  
			            	ds.receive(dp); //阻塞型接收
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
	
	public void sendUdpPacket(String msg) {
		
	}

}
