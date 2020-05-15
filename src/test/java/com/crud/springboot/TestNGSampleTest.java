package com.crud.springboot;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crud.springboot.model.User;

@RunWith(Suite.class)
@SpringBootTest(classes=UserCrudServiceApplication.class)
public class TestNGSampleTest extends AbstractTestNGSpringContextTests {
	@BeforeClass
	public static void createUser() {
		System.out.println("Run before class>>>>>>>>>");
		
	}
	
	@BeforeMethod
	public void beformeth() {
		System.out.println("Run before method>>>>>>>>>");
	}
	
	@Test
	public void simpleTest() {
		System.out.println("Run TestNG Test Class>>>>>>>>>>>>>>>>>>>");
		Assert.assertEquals((5*2), 10);
	}
	
	@AfterMethod
	public void aftermeth() {
		System.out.println("Run after method>>>>>>>>>");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("Run after class>>>>>>>>>");
	}
}
