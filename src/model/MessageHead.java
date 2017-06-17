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
	//			11--			11-shinrai			11-hlccare			- means all clients
	public static final int BROADCAST_ONLINE 			= 12; //Someone appeared (String(info set))												([^-]+)-(.+)-([.\d]+)-(.+$)
	//			12-shinrai-password-ip-publickey
	public static final Pattern parse_member 				= Pattern.compile("([^-]+)-(.+)-([.\\d]+)-(.+$)");
	public static final int BROADCAST_OFFLINE 			= 13; //Someone disappeared (String(nickname))
	//			13-shinrai			13-hlccare
	/** statement of LOGIN */
	public static final int LOGIN_REQUEST 				= 21; //Request to login (String(info set))														([^-]+)-(.+)-([.\d]+)-(.+$)
	//			21-shinrai-password-ip-publickey
	public static final int LOGIN_FEEDBACK 			= 22; //Feedback results (String(nickname + result + publicKey)) 					([^-]+)-(\d+)-(.+$)
	//			22-shinrai-51-publickey
	public static final Pattern login_feedback 			= Pattern.compile("([^-]+)-(\\d+)-(.+$)");
	/** statement of MESSAGE */
	public static final int MESSAGE_PUBLIC 			= 31; //Everyone can see this information (String(origin + msg))					([^-]+)-(.+$)
	//			31-shinrai-msg
	public static final Pattern message_public 			= Pattern.compile("([^-]+)-(.+$)");
	public static final int MESSAGE_PRIVATE 			= 32; //One person can see this information (String(origin + target + msg))	([^-]+)-([^-]+)-(.+$)
	//			32-shinrai-hlc-msg
	public static final Pattern message_private 		= Pattern.compile("([^-]+)-([^-]+)-(.+$)");
	public static final int MESSAGE_FROM_SERVER 	= 33;
	//			33-msg
	public static final int PRIVATE_ON_SERVER 		= 34; //symbol
	
	public static final Pattern first_parse 					= Pattern.compile("(\\d+)-(.+$)");
	public static final String ban_meta 						= "-";
	
	/** statement of REQUEST or FEEDBACK */
	public static final int ALL_RIGHT 						= 51;
	public static final int ONLINE 							= 52;
	public static final int WRONG_PASSWORD 		= 53;
	public static final int NEW_ACCOUNT				= 54;
	public static final int ILLEGAL 							= 55;
	
}
