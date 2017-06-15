package control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.*;

/**
 * ����Ϣͷ��¼��ģʽ���������ַ������ݲ����ؽ����
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
