package org.wxh.user.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 是否进行权限控制
 * <pre>
 * 说明：
 * 只有在Controller类上注入该注解，才能进行权限的控制
 * 1、如果controller的url以/admin开头，但没有注入该注解，则该controller表示为只有超级管理员才能访问
 * 2、如果controller的url不以/admin开头，但没有注入该注解，则表示可以被所有角色访问
 * 3、如果controller的url以/admin开头，也注入该注解，则表示可以被权限控制
 * </pre>
 * @author wxh
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthClass {
	/**
	 * 说明：
	 * 1、如果value为admin就表示这个类只能超级管理员访问（默认值）
	 * 2、如果value为login表示这个类中的方法，某些可能为相应的角色可以访问
	 * @return
	 */
	public String value() default "admin";
	
}

/**
 * java中四个元注解说明：
 * @interface用来声明一个注解；
 * @Retention用来声明注解的保留策略
 * @Target用来声明注解可以被添加在哪些类型的元素上，如类型、方法和域等。
 * @Inherited：说明子类可以继承父类中的该注解
 * @Document：说明该注解将被包含在javadoc中
 * 
 * 具体参数：
 * @Retention：注解的保留位置　　　　　　　　　
　　　　   @Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
　　　　   @Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
　　　　   @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
	
	@Target:注解的作用目标
　　　　@Target(ElementType.TYPE)   //接口、类、枚举、注解
　　　　@Target(ElementType.FIELD) //字段、枚举的常量
　　　　@Target(ElementType.METHOD) //方法
　　　　@Target(ElementType.PARAMETER) //方法参数
　　　　@Target(ElementType.CONSTRUCTOR)  //构造函数
　　　　@Target(ElementType.LOCAL_VARIABLE)//局部变量
　　　　@Target(ElementType.ANNOTATION_TYPE)//注解
　　　　@Target(ElementType.PACKAGE) ///包   
	
	另外，每个方法实际上是声明了一个配置参数，方法名=参数名，返回值类型=参数的类型，
 	可以通过default来声明参数的默认值；
 * 
 */
