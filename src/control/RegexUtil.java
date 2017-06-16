package control;

import java.security.PrivateKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.*;

/**
 * 按信息头记录的模式解析附加字符串内容并返回结果。
 * @author Shinrai
 * @since 2017-6-16 01:35:09
 */
public class RegexUtil {
	public static Object pattern_match(String s, Pattern p, int mode, String nickname, String publicKey, PrivateKey privateKey) {
		Matcher matcher = p.matcher(s);
		if(matcher.find()) {
			switch(mode) {
			case MessageHead.BROADCAST_ONLINE :
			case MessageHead.LOGIN_REQUEST :
				return new Member(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
			case MessageHead.LOGIN_FEEDBACK :
				if(nickname == matcher.group(1) && publicKey == matcher.group(3)) {
					return Integer.valueOf(matcher.group(2));
				}
			case MessageHead.MESSAGE_PUBLIC :
				return matcher.group(1) + " : " + matcher.group(2);
			case MessageHead.MESSAGE_PRIVATE :
				if(nickname == matcher.group(2)) {
					return matcher.group(1) + RsaUtil.uncodedText(matcher.group(3), privateKey);
				}
			default:
				break;
			}
		}
		return null;
	}

}
