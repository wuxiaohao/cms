package org.wxh.user.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 该注解用来确定哪些方法由哪些角色访问(需要配合@AuthClass使用)
 * <pre>
 * 说明：(属性只有一个role)
 * 1、如果role的值为base表示这个方法可以被所有的登录用户访问（不设置默认为base）
 * 2、如果为ROLE_PUBLISH表示只能为文章发布人员访问，以此类推
 * 3、如果类上注入@AuthClass，而方法中没有@AuthMethod注解，则意味着该方法只能由超级管理员访问
 * 4、如果类上没有注入@AuthClass，则该注解无效
 * </pre>
 * @author wxh
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthMethod {
	
	public String[] role() default "base";

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
　　　　   @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到,这个功能搭配反射是非常强大的。
	
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
