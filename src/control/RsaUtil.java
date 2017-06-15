package control;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @see http://www.cnblogs.com/SirSmith/p/4990183.html
 * @since 2015-11-24 00:22
 * modified by shinrai 2017-6-15 14:12:26
 */
public class RsaUtil {
    public static final String KEY_ALGORITHM = "RSA";
    /** ò��Ĭ����RSA/NONE/PKCS1Padding��δ��֤ */
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";

    /** RSA��Կ���ȱ�����64�ı�������512~65536֮�䡣Ĭ����1024 */
    public static final int KEY_SIZE = 512;
    
    /** ���ɵ���Կ�� */
    private PublicKey publicKey;
    private PrivateKey privateKey;

    /**
     * ��ʼ��ʱִ��������Կ�Բ���
     */
    public RsaUtil() {
    	Map<String, byte[]> keyMap = generateKeyBytes();
    	publicKey = restorePublicKey(keyMap.get(PUBLIC_KEY));
    	privateKey = restorePrivateKey(keyMap.get(PRIVATE_KEY));
    }
    
    public PublicKey getPublicKey() {
    	return publicKey;
    }
    public PrivateKey getPrivateKey() {
    	return privateKey;
    }

    /**
     * ������Կ�ԡ�ע��������������Կ��KeyPair��������Կ�Ի�ȡ��˽Կ
     * 
     * @return
     */
    public static Map<String, byte[]> generateKeyBytes() {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator
                    .getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            Map<String, byte[]> keyMap = new HashMap<String, byte[]>();
            keyMap.put(PUBLIC_KEY, publicKey.getEncoded());
            keyMap.put(PRIVATE_KEY, privateKey.getEncoded());
            return keyMap;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ԭ��Կ��X509EncodedKeySpec ���ڹ�����Կ�Ĺ淶
     * 
     * @param keyBytes
     * @return
     */
    public static PublicKey restorePublicKey(byte[] keyBytes) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);

        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ԭ˽Կ��PKCS8EncodedKeySpec ���ڹ���˽Կ�Ĺ淶
     * 
     * @param keyBytes
     * @return
     */
    public static PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = factory
                    .generatePrivate(pkcs8EncodedKeySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ���ܣ������ߡ�
     * 
     * @param key
     * @param plainText
     * @return
     */
    public static byte[] RSAEncode(PublicKey key, byte[] plainText) {

        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(plainText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /**
     * ���ܣ������ߡ�
     * 
     * @param key
     * @param encodedText
     * @return
     */
    public static String RSADecode(PrivateKey key, byte[] encodedText) {

        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(encodedText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
    
    /**
     * �õ���Կ
     * @param key ��Կ�ַ���������base64���룩
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
          byte[] keyBytes;
          keyBytes = (new BASE64Decoder()).decodeBuffer(key);

          X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
          KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
          PublicKey publicKey = keyFactory.generatePublic(keySpec);
          return publicKey;
    }
    /**
     * �õ���Կ�ַ���������base64���룩
     * @return
     */
    public static String getKeyString(Key key) throws Exception {
          byte[] keyBytes = key.getEncoded();
          String s = (new BASE64Encoder()).encode(keyBytes);
          return s;
    }
    
    public static String encodedText(String uncoded_text, PublicKey publicKey) {
    	byte[] encoded_byte = RSAEncode(publicKey, uncoded_text.getBytes());
    	return Base64.encodeBase64String(encoded_byte);
    }
    public static String uncodedText(String  encoded_text, PrivateKey privateKey) {
    	byte[] encoded_byte = Base64.decodeBase64(encoded_text);
    	return RSADecode(privateKey, encoded_byte);
    }
}
