package org.wxh.backup;

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
import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * 压缩与解压
 * @author wxh
 *
 */
public class TestTarAndGzip {
	
	/**
	 * 打包成tar包（无递归）
	 * 没有压缩
	 */
	@Test
	public void testSimpleTar() {
		try {
			//要压缩的文件夹
			String path = "G:/javawebCode/jsp/day11";
			//压缩后的路径和名称
			String f = "d:/stu.tar";
			TarArchiveOutputStream taos = new TarArchiveOutputStream(new FileOutputStream(f));
			File of = new File(path); //读取该文件夹的所有文件
			File[] ofs = of.listFiles(); 
			for(File off:ofs) {	//遍历文件夹里的文件
				FileInputStream fis = new FileInputStream(off);
				//此时创建Entry时是通过off来传的，会自动找到相应的绝对路径完成打包，此时如果希望通过相对路径来打包，需要手动设置路径地址
				//TarArchiveEntry tae = new TarArchiveEntry(off);
				String p = off.getParentFile().getParent(); 
				String pn = off.getParentFile().getAbsolutePath().substring(p.length()+1);
				System.out.println(pn);
				//Windows下的路径分隔符和Linux下的路径分隔符是不一样的，windows是\，unix是/，如果要考虑跨平台，则最好加上File.separator
				TarArchiveEntry tae = new TarArchiveEntry(pn+File.separator+off.getName());
				tae.setSize(off.length());
				taos.putArchiveEntry(tae);
				IOUtils.copy(fis, taos);
				fis.close();
				taos.closeArchiveEntry();
			}
			taos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解开tar包
	 */
	@Test
	public void testSimpleUnTar() {
		try {
			TarArchiveInputStream tais = new TarArchiveInputStream(new FileInputStream("d:/stu.tar"));
			TarArchiveEntry tae = null;
			String path = "d:/";
			while((tae=tais.getNextTarEntry())!=null) {
				String p = path+File.separator+tae.getName();
				File f = new File(p);
				if(!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(path+File.separator+tae.getName());
				IOUtils.copy(tais, fos);
				fos.close();
			}
			tais.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 用Gzip压缩tar包
	 * 
	 * 对于linux而已，压缩使用的是gzip,但是只能压缩单个文件，不能压缩文件夹，所以需要先把文件夹打包成tar包（没有压缩），然后再用Gzip压缩
	 * 
	 */
	@Test
	public void testGzip() {
		try {
			GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream("d:/stu2.tar.gz")); //压缩到
			FileInputStream fis = new FileInputStream("d:/stu.tar");//要压缩的文件
			IOUtils.copy(fis, gzos);
			gzos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 将Gzip解压成tar包
	 */
	@Test
	public void testUnGzip() {
		try {
			GZIPInputStream gzis = new GZIPInputStream(new FileInputStream("d:/test.tar.gz")); //要解压的文件
			FileOutputStream fos = new FileOutputStream("d:/test.tar"); //解压为
			IOUtils.copy(gzis,fos);
			gzis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 压缩文件夹
	 */
	@Test
	public void testTarFile() {
		String path = "G:/javawebCode/jsp/day11"; //要压缩的文件夹
		String tp = "d:/stu.tar"; //目标文件
		tarFile(path,tp);
	}
	/**
	 * 解压
	 */
	@Test
	public void testUnTarFile() {
		String path = "d:/"; //解压到
		File f = new File("d:/stu.tar.gz"); //要解压的目标文件
		unGzipFile(f); //解压GZIP文件成tar包
		 
		unTarFile(new File("d:/stu.tar"), path);  //解tar包
	}
	
	private void tarFile(String path,String tarFile) {
		TarArchiveOutputStream taos = null;
		try {
			File f = new File(path);
			int len = f.getParent().length();
			taos = new TarArchiveOutputStream(new FileOutputStream(tarFile));
			taos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
			tarFile(new File(path),taos,len);
			gzipFile(new File(tarFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(taos!=null) taos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void unTarFile(File file,String path) {
		TarArchiveInputStream tais = null;
		try {
			tais = new TarArchiveInputStream(new FileInputStream(file));
			TarArchiveEntry tae = null;
			while((tae=tais.getNextTarEntry())!=null) {
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
		}
	}
	
	private void unGzipFile(File file) {
		GZIPInputStream gis = null;
		FileOutputStream fos = null;
		try {
			gis = new GZIPInputStream(new FileInputStream(file));
			String path = file.getAbsolutePath();
			path = path.substring(0,path.lastIndexOf("."));
			System.out.println(path);
			fos = new FileOutputStream(path);
			IOUtils.copy(gis, fos);
			file.deleteOnExit();
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
	}
	
	private void gzipFile(File file) {
		GZIPOutputStream gos = null;
		FileInputStream fis = null;
		try {
			gos = new GZIPOutputStream(new FileOutputStream(file.getAbsolutePath()+".gz"));
			fis = new FileInputStream(file);
			IOUtils.copy(fis, gos);
			file.deleteOnExit();
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
		}
	}

	private void tarFile(File file, TarArchiveOutputStream taos,int len) {
		if(file.isDirectory()) { //如果是文件夹
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
