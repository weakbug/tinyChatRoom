package control;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import control.*;
import model.*;

public class Backstage implements ThreadCallBack {
	
	private List<Member> loginList;
	private UdpUtil udpUtil;
	private int running_mode;
	public static final int CLIENT_MODE = 1;
	public static final int SERVER_MODE = 2;
	
	/**
	 * ʵ������̨�ಢ���ú�̨ģʽ��
	 * @param mode ִ�к�̨��ģʽ��
	 */
	public Backstage(int mode) {
		running_mode = mode;
		initialize();
	}
	
	private void initialize() {
		loginList = new ArrayList<Member>();
		// ����UDP�����̡߳�
		udpUtil = new UdpUtil(this);
	}
	
	/**
	 * server mode
	 * ���������еĲ��� 'nickname' 'password'����ѯ���ݱ��ж��Ƿ������¼��
	 * �����Ϊ��
	 * 1. �˺����������ݱ��¼��ͬ��'isLogin' ���δ��¼�����ı�ǲ����� ALL_RIGHT��
	 * 2. �˺����������ݱ��¼��ͬ��'isLogin' ����ѵ�¼������ MAYBE_REPEAT ���㲥ѯ�ʵ�¼������ӹ㲥�����Ӧ�㲥�����е��Ѷȣ�
	 * 3. �˺Ŷ�Ӧ���벻��ͬ������ WRONG_PASSWORD��
	 * 4. �����ڴ��˺���Ϣ����ӵ����ݱ��� 'isLogin' ��ǲ����� NEW_ACCOUNT�������½��˺Ų�����
	 * 5. �ǳ��д��ڷǷ��ַ�����ֹ��¼������ ILLEGAL��
	 * @param nickname �����¼���ǳơ�
	 * @param password �����¼�����루md5��
	 * @return 
	 */
	private int isLogin(String nickname, String password) {
		if(nickname.contains("-")) {
			return MessageHead.ILLEGAL;
		}
		Member __new = new Member(nickname);
		int index_of_member = loginList.indexOf(__new);
		if(index_of_member == -1) { //�������˺ţ����½��˺Ų�����
			return MessageHead.NEW_ACCOUNT;
		}
		else {
			Member origin_member = loginList.get(index_of_member);
			if( !(origin_member.getPassword().equals(password)) ) { //�������
				return MessageHead.WRONG_PASSWORD;
			}
			else if(origin_member.isLogin()) { //������ڵ�¼
				return MessageHead.MAYBE_REPEAT;
			}
			else { //������¼
				return MessageHead.ALL_RIGHT;
			}
		}
	}
	
	/**
	 * Udp�߳��л�ȡ����Ϣʱִ�д˷����ص����˷���ִ����Ϣ��������ɹ�����
	 * @param msg �ص����ַ�����Ϣ��
	 */
	public void receiveMessage(String msg) {
		Matcher matcher = MessageHead.first_parse.matcher(msg);
		if(matcher.find()) {
			switch( Integer.parseInt(matcher.group(1)) ) {
			case MessageHead.BROADCAST_ONLINE_ASK :
				//reply
				break;
			case MessageHead.BROADCAST_ONLINE :
				Member newMember = (Member) RegexUtil.pattern_match(msg, MessageHead.broadcast_online, MessageHead.BROADCAST_ONLINE, null, null, null);
			case MessageHead.BROADCAST_OFFLINE :
				Member deleteMember = new Member(matcher.group(2));
			case MessageHead.BROADCAST :
				
			}
		}
	}
}


