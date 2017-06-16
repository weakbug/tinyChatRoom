package model;

import java.util.regex.Pattern;

/**
 * 定义广播信息的头部。
 * @author Shinrai
 * @since 2017-6-16 01:37:23
 */
public class MessageHead {
	
	/** statement of EVERYONE */
	public static final int BROADCAST_ONLINE_ASK 	= 11; //Please reply to me if you are online (String(nickname))
	public static final int BROADCAST_ONLINE 			= 12; //Someone appeared (String(info set))													(\d+)-(\d+)-(.+$)
	public static final Pattern broadcast_online 		= Pattern.compile("(\\d+)-(\\d+)-(.+$)");
	public static final int BROADCAST_OFFLINE 			= 13; //Someone disappeared (String(nickname))
	public static final int BROADCAST 	= 14; //I am online (String(nickname))
	/** statement of LOGIN */
	public static final int LOGIN_REQUEST 				= 21; //Request to login (String(info set))														(\d+)-(\d+)-(.+$)
	public static final Pattern login_request 			= broadcast_online;
	public static final int LOGIN_FEEDBACK 			= 22; //Feedback results (String(nickname + result + publicKey)) 					([^-]+)-(\d+)-(.+$)
	public static final Pattern login_feedback 			= Pattern.compile("([^-]+)-(\\d+)-(.+$)");
	/** statement of MESSAGE */
	public static final int MESSAGE_PUBLIC 			= 31; //Everyone can see this information (String(origin + msg))					([^-]+)-(.+$)
	public static final Pattern message_public 			= Pattern.compile("([^-]+)-(.+$)");
	public static final int MESSAGE_PRIVATE 			= 32; //One person can see this information (String(origin + target + msg))	([^-]+)-([^-]+)-(.+$)
	public static final Pattern message_private 		= Pattern.compile("([^-]+)-([^-]+)-(.+$)");
	public static final int MESSAGE_FROM_SERVER 	= 33;
	
	public static final Pattern first_parse 					= Pattern.compile("(\\d+)-(.+$)");
	
	/** statement of REQUEST or FEEDBACK */
	public static final int ALL_RIGHT 						= 51;
	public static final int MAYBE_REPEAT 				= 52;
	public static final int WRONG_PASSWORD 		= 53;
	public static final int NEW_ACCOUNT				= 54;
	public static final int ILLEGAL 							= 55;
	
}
