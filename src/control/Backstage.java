package control;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import javax.swing.JTextArea;

import control.*;
import model.*;

public class Backstage implements ThreadCallBack {
	
	private List<Member> loginList;
	private UdpUtil udpUtil;
	private int running_mode;
	private Member thisMember;
	private RsaUtil thisRsa;
	private WindowCallBack windowCallBack;
	public static final int CLIENT_MODE = 1;
	public static final int SERVER_MODE = 2;
	
	/**
	 * ʵ������̨�ಢ���ú�̨ģʽ��
	 * @param mode ִ�к�̨��ģʽ��
	 */
	public Backstage(int mode, WindowCallBack wcb) {
		running_mode = mode;
		windowCallBack = wcb;
		initialize();
	}
	public void replaceCallBackInterface(WindowCallBack wcb) {
		windowCallBack = wcb;
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
	private int isLogin(Member _maybe_new) {
		if(_maybe_new.getNickname().contains(MessageHead.ban_meta)) {
			return MessageHead.ILLEGAL;
		}
		int index_of_member = loginList.indexOf(_maybe_new);
		if(index_of_member == -1) { //�������˺ţ����½��˺Ų�����
			loginList.add(_maybe_new);
			_maybe_new.setLogin(true);
			return MessageHead.NEW_ACCOUNT;
		}
		else {
			Member origin_member = loginList.get(index_of_member);
			if( !(origin_member.getPassword().equals(_maybe_new.getPassword())) ) { //�������
				return MessageHead.WRONG_PASSWORD;
			}
			else if(origin_member.isLogin()) { //������ڵ�¼
				return MessageHead.ONLINE;
			}
			else { //������¼
				origin_member.setLogin(true);
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
			String s = matcher.group(2);
			switch( Integer.parseInt(matcher.group(1)) ) {
			case MessageHead.BROADCAST_ONLINE_ASK : //��server����,client����
				if(running_mode == CLIENT_MODE) {
					if(s.equals(MessageHead.ban_meta) || s.equals(thisMember.getNickname())) {
						udpUtil.sendUdpPacket(StringMessage.messageBROADCAST_ONLINE(thisMember));
					}
				}
				break;
			case MessageHead.BROADCAST_ONLINE : //��server����,client����
				Member newMember = (Member) RegexUtil.pattern_match(s, MessageHead.parse_member, MessageHead.BROADCAST_ONLINE, null, null, null);
				int index = loginList.indexOf(newMember);
				String _0 = newMember.getNickname() + " �����������ҡ�"; 
				if(running_mode == CLIENT_MODE) {
					if(index == -1) {
						loginList.add(newMember);
						newMember.setLogin(true);
						//ǰ̨��ʾ�ַ�����
						printStringOnScreen(_0);
					}
				}
				if(running_mode == SERVER_MODE) {
					if(index != -1) {
						loginList.get(index).setLogin(true);
						//��̨��ʾ�ַ�����
						printStringOnScreen(_0);
					}
				}
				break;
			case MessageHead.BROADCAST_OFFLINE : //��client����,�����˽���
				Member deleteMember = new Member(matcher.group(2));
				String _1 = deleteMember.getNickname() + " �뿪�������ҡ�"; 
				int index2 = loginList.indexOf(deleteMember);
				if(running_mode == CLIENT_MODE) {
					if(index2 == -1) {
						loginList.remove(index2);
						printStringOnScreen(_1);
					}
				}
				if(running_mode == SERVER_MODE) {
					if(index2 == -1) {
						loginList.get(index2).setLogin(false);
						printStringOnScreen(_1);
					}
				}
				break;
			case MessageHead.LOGIN_REQUEST : //server mode
				if(running_mode == SERVER_MODE) {
					Member loginMember = (Member) RegexUtil.pattern_match(s, MessageHead.parse_member, MessageHead.LOGIN_REQUEST, null, null, null);
					int status = isLogin(loginMember);
					if(status == MessageHead.NEW_ACCOUNT || status == MessageHead.ALL_RIGHT) {
						//��¼�ɹ�
					}
					if(status == MessageHead.ONLINE) {
						//��ʱ���׼��
						cleanWhenTimeOut(loginList);
					}
					//������Ϣ�ַ��������͡�
					String _2 = StringMessage.messageLOGIN_FEEDBACK(loginMember, status);
					udpUtil.sendUdpPacket(_2);
				}
				break;
			case MessageHead.LOGIN_FEEDBACK : //client mode(����ɵ�¼ǰ)
				int resultOfLogin = (Integer) RegexUtil.pattern_match(s, MessageHead.login_feedback, MessageHead.LOGIN_FEEDBACK, 
						thisMember.getNickname(), thisMember.getPublicKey(), null);
				//��������
				if((resultOfLogin == MessageHead.ALL_RIGHT) || (resultOfLogin == MessageHead.NEW_ACCOUNT)) {
					//��¼�ɹ�����ת����
					windowCallBack.nextStep();
				}
				else {
					//��ʾ������Ϣ
					String _3 = null;
					switch(resultOfLogin) {
					case MessageHead.ONLINE :
						_3 = "���˺��ѵ�¼��";
						break;
					case MessageHead.WRONG_PASSWORD :
						_3 = "�������";
						break;
					case MessageHead.ILLEGAL :
						_3 = "�ǳƺ��Ƿ��ַ���-����";
						break;
					default:
					}
					//���ַ����������¼�����label
					printStringOnScreen(_3);
				}
				break;
			case MessageHead.MESSAGE_PUBLIC :
				String showString = (String) RegexUtil.pattern_match(s, MessageHead.message_public, MessageHead.MESSAGE_PUBLIC, null, null, null);
				//ǰ̨��ʾ�ַ�����
				printStringOnScreen(showString);
				break;
			case MessageHead.MESSAGE_PRIVATE : //�������Ϳͻ������ֲ���
				if(running_mode == SERVER_MODE) {
					String privateStringOnServer = (String) RegexUtil.pattern_match(s, MessageHead.message_private, MessageHead.PRIVATE_ON_SERVER, null, null, null);
					//����˻���
					printStringOnScreen(privateStringOnServer);
				}
				if(running_mode == CLIENT_MODE) {
					String privateString = (String) RegexUtil.pattern_match(s, MessageHead.message_private, MessageHead.MESSAGE_PRIVATE, 
							thisMember.getNickname(), null, thisRsa.getPrivateKey());
					if(privateString != null) {
						//ǰ̨��ʾ�ַ�����
						printStringOnScreen(privateString);
					}
				}
				break;
			case MessageHead.MESSAGE_FROM_SERVER : //client mode
				String serverString = "SERVER : " + s;
				//ǰ̨��ʾ�ַ�����
				printStringOnScreen(serverString);
				break;
			default :
			}
		}
		//ˢ���б�
		
	}
	/**
	 * ��ʱ�����������ֹ�����ߡ�
	 * @param originalList
	 */
	private void cleanWhenTimeOut(final List<Member> originalList) {
		List<Member> onlineList = new ArrayList<Member>();
		
	}
	
	private void printStringOnScreen(String s) {
		windowCallBack.textAreaAppend(s);
	}
	public void login() {
		udpUtil.sendUdpPacket(StringMessage.messageLOGIN_REQUEST(thisMember));
	}
}


