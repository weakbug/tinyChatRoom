package model;

import java.net.InetAddress;

public class Member {

	private String nickname;
	private String password;
	private String ip;
	private String publicKey;
	
	public Member(String nickname, String password, String ip, String publicKey) {
		this.nickname 	= nickname;
		this.password 	= password;
		this.ip 				= ip;
		this.publicKey 	= publicKey;
	}
	
	

}
