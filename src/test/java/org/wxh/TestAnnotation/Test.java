package org.wxh.TestAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Test {
	
	@MyAnno(world={"wxh1","wxh2"})
	public void output(String hh) {
		System.out.println(hh);
	}
	
	
	public static void main(String[] args) throws Exception {
		
		Class<Test> test = Test.class;
		Method method = test.getMethod("output", String.class);
		if(method.isAnnotationPresent(MyAnno.class)) {
			MyAnno anno = method.getAnnotation(MyAnno.class);
			String[] str = anno.world();
			for(String s : str) {
				System.out.println(s);
			}
 		}
		/*Annotation[] annos = method.getAnnotations();
		for(Annotation a : annos) {
			System.out.println(a.annotationType());
		}*/
	}

}
