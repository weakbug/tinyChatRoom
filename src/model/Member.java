package model;

import java.net.InetAddress;

import control.RsaUtil;

public class Member {

	private String nickname;
	private String password;
	private String ip;
	private String publicKey;
	private RsaUtil rsaUtil;
	
	/** 实例化时生成rsa实例 */
	private Member() {
		rsaUtil = new RsaUtil();
	}
	public Member(String nickname, String password, String ip, String publicKey) {
		this();
		this.nickname 	= nickname;
		this.password 	= password;
		this.ip 				= ip;
		this.publicKey 	= publicKey;
	}
	
	public RsaUtil getRsaUtil() {
		return rsaUtil;
	}
}
