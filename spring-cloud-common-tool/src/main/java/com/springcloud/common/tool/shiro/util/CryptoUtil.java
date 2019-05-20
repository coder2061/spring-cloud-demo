package com.springcloud.common.tool.shiro.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密/解密
 *
 * @author jiangyf
 */
public class CryptoUtil {
    // 随机数生成器
    private static final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    // 散列算法
    public static final String algorithmName = "SHA-512";
    // 迭代次数
    public static final int hashIterations = 5;

    /**
     * 获取盐
     *
     * @return
     */
    public static String getSalt() {
        return randomNumberGenerator.nextBytes().toBase64();
    }

    /**
     * 加密
     *
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        return encryptPassword(password, getSalt());
    }

    /**
     * 加密
     *
     * @param password
     * @param salt
     * @return
     */
    public static String encryptPassword(String password, String salt) {
        return new SimpleHash(algorithmName, password, ByteSource.Util.bytes(salt), hashIterations).toBase64();
    }

    /**
     * 检验密码是否正确
     *
     * @param pwd          原密码
     * @param salt         盐值
     * @param encryptedPwd 加密后的密码字符串
     * @return
     */
    public static boolean verifyPassword(String pwd, String salt, String encryptedPwd) {
        return encryptPassword(pwd, salt).equals(encryptedPwd);
    }

//	public static void main(String[] args) {
//		String salt = randomNumberGenerator.nextBytes().toHex();
//		System.out.println("----- salt -----" + salt);
//		System.out.println("----- password -----" + encryptPassword("123456", salt));
//		System.out.println("----- password -----" + verifyPassword("12345678", "oZNn05EUN2VO" ,"UcheDxwx6uld0nF2XeWSm9yRJneIzZdujQmKe4wLltFltPXS86hejA7uB7py1JT0gdRVgyrp8qz8WPhjKcEmzg==" ));
//	}
}
