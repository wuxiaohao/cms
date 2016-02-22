package org.wxh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestComparator {
	
	public static void main(String[] args) {
		List<Person> list = new ArrayList<Person>();
		list.add( new Person(21,"axh1") );
		list.add( new Person(22,"bxh1") );
		list.add( new Person(25,"cxh1") );
		list.add( new Person(24,"dxh1") );
		list.add( new Person(23,"exh1") );
		list.add( new Person(29,"fxh1") );
		list.add( new Person(29,"gxh1") );
		list.add( new Person(26,"hxh1") );
		list.add( new Person(20,"ixh1") );
		
		Collections.sort(list,new Comparator<Person>() {
			//首先比较年龄，如果年龄相同，则比较名称
			@Override
			public int compare(Person o1, Person o2) {
				//int:前面参数-后面参数，则升序，反之降序
				//String:前面参数.compareTo(后面参数)，则升序，反之降序
				int flag = o2.getAge() - o1.getAge();//降序
				return flag == 0 ?  ( o2.getName().compareTo(o1.getName()) ) : flag;
			}
			
		});
		
		for(Person p : list) {
			System.out.println(p.getAge() + "--"+ p.getName());
		}
		
	}
	
}

class Person {
	private int age;
	private String name;
	
	Person(int age,String name) {
		this.age = age;
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}