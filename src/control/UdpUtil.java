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
	private static InetAddress address;
	private ThreadCallBack tCallBack;
	
	private UdpUtil() {
		try {
			address = InetAddress.getByName(multicastHost);  
			ds = new MulticastSocket(8004);
			ds.setTimeToLive(4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ʵ����ʼ��ʱ����ӿ�ʵ��
	 * @param tcb ThreadCallBack�ӿڵ�ʵ��
	 */
	public UdpUtil(ThreadCallBack tcb) {
		super();
		tCallBack = tcb;
	}
	
	public void breakThread() {
		if (!ds.isClosed()) {
			ds.close();
		}
	}
	
	/**
	 * ��ʼ����udp�㲥
	 */
	public void startUdpReceive() {
		try {  
            ds = new MulticastSocket(8004);  
            ds.joinGroup(address);  
            new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					byte buf[] = new byte[1024];  
			        DatagramPacket dp = new DatagramPacket(buf, 1024);  
			        while (true) {  
			            try {  
			            	ds.receive(dp); //�����ͽ���
			            	String msg = new String(buf, 0, dp.getLength());
			            	tCallBack.receiveMessage(msg); //�ص����յ�����Ϣ
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
