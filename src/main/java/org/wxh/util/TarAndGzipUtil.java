package org.wxh.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;


/**
 * 打包tar和gzip压缩的工具类
 * 懒汉式单例类（在第一次调用的时候实例化自己  ）
 * 单例模式主要优点：
	1、提供了对唯一实例的受控访问。
	2、由于在系统内存中只存在一个对象，因此可以节约系统资源，对于一些需要频繁创建和销毁的对象单例模式无疑可以提高系统的性能。
	3、允许可变数目的实例。
 * @author wxh
 *
 */
public class TarAndGzipUtil {
	private static TarAndGzipUtil util;
	private TarAndGzipUtil(){}
	
	public static TarAndGzipUtil getInstance() {
		if(util==null) util = new TarAndGzipUtil();
		return util;
	}
	
	/**
	 * 压缩文件
	 * 文件 --》 tar --》 gzip
	 * @param path 文件路径
	 * @param tarFile 压缩后的目标路径+文件名
	 */
	public void tarFile(String path,String tarFile) {
		TarArchiveOutputStream taos = null;
		try {
			File f = new File(path);
			int len = f.getParent().length();
			taos = new TarArchiveOutputStream(new FileOutputStream(tarFile));
			taos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
			tarFile(new File(path),taos,len);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(taos!=null) taos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		gzipFile(new File(tarFile));
	}
	/**
	 * 解tar文件
	 * @param file
	 * @param path
	 */
	public void unTarFile(File file,String path) {
		TarArchiveInputStream tais = null;
		File tf = null;
		try {
			tf = unGzipFile(file);
			tais = new TarArchiveInputStream(new FileInputStream(tf));
			TarArchiveEntry tae = null;
			while((tae=tais.getNextTarEntry())!=null) {
				if(!tae.isDirectory()) {
					String name = path+File.separator+tae.getName();//d:/test/stu/.classpath
					FileOutputStream fos = null;
					File ff = new File(name);
					if(!ff.getParentFile().exists()) ff.getParentFile().mkdirs();
					try {
						fos = new FileOutputStream(ff);
						IOUtils.copy(tais, fos);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if(fos!=null) fos.close();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(tais!=null) tais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(tf!=null) {
				tf.delete();
				tf.deleteOnExit();
			}
		}
	}
	/**
	 * 解压Gzip文件
	 * @param file
	 * @return
	 */
	public File unGzipFile(File file) {
		GZIPInputStream gis = null;
		FileOutputStream fos = null;
		try {
			gis = new GZIPInputStream(new FileInputStream(file));
			String path = file.getAbsolutePath();
			path = path.substring(0,path.lastIndexOf("."));
			//要返回的文件
			File f = new File(path);
			fos = new FileOutputStream(f);
			IOUtils.copy(gis, fos);
			return f;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(gis!=null) gis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(fos!=null) fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	/**
	 * 压缩成gzip文件
	 * @param file
	 */
	public void gzipFile(File file) {
		GZIPOutputStream gos = null;
		FileInputStream fis = null;
		try {
			gos = new GZIPOutputStream(new FileOutputStream(file.getAbsolutePath()+".gz"));
			fis = new FileInputStream(file);
			IOUtils.copy(fis, gos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(gos!=null) gos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(fis!=null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			file.delete();
		}
	}
	
	private void tarFile(File file, TarArchiveOutputStream taos,int len) {
		if(file.isDirectory()) {
			File[] fs = file.listFiles();
			for(File f:fs) {
				tarFile(f,taos,len);
			}
		} else {
			FileInputStream fis = null;
			try {
//				System.out.println(file.getAbsolutePath().substring(len)+File.separator+file.getName());
				TarArchiveEntry tae = new TarArchiveEntry(file.getParent().substring(len)+File.separator+file.getName());
				tae.setSize(file.length());
				fis = new FileInputStream(file);
				taos.putArchiveEntry(tae);
				IOUtils.copy(fis, taos);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(fis!=null) fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					taos.closeArchiveEntry();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
