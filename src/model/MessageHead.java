package model;

/**
 * 定义广播信息的头部。
 * @author Shinrai
 * @since 2017-6-16 01:37:23
 */
public class MessageHead {
	
	public static final int EVERYONE 		= 1;
	public static final int LOGIN 				= 2;
	public static final int MESSAGE 			= 3;
	/** statement of EVERYONE */
	public static final int ONLINE_ASK 	= 11; //Please reply to me who is online (String(nickname))
	public static final int ONLINE 			= 12; //Someone appeared (String(info set))															R
	public static final int OFFLINE 			= 13; //Someone disappeared (String(nickname))
	public static final int ONLINE_FB 		= 14; //I am online (String(nickname))
	/** statement of LOGIN */
	public static final int REQUEST 			= 21; //Request to login (String(info set))																R
	public static final int FEEDBACK 		= 22; //Feedback results (String(nickname + result + publicKey)) 							R
	/** statement of MESSAGE */
	public static final int PUBLIC 				= 31; //Everyone can see this information (String(origin + msg))							R
	public static final int PRIVATE 			= 32; //Only one person can see this information (String(origin + target + msg))	R
	
	/** statement of REQUEST or FEEDBACK */
	public static final int ALL_RIGHT 					= 51;
	public static final int MAYBE_REPEAT 			= 52;
	public static final int WRONG_PASSWORD 	= 53;
	public static final int NEW_ACCOUNT			= 54;
	public static final int ILLEGAL 						= 55;
	
}
