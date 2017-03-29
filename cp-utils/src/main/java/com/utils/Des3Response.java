package com.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * @author
 */
public class Des3Response {
	// 密钥
	private final static String secretKey = "h1k2#3s4f5d6%7d8s9@0s1f2";
	// 向量
	private final static String iv = "20140305";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	/**
	 * 3DES加密
	 * 
	 * @param content 内容
	 * @return
	 * @throws EncodeException 
	 */
	public static String encode(String content) throws Exception {
		try {
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			Key deskey = keyfactory.generateSecret(spec);
	
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(content.getBytes(encoding));
			return Base64.encode(encryptData);
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(encode("123456"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
