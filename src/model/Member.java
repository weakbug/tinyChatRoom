package model;

import java.net.InetAddress;

import control.RsaUtil;

public class Member {

	private String nickname;
	private String password;
	private String ipAddress;
	private RsaUtil rsaUtil;
	private boolean isLogin;
	
	/**
	 * ��������
	 * @param nickname
	 * @param password
	 * @param ipAddress
	 */
	public Member(String nickname, String password, String ipAddress) {
		/** ʵ����ʱ����rsaʵ�� */
		rsaUtil = new RsaUtil();
		isLogin = false;
		this.nickname 	= nickname;
		this.password 	= password;
		this.ipAddress 	= ipAddress;
	}
	/**
	 * ���ù�����
	 * ������֤��¼�����궪��
	 * @param nickname
	 */
	public Member(String nickname) {
		this.nickname 	= nickname;
	}
	
	@Override
	public boolean equals(Object anObject) {
		if(this == anObject) {
			return true;
		}
		if(anObject instanceof Member) {
			Member anotherMember = (Member)anObject;
			if(this.getNickname() == anotherMember.getNickname()) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	/** five getter functions below */
	public String getNickname() {
		return nickname;
	}
	public String getPassword() {
		return password;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public RsaUtil getRsaUtil() {
		return rsaUtil;
	}
	public boolean isLogin() {
		return isLogin;
	}
	
}
