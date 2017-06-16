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
	private Member thisMember;
	private RsaUtil thisRsa;
	public static final int CLIENT_MODE = 1;
	public static final int SERVER_MODE = 2;
	
	/**
	 * 实例化后台类并设置后台模式。
	 * @param mode 执行后台的模式。
	 */
	public Backstage(int mode) {
		running_mode = mode;
		initialize();
	}
	
	private void initialize() {
		loginList = new ArrayList<Member>();
		// 启动UDP接收线程。
		udpUtil = new UdpUtil(this);
	}
	
	/**
	 * server mode
	 * 给定请求中的参数 'nickname' 'password'，查询数据表，判断是否允许登录。
	 * 情况分为：
	 * 1. 账号密码与数据表记录相同，'isLogin' 标记未登录，更改标记并返回 ALL_RIGHT。
	 * 2. 账号密码与数据表记录相同，'isLogin' 标记已登录，返回 MAYBE_REPEAT 并广播询问登录情况，视广播情况回应广播。（有点难度）
	 * 3. 账号对应密码不相同，返回 WRONG_PASSWORD。
	 * 4. 不存在此账号信息，添加到数据表、置 'isLogin' 标记并返回 NEW_ACCOUNT。（即新建账号操作）
	 * 5. 昵称中存在非法字符，禁止登录，返回 ILLEGAL。
	 * @param nickname 请求登录的昵称。
	 * @param password 请求登录的密码（md5）
	 * @return 
	 */
	private int isLogin(String nickname, String password) {
		if(nickname.contains(MessageHead.ban_meta)) {
			return MessageHead.ILLEGAL;
		}
		Member __new = new Member(nickname);
		int index_of_member = loginList.indexOf(__new);
		if(index_of_member == -1) { //不存在账号（即新建账号操作）
			return MessageHead.NEW_ACCOUNT;
		}
		else {
			Member origin_member = loginList.get(index_of_member);
			if( !(origin_member.getPassword().equals(password)) ) { //密码错误
				return MessageHead.WRONG_PASSWORD;
			}
			else if(origin_member.isLogin()) { //标记正在登录
				return MessageHead.MAYBE_REPEAT;
			}
			else { //正常登录
				return MessageHead.ALL_RIGHT;
			}
		}
	}
	
	/**
	 * Udp线程中获取到消息时执行此方法回调。此方法执行信息分析或分派工作。
	 * @param msg 回调的字符串信息。
	 */
	public void receiveMessage(String msg) {
		Matcher matcher = MessageHead.first_parse.matcher(msg);
		if(matcher.find()) {
			String s = matcher.group(2);
			switch( Integer.parseInt(matcher.group(1)) ) {
			case MessageHead.BROADCAST_ONLINE_ASK :
				if(s.equals(MessageHead.ban_meta)) { //all client want to reply
					//reply
				}
				if(s.equals(thisMember.getNickname())) {
					//reply(only me)
				}
				break;
			case MessageHead.BROADCAST_ONLINE :
				Member newMember = (Member) RegexUtil.pattern_match(s, MessageHead.parse_member, MessageHead.BROADCAST_ONLINE, null, null, null);
				break;
			case MessageHead.BROADCAST_OFFLINE :
				Member deleteMember = new Member(matcher.group(2));
				break;
			case MessageHead.LOGIN_REQUEST : //server mode
				Member loginMember = (Member) RegexUtil.pattern_match(s, MessageHead.parse_member, MessageHead.LOGIN_REQUEST, null, null, null);
				int status = isLogin(loginMember.getNickname(), loginMember.getPassword());
				if(status == MessageHead.NEW_ACCOUNT || status == MessageHead.ALL_RIGHT) {
					//登录成功
				}
				//构造信息字符串并发送。
				udpUtil.sendUdpPacket(null);
				break;
			case MessageHead.LOGIN_FEEDBACK : //client mode(仅完成登录前)
				int resultOfLogin = (Integer) RegexUtil.pattern_match(s, MessageHead.login_feedback, MessageHead.LOGIN_FEEDBACK, 
						thisMember.getNickname(), thisMember.getPublicKey(), null);
				//处理或回显
				break;
			case MessageHead.MESSAGE_PUBLIC :
				String showString = (String) RegexUtil.pattern_match(s, MessageHead.message_public, MessageHead.MESSAGE_PUBLIC, null, null, null);
				//前台显示字符串。
				break;
			case MessageHead.MESSAGE_PRIVATE : //服务器和客户端区分操作
				if(running_mode == SERVER_MODE) {
					String privateString = (String) RegexUtil.pattern_match(s, MessageHead.message_private, MessageHead.PRIVATE_ON_SERVER, null, null, null);
					//服务端回显
				}
				else if(running_mode == CLIENT_MODE) {
					String privateString = (String) RegexUtil.pattern_match(s, MessageHead.message_private, MessageHead.MESSAGE_PRIVATE, 
							thisMember.getNickname(), null, thisRsa.getPrivateKey());
					if(privateString != null) {
						//前台显示字符串。
					}
				}
				break;
			case MessageHead.MESSAGE_FROM_SERVER : //client mode
				String serverString = "SERVER : " + s;
				//前台显示字符串。
				break;
			default :
				break;
			}
		}
	}
}


