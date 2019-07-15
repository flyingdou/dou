package com.dou.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class EasyUtils {

	// 算法名
	public static final String KEY_NAME = "AES";
	// 加解密算法/模式/填充方式
	// ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个iv
	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

	/**
	 * 生成No:(当前年月加上上一条数据的no累加)
	 * 
	 * @param no
	 * @return
	 */
	public static String generationNoByLastNo(String no) {
		if (StringUtils.isNotEmpty(no)) {
			String noNumber = no.substring(8, no.length());
			String lastData = no.substring(0, 8);
			String dateFormat = new SimpleDateFormat("yyyyMMdd").format(new Date());
			int number = Integer.parseInt(noNumber);
			if (lastData.equals(dateFormat)) {
				number++;
			} else {
				number = 1;
			}
			int length = String.valueOf(number).length();
			noNumber = "";
			for (int i = 0; i < 2 - length; i++) {
				noNumber += "0";
			}
			noNumber += number;
			return dateFormat + noNumber;
		} else {
			return new SimpleDateFormat("yyyyMMdd").format(new Date()) + "01";
		}
	}

	/**
	 * 通过日期获取随机数
	 * 
	 * @param format
	 * @param length
	 * @return
	 */
	public static String getRandomNoByDate(String format, int length) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String result = "";
		result += sdf.format(new Date());
		for (int i = 0; i < length; i++) {
			result += (int) (Math.random() * 10) + "";
		}
		return result;

	}

	/**
	 * 微信 数据解密<br/>
	 * 对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充<br/>
	 * 对称解密的目标密文:encrypted=Base64_Decode(encryptData)<br/>
	 * 对称解密秘钥:key = Base64_Decode(session_key),aeskey是16字节<br/>
	 * 对称解密算法初始向量:iv = Base64_Decode(iv),同样是16字节<br/>
	 *
	 * @param encrypted
	 *            目标密文
	 * @param session_key
	 *            会话ID
	 * @param iv
	 *            加密算法的初始向量
	 */
	@SuppressWarnings("unused")
	public static String wxDecrypt(String encrypted, String session_key, String iv) {
		String json = null;
		byte[] encrypted64 = Base64.decodeBase64(encrypted);
		byte[] key64 = Base64.decodeBase64(session_key);
		byte[] iv64 = Base64.decodeBase64(iv);
		byte[] data;
		try {
			init();
			json = new String(decrypt(encrypted64, key64, generateIV(iv64)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 初始化密钥
	 */
	public static void init() throws Exception {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		KeyGenerator.getInstance(KEY_NAME).init(128);
	}

	/**
	 * 生成iv
	 */
	public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
		// iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
		// Arrays.fill(iv, (byte) 0x00);
		AlgorithmParameters params = AlgorithmParameters.getInstance(KEY_NAME);
		params.init(new IvParameterSpec(iv));
		return params;
	}

	/**
	 * 生成解密
	 */
	public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes, AlgorithmParameters iv) throws Exception {
		Key key = new SecretKeySpec(keyBytes, KEY_NAME);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		return cipher.doFinal(encryptedData);
	}

	/**
	 * 从解密数据中获取需要的字段
	 * 
	 * @param AESstring
	 *            解密后的数据
	 * @param target
	 *            需要获取的字段
	 * @return
	 */
	public static String getTargetFromAES(String AESstring, String target) {
		String[] douStrx = AESstring.split(",");
		for (String string : douStrx) {
			if (string.indexOf(target) > 0) {
				string = string.replace("'", "");
				string = string.replace("\"", "");
				String[] doux = string.split(":");
				return doux[1];
			}
		}
		return "";
	}

	/**
	 * emoji表情替换
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String cutStringEmoji(String str) throws Exception {
		char[] chr = str.toCharArray();
		String strx = "";
		for (int i = 0; i < chr.length; i++) {
			if (((chr[i] >= 0x4e00) && (chr[i] <= 0x9fbb))
					|| ((chr[i] >= 'A' && chr[i] <= 'Z') || (chr[i] >= 'a' && chr[i] <= 'z'))
					|| (chr[i] > 47 && chr[i] < 58)) {
				strx += String.valueOf(chr[i]);
			} else {
				chr[i] = '*';
				strx += String.valueOf(chr[i]);
			}
		}

		return strx;
	}

	/**
	 * 把String转成Date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date formatStringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Map<String, String> getRequestParametersForXML(HttpServletRequest request) throws Exception {
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		return map;
	}
	
	
    /**
     * 字符串格式化(占位符填充, 占位符: {})
     * 
     * @param origin
     * @param args
     * @return
     */
    public static String stringFormat(String origin, String... args) {
     StringBuffer result = new StringBuffer(origin);
     String rep = "{}";
     for (String arg : args) {
      int start = result.indexOf(rep);
      int end = start + rep.length();
      result.replace(start, end, arg);
     }
     return result.toString();
    }


}
