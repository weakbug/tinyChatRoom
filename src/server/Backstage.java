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
		// 启动UDP接收线程。
		udpUtil = new UdpUtil(this);
	}
	
	/**
	 * 给定请求中的参数 'nickname' 'password'，查询数据库和登录表，判断是否允许登录。
	 * 情况分为：
	 * 1. 账号密码与数据库记录相同，登录表中不存在该账号，将账号置入登录表并返回 true。
	 * 2. 账号密码与数据库记录相同，登录表中存在该账号，广播询问登录情况，视广播情况返回 true 或 false。（有点难度）
	 * 3. 账号对应密码不相同，返回 false。
	 * 4. 不存在此账号信息，添加到数据库和登录表并返回 true。（即新建账号操作）
	 * @param nickname 请求登录的昵称。
	 * @param password 请求登录的密码（md5）
	 * @return 是否允许登录：true - 允许 / false - 不允许
	 */
	private boolean isLogin(String nickname, String password) {
		
		return false;
	}
	
	/**
	 * Udp线程中获取到消息时执行此方法回调。此方法执行信息分析或分派工作。
	 * @param msg 回调的字符串信息。
	 */
	public void receiveMessage(String msg) {
		
	}
}


