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
	private enum loginStatus {
		ALL_RIGHT, MAYBE_REPEAT, WRONG_PASSWORD, NEW_ACCOUNT;
	}
	
	public Backstage() {
		initialize();
	}
	
	private void initialize() {
		loginList = new ArrayList<Member>();
		// 启动UDP接收线程。
		udpUtil = new UdpUtil(this);
	}
	
	/**
	 * 给定请求中的参数 'nickname' 'password'，查询数据表，判断是否允许登录。
	 * 情况分为：
	 * 1. 账号密码与数据表记录相同，'isLogin' 标记未登录，更改标记并返回 ALL_RIGHT。
	 * 2. 账号密码与数据表记录相同，'isLogin' 标记已登录，返回 MAYBE_REPEAT 并广播询问登录情况，视广播情况回应广播。（有点难度）
	 * 3. 账号对应密码不相同，返回 WRONG_PASSWORD。
	 * 4. 不存在此账号信息，添加到数据表、置 'isLogin' 标记并返回 NEW_ACCOUNT。（即新建账号操作）
	 * @param nickname 请求登录的昵称。
	 * @param password 请求登录的密码（md5）
	 * @return 是否允许登录：true - 允许 / false - 不允许
	 */
	private loginStatus isLogin(String nickname, String password) {
		Member __new = new Member(nickname);
		int index_of_member = loginList.indexOf(__new);
		if(index_of_member == -1) { //不存在账号（即新建账号操作）
			return loginStatus.NEW_ACCOUNT;
		}
		else {
			Member origin_member = loginList.get(index_of_member);
			if( !(origin_member.getPassword().equals(password)) ) { //密码错误
				return loginStatus.WRONG_PASSWORD;
			}
			else if(origin_member.isLogin()) { //标记正在登录
				return loginStatus.MAYBE_REPEAT;
			}
			else { //正常登录
				return loginStatus.ALL_RIGHT;
			}
		}
	}
	
	/**
	 * Udp线程中获取到消息时执行此方法回调。此方法执行信息分析或分派工作。
	 * @param msg 回调的字符串信息。
	 */
	public void receiveMessage(String msg) {
		
	}
}


