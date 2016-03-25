package org.wxh.TestAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnno {

	String hello() default "wxh";		
	
	String world();
	
}
