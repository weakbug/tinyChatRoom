package model;

import java.net.InetAddress;

public class Member {

	private String account;
	private String password;
	private String nickname;
	private String ip;
	private String publicKey;
	
	public Member(String account, String password, String nickname, String ip, String publicKey) {
		this.account 	= account;
		this.password 	= password;
		this.nickname 	= nickname;
		this.ip 		= ip;
		this.publicKey 	= publicKey;
	}
	
	

}
