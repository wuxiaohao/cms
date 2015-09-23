package org.wxh.util;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.wxh.sys.model.BaseInfo;

/**
 * 读写网站基本信息的配置文件的工具类
 * 懒汉式单例类（在第一次调用的时候实例化自己  ）
 * 单例模式主要优点：
	1、提供了对唯一实例的受控访问。
	2、由于在系统内存中只存在一个对象，因此可以节约系统资源，对于一些需要频繁创建和销毁的对象单例模式无疑可以提高系统的性能。
	3、允许可变数目的实例。
 * @author wxh
 *
 */
public class BaseInfoUtil {
	private static BaseInfoUtil biu;
	private static Properties prop;
	
	private BaseInfoUtil() throws IOException {
		if(prop==null) {
			prop = new Properties();
			prop.load(BaseInfoUtil.class.getClassLoader().getResourceAsStream("baseinfo.properties"));
		}
	}
	
	public static BaseInfoUtil getInstacne() {
		try {
			if(biu==null) {
				biu = new BaseInfoUtil();
			}
			return biu;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 读取配置文件信息
	 * @return
	 */
	public BaseInfo read() {
		BaseInfo bi = new BaseInfo();
		bi.setAddress(prop.getProperty("address"));
		bi.setEmail(prop.getProperty("email"));
		bi.setName(prop.getProperty("name"));
		bi.setPhone(prop.getProperty("phone"));
		bi.setRecordCode(prop.getProperty("recordCode"));
		bi.setZipCode(prop.getProperty("zipCode"));
		bi.setDomainName(prop.getProperty("domainName"));
		bi.setIndexPicNumber(Integer.parseInt(prop.getProperty("indexPicNumber")));
		String w = prop.getProperty("indexPicSize");
		
		String[] ws = w.split("\\*");
		bi.setIndexPicWidth(Integer.parseInt(ws[0]));
		bi.setIndexPicHeight(Integer.parseInt(ws[1]));
		return bi;
	}
	
	/**
	 * 写入配置文件信息
	 * @param bi
	 * @return
	 */
	public BaseInfo write(BaseInfo bi) {
		FileOutputStream fos = null;
		try {
			prop.setProperty("address", bi.getAddress());
			prop.setProperty("email", bi.getEmail());
			prop.setProperty("name", bi.getName());
			prop.setProperty("phone",bi.getPhone());
			prop.setProperty("recordCode",bi.getRecordCode());
			prop.setProperty("zipCode",bi.getZipCode());
			prop.setProperty("indexPicNum", String.valueOf(bi.getIndexPicNumber()));
			prop.setProperty("domainName",bi.getDomainName());
			prop.setProperty("indexPicSize", bi.getIndexPicWidth()+"*"+bi.getIndexPicHeight());
			String path = BaseInfoUtil.class.getClassLoader().getResource("baseinfo.properties").getPath();
			System.out.println(path);
			fos = new FileOutputStream(path);
			prop.store(fos,null);
			return bi;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fos!=null) fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
