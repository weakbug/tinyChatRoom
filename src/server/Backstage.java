package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

import control.*;
import model.*;

public class Backstage implements ThreadCallBack {
	
	private List<Member> loginList;
	private UdpUtil udpUtil;
	
	public Backstage() {
		initialize();
	}
	
	private void initialize() {
		loginList = new ArrayList<Member>();
		// ����UDP�����̡߳�
		udpUtil = new UdpUtil(this);
	}
	
	/**
	 * ���������еĲ��� 'nickname' 'password'����ѯ���ݿ�͵�¼���ж��Ƿ������¼��
	 * �����Ϊ��
	 * 1. �˺����������ݿ��¼��ͬ����¼���в����ڸ��˺ţ����˺������¼������ true��
	 * 2. �˺����������ݿ��¼��ͬ����¼���д��ڸ��˺ţ��㲥ѯ�ʵ�¼������ӹ㲥������� true �� false�����е��Ѷȣ�
	 * 3. �˺Ŷ�Ӧ���벻��ͬ������ false��
	 * 4. �����ڴ��˺���Ϣ����ӵ����ݿ�͵�¼������ true�������½��˺Ų�����
	 * @param nickname �����¼���ǳơ�
	 * @param password �����¼�����루md5��
	 * @return �Ƿ������¼��true - ���� / false - ������
	 */
	private boolean isLogin(String nickname, String password) {
		
		return false;
	}
	
	/**
	 * Udp�߳��л�ȡ����Ϣʱִ�д˷����ص����˷���ִ����Ϣ��������ɹ�����
	 * @param msg �ص����ַ�����Ϣ��
	 */
	public void receiveMessage(String msg) {
		
	}
}


