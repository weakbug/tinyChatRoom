package control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.*;

/**
 * 按信息头记录的模式解析附加字符串内容并返回结果。
 * @author Shinrai
 * @since 2017-6-16 01:35:09
 */
public class RegexUtil {
	public static Object pattern_match(String s, Pattern p, int mode) {
		Matcher matcher = p.matcher(s);
		if(matcher.find()) {
			switch(mode) {
			case MessageHead.REQUEST:
			case MessageHead.ONLINE:
				return new Member(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
			default:
				break;
			}
		}
		return null;
	}

}
