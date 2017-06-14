package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import control.*;
import model.*;

public class Backstage implements ThreadCallBack{
	
	
	private UdpUtil udpUtil;
	
	public Backstage() {
		initialize();
	}
	
	private void initialize() {
		/*
		 * ����UDP�����̡߳�
		 */
		udpUtil = new UdpUtil(this);
		udpUtil.startUdpReceive();
	}
	
	/**
	 * ���������еĲ��� 'account' 'password'����ѯ���ݿ�͵�¼���ж��Ƿ������¼��
	 * �����Ϊ��
	 * 1. �˺����������ݿ��¼��ͬ����¼���в����ڸ��˺ţ����˺������¼������ true��
	 * 2. �˺����������ݿ��¼��ͬ����¼���д��ڸ��˺ţ��㲥ѯ�ʵ�¼������ӹ㲥������� true �� false�����е��Ѷȣ�
	 * 3. �˺Ŷ�Ӧ���벻��ͬ������ false��
	 * 4. �����ڴ��˺���Ϣ����ӵ����ݿ�͵�¼������ true�������½��˺Ų�����
	 * @param account �����¼���˺�����
	 * @param password �����¼�����루md5��
	 * @return �Ƿ������¼��true - ���� / false - ������
	 */
	private boolean isLogin(String account, String password) {
		
		return false;
	}
	
	/**
	 * Udp�߳��л�ȡ����Ϣʱִ�д˷����ص����˷���ִ����Ϣ��������ɹ�����
	 * @param msg �ص����ַ�����Ϣ��
	 */
	public void receiveMessage(String msg) {
		
	}
}


