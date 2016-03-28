package org.wxh.user.auth;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wxh.basic.util.JsonUtils;

/**
 * 权限工具类
 * @author wxh
 *
 */
public class AuthUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthUtil.class);
	
	private static Properties prop;
	
	static {
		try {
			if( prop == null ) {
				prop = new Properties();
				prop.load( AuthUtil.class.getClassLoader().getResourceAsStream("auth.properties"));
			}
		} catch (IOException e) {
			logger.error( "获取配置文件失败，失败信息[{}]", e.getMessage() );
		}
	}
	
	
	/**
	 * 初始化系统的角色所访问的功能信息
	 * @return Map<角色，Set<所拥有的方法>> map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String,Set<String>> initAuth() {
		Map<String,Set<String>> auths = new HashMap<String, Set<String>>();
		try {
			String[] pnames = prop.getProperty("package").split(",");//获取所有要被权限控制的包路径
			for( String pname : pnames ) {
				String[] ps = getClassByPackage( pname );
				for(String p:ps) {
					//去除.class后缀，得到完整的包名+类名，如：org.wxh.topic.controller.ChannelController
					String pc = pname + "." + p.substring( 0, p.lastIndexOf(".class") );
					//得到类的class对象
					Class clz = Class.forName( pc );
					//过滤掉没有@AuthClass的类
					if( !clz.isAnnotationPresent( AuthClass.class ) ) continue;  
					//获取每个类中的方法，以此确定哪些角色可以访问哪些方法
					Method[] ms = clz.getDeclaredMethods();
					/**
					 * 遍历method来判断每个method上面是否存在相应的AuthMethd
					 * 如果存在就直接将这个方法存储到auths中，如果不存在就不存储（不存储就意味着该方法只能由超级管理员访问）
					 */
					for( Method m : ms ) {
						if( !m.isAnnotationPresent( AuthMethod.class ) ) continue;
						//如果存在就要获取这个Annotation
						AuthMethod am = m.getAnnotation( AuthMethod.class );
						String[] roles = am.role(); //获取该注解里面的所有角色
						for( String role : roles ) {
							Set<String> actions = auths.get( role );
							if( actions == null ) {
								actions = new HashSet<String>();//创建一个存储角色的权限信息的Set集合
								auths.put( role, actions );
							}
							actions.add( pc + "." + m.getName() ); //存入该权限信息
						}
					}
				}
			}
			return auths;
		} catch ( ClassNotFoundException e ) {
			logger.error( "初始化系统的角色所访问的功能信息失败，失败信息：[{}]",e.getMessage() );
		}
		return null;
	}
	
	/**
	 * 根据包名获取该包下的所有.class文件
	 * @param pname 包名（全名）
	 * @return 该包下的所有java类
	 */
	private static String[] getClassByPackage( String pname ) {
		//把org.wxh.topic.controller 替换成 org/wxh/topic/controller
		String pr = pname.replace( ".", "/" );  
		//获取绝对路径：/G:/apache-tomcat-7.0.6/webapps/cms/WEB-INF/classes/org/wxh/topic/controller/
		String pp = AuthUtil.class.getClassLoader().getResource( pr ).getPath();
		File file = new File( pp );
		//获取到出该包下的所有.class文件
		String[] fs = file.list(new FilenameFilter() {
			@Override
			public boolean accept( File dir, String name ) {
				if( name.endsWith( ".class" ) ) return true;
				return false;
			}
		});
		return fs;
	}
	
	public static void main(String[] args) {
		System.out.println( JsonUtils.map2String( initAuth() ) );
	}
}
