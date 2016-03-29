package org.wxh.basic.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;


/*
 * MD5 算法
*/
public class MD5 {
    
    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public MD5() {
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
    
    public static String GetMD5CodeUtf8(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            try {
            	byte[] z = strObj.getBytes("utf-8");
            	int off = 0;
            	if(z.length >= 3 && z[0] == (byte)0xEF && z[1] == (byte)0xBB && z[2] == (byte)0xBF)
            	{
            		off = 3;
            		z = Arrays.copyOfRange(z, off, z.length);
            	}
            	
       
				resultString = byteToString(md.digest(z));
			} catch (UnsupportedEncodingException e) {
			
				e.printStackTrace();
			}
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

	public static String getMd5Value(Map<String, String> paramsMap) {
	StringBuilder value = new StringBuilder(128);
	 //key值按字典顺序排序(升序)
	 Object[]   key   =     paramsMap.keySet().toArray();   
     Arrays.sort(key);  
     for   (int   i   =   0;   i   <   key.length;   i++)  
     {  
    	 if(paramsMap.get(key[i]).equals("")||paramsMap.get(key[i]).equals("null")||null==paramsMap.get(key[i]))
    		 continue;
        value.append(paramsMap.get(key[i]));   
     }   
		return GetMD5CodeUtf8(value.toString());
	}
    

}