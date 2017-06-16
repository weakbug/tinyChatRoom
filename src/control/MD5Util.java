package control;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
	/**����MD5���м���
 ����* @param str  �����ܵ��ַ���
����* @return  ���ܺ���ַ���
����* @throws NoSuchAlgorithmException  û�����ֲ�����ϢժҪ���㷨
���� * @throws UnsupportedEncodingException  ��֧�ִ˱���
����*/
	public static String encoderByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}
	/**�ж��û������Ƿ���ȷ
����* @param newpassword  �û����������
����* @param oldpassword  ���ݿ��д洢�����룭���û������ժҪ
����* @return	������֤��ͬ����true,��ͬ�򷵻�false
����* @throws NoSuchAlgorithmException
����* @throws UnsupportedEncodingException
����*/
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

