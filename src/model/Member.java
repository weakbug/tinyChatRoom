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
	 * 主构造器
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
	 * 辅用构造器
	 * 用于验证登录，用完丢弃
	 * @param nickname
	 */
	public Member(String nickname) {
		this.nickname 	= nickname;
	}
	
	/**
	 * 如果两个Member实例的nickname相同，此覆写方法将两个实例视为可相等。
	 */
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
	@Override
	public String toString() {
		String r = nickname + "-" + (password==null?"null":password) + "-" + (ipAddress==null?"0.0.0.0":ipAddress) + "-" + (publicKey==null?"null":publicKey);
		return r;
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
	public void setLogin(boolean l) {
		isLogin = l;
	}
	public void setPublicKey(String pk) {
		publicKey = pk;
	}
	public void setIpAddress(String ip) {
		ipAddress = ip;
	}
}
