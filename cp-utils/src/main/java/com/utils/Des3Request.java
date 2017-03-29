package com.utils;

import java.security.Key;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


/** 
 * @author 
 */
public class Des3Request {
    // 密钥  	                                 
    private final static String secretKey = "h1k2#3s4f5d6%7d8s9@0s1f2";
    // 向量  
    private final static String iv = "20140305";
    // 加解密统一使用的编码方式  
    private final static String encoding = "utf-8";

    /** 
     * 3DES解密 
     *  
     * @param encryptText 加密文本 
     * @return 
     * @throws DecodeException 
     */
    public static String decode(String encryptText) throws Exception{
    	try {
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            Key deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

            byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

            return new String(decryptData, encoding);
    	} catch (Exception e) {
			throw new Exception();
		}
    }

    /**
     * 获取参数并解密
     * @date 2014.04.12
     * @param request
     * @return
     * @author Wangweijun
     * @throws DecodeException 
    @SuppressWarnings("unchecked")
    public static String getDecodeParameter(HttpServletRequest request) throws Exception {
    	Object oo = request.getParameter("param");
    	if( oo == null){
    		oo = request.getAttribute("param");
    	}
    	String param = null;
    	if (null != oo) {
    		param = oo.toString();
		}
        Map<String, String[]> map = request.getParameterMap();
        if (null == param && map.get("param") != null) {
            param = map.get("param")[0];
        }
        if (null != param) {
        	return Des3Request.decode(param);
		}else {
            return "";
        }
    }
    */
    public static void main(String[] args) throws Exception {
		System.out.println(decode("nPLIpr26ldA="));
	}
}
