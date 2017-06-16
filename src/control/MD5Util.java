package control;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
	/**利用MD5进行加密
 　　* @param str  待加密的字符串
　　* @return  加密后的字符串
　　* @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
　　 * @throws UnsupportedEncodingException  不支持此编码
　　*/
	public static String encoderByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}
	/**判断用户密码是否正确
　　* @param newpassword  用户输入的密码
　　* @param oldpassword  数据库中存储的密码－－用户密码的摘要
　　* @return	密码验证相同返回true,不同则返回false
　　* @throws NoSuchAlgorithmException
　　* @throws UnsupportedEncodingException
　　*/
	public static boolean checkPassword(String newPassword,String oldPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		if(encoderByMD5(newPassword).equals(oldPassword)){
			return true;
		}else return false;
	}
//	public static void main(String args[]) throws NoSuchAlgorithmException, UnsupportedEncodingException{
//	String str1 = "abc123";
//	String str2 = "abc123";
//	String str3 = "def456";
//	String str4 = encoderByMD5(str1);
//	System.out.println(str4);
//	System.out.println(checkPassword(str2,str4)+"\t"+checkPassword(str3,str4));
//}
}

