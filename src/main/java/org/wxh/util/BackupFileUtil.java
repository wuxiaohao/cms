package org.wxh.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.wxh.sys.model.BackupFile;

/**
 * 备份的工具类
 * 单例
 * @author wxh
 *
 */
public class BackupFileUtil {
	private static BackupFileUtil util;
	private String backupFile; //备份的文件夹
	private static String realPath;
	private String database;
	private String username;
	private String password;
	private List<String> backupFiles;
	private final static String DATABASE_NAME = "database";
	private final static String BACKUP_NAME = "backup";
	
	private BackupFileUtil() throws IOException{
		Properties prop = new Properties();
		prop.load(BackupFileUtil.class.getClassLoader().getResourceAsStream("backup.properties"));
		database = prop.getProperty("database");
		username = prop.getProperty("database_username");
		password = prop.getProperty("database_password");
		backupFile = prop.getProperty("backupFile");
		File bf = new File(realPath+File.separator+backupFile);
		if(!bf.exists()) bf.mkdirs();
		backupFiles = new ArrayList<String>();
		//添加要备份或者要恢复的文件夹
		String fs = prop.getProperty("file");
		String[] fas = fs.split(",");
		for(String f:fas) {
			backupFiles.add(f);
		}
		
	}
	
	public static BackupFileUtil getInstance(String realPath) {
		try {
			BackupFileUtil.realPath = realPath;
			if(util==null) util = new BackupFileUtil();
			return util;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取所有备份文件
	 * @return
	 */
	public List<BackupFile> listBackups() {
		//得到所有文件
		File [] fs = new File(realPath+File.separator+backupFile).listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				//过滤掉所有文件夹
				if(pathname.isDirectory())
					return false;
				return true;
			}
		});
		List<BackupFile> bs = new ArrayList<BackupFile>();
		BackupFile bf = null;
		for(File f:fs) {
			bf = new BackupFile();
			bf.setName(f.getName());
			String sizeStr = FileSizeUtil.convertFileSize(f.length());
			bf.setSize(sizeStr);
			bf.setTime(new Date(f.lastModified()));
			bf.setFiletype(f.getName().substring(f.getName().lastIndexOf(".")+1));
			bs.add(bf);
		}
		Collections.sort(bs);
		return bs;
	}
	/**
	 * 备份
	 * @param name
	 */
	public void backup(String name) {
		String bp = realPath+File.separator+backupFile+File.separator+BACKUP_NAME;
		try {
			//0、创建目标文件夹对象
			File bpf = new File(bp);
			bpf.mkdirs();
			//1、导出数据库到创建的目标文件夹中
			MySQLUtil msu = MySQLUtil.getInstance();
			msu.setCfg(DATABASE_NAME, bp, database, username, password);
			msu.backup();
			//2、将要备份的文件夹拷贝到目标文件夹中
			for(String f:backupFiles) {
				String src = realPath+File.separator+f; //原文件夹的路径+原文件夹名称
				String dest = bp+f; //目标文件夹路径+原文件夹名称
				//将原文件夹拷贝到目标文件夹
				FileUtils.copyDirectory(new File(src), new File(dest)); 
			}
			//3、压缩成.gzip文件
			TarAndGzipUtil tagu = TarAndGzipUtil.getInstance();
			tagu.tarFile(bp,realPath+File.separator+backupFile+File.separator+new Date().getTime()+"_"+name+".tar");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				//删除目标文件夹
				FileUtils.deleteDirectory(new File(bp)); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 恢复备份文件
	 * @param name
	 */
	//恢复的name就是整个文件的名称
	public void resume(String name) {
		String op = realPath+File.separator+backupFile+File.separator+BACKUP_NAME;
		try {
			//1、将文件进行解压缩
			String fp = realPath+File.separator+backupFile+File.separator+name;
			TarAndGzipUtil tagu = TarAndGzipUtil.getInstance();
			tagu.unTarFile(new File(fp), realPath+File.separator+backupFile);
			
			//2、拷贝并且覆盖相应的文件夹
			for(String f:backupFiles) {
				//先删除原有的文件夹
				String src = op+f;
				String dest = realPath+File.separator+f;
				File dfd = new File(dest);
				if(!dfd.exists()) dfd.mkdirs();
				FileUtils.deleteDirectory(dfd);
				FileUtils.copyDirectory(new File(src), dfd);
			}
			//3、恢复数据库
			/*MySQLUtil msu = MySQLUtil.getInstance();
			msu.setCfg(DATABASE_NAME, op, database, username, password);
			msu.resume();*/
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				FileUtils.deleteDirectory(new File(op));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 要删除的文件的名称
	 * @param name
	 */
	public void delete(String name) {
		File f = new File(realPath+File.separator+backupFile+File.separator+name);
		f.delete();
	}
}
