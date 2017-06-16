package control;

import model.Member;
import model.MessageHead;

public class StringMessage {
	
	public static String messageBROADCAST_ONLINE_ASK(String nickname){
		String msg=null;
		msg=MessageHead.BROADCAST_ONLINE_ASK + "-" + nickname;
		return msg;
	}
	
	public static String messageBROADCAST_ONLINE (Member member){
		String msg=null;
		msg = MessageHead.BROADCAST_ONLINE + "-" + member.toString();
		return msg;
	}
	
	public static String messageBROADCAST_OFFLINE(String nickname){
		String msg=null;
		msg = MessageHead.BROADCAST_OFFLINE + "-" + nickname;
		return msg;
	}
	
	public static String messageLOGIN_REQUEST(Member member){
		String msg=null;
		msg = MessageHead.LOGIN_REQUEST + "-" + member.toString();
		return msg;
	}
	
	public static String messageLOGIN_FEEDBACK(Member member,Integer state){
		String msg=null;
		msg = MessageHead.LOGIN_FEEDBACK + "-" + member.getNickname() + 
				   "-" + state.toString() + "-" + member.getPublicKey();
		return msg;
	}
	
	public static String messageMESSAGE_PUBLIC (String origin,String message){
		String msg=null;
		msg = MessageHead.MESSAGE_PUBLIC + "-" + origin + "-" + message;
		return msg;
	}
	
	public static String messageMESSAGE_PRIVATE(String origin,String target,String message){
		String msg=null;
		msg = MessageHead.MESSAGE_PRIVATE + "-" + origin + "-" + target + "-"+message;
		return msg;
	}
	
	public static String messageMESSAGE_FROM_SERVER(String message){
		String msg=null;
		msg = MessageHead.MESSAGE_FROM_SERVER + "-" + message;
		return msg;
	}

}
