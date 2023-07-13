package com.example.database;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Test {

	
	public Test() {
		
	}
	
	public Test(Integer id, String name, String locatin) {
		super();
		this.id = id;
		this.name = name;
		this.locatin = locatin;
	}

	private Integer id;
	
	private String name;
	
	private String locatin;

	@Override
	public int hashCode() {
		return Objects.hash(id, locatin, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		return Objects.equals(id, other.id) && Objects.equals(locatin, other.locatin)
				&& Objects.equals(name, other.name);
	}
	
	
	
	 static class A implements Comparator<Test>{

		@Override
		public int compare(Test o1, Test o2) {
			return (o1.id ==  o2.id) && (o1.name ==  o2.name)?0:1;
		}
		
		
		
		
		
	}
	
	 static class B implements Comparator<Test>{

			@Override
			public int compare(Test o1, Test o2) {
				return (o1.name ==  o2.name)?0:1;
			}
			
			
			
			
			
		}
		
	
	
}
