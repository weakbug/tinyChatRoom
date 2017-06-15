package model;

import java.net.InetAddress;

import control.RsaUtil;

public class Member {

	private String nickname;
	private String password;
	private String ipAddress;
	private String publicKey;
	private boolean isLogin;
	
	/**
	 * ��������
	 * @param nickname
	 * @param password
	 * @param ipAddress
	 */
	public Member(String nickname, String password, String ipAddress, String publicKey) {
		isLogin = false;
		this.publicKey = publicKey;
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
	public String getPublicKey() {
		return publicKey;
	}
	public boolean isLogin() {
		return isLogin;
	}
	
}
