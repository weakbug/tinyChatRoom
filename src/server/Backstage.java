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
	 * ���������еĲ��� 'account' 'password'����ѯ���ݿ�͵�¼���ж��Ƿ������¼��
	 * ��������Ŀ����ӵ����ݿ�͵�¼�������¼�������½��˺Ų�����
	 * @param account �����¼���˺�����
	 * @param password �����¼�����루md5��
	 * @return �Ƿ������¼��true - ���� / false - ������
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
