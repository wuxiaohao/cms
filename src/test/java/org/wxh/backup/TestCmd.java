package org.wxh.backup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

/**
 * 测试cmd命令、导入导出数据
 * @author wxh
 *
 */
public class TestCmd {

	/**
	 * 获取cmd命令的执行结果
	 */
	@Test
	public void testSimpleCmd() {
		try {
			String cmd = "cmd /c dir c:\\";
			Process proc = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream(),"utf-8"));
			String str = null;
			while((str=br.readLine())!=null) {
				System.out.println(str);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 导出cms数据库到d盘
	 */
	@Test
	public void testMySql() {
		try {
			String cmd = "cmd /c mysqldump -uroot -proot test";
			Process proc = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new FileWriter("d:/test.sql"));
			String str = null;
			while((str=br.readLine())!=null) {
				bw.write(str);
				bw.newLine();
			}
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 读取sql文件，导入数据库
	 */
	@Test
	public void testResume() {
		try {
			String cmd = "cmd /c mysql -uroot -proot cms";
			Process proc = Runtime.getRuntime().exec(cmd);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proc.getOutputStream()));
			BufferedReader br = new BufferedReader(new FileReader("d:/test.sql"));
			String str = null;
			while((str=br.readLine())!=null) {
				bw.write(str);
				bw.newLine();
			}
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
