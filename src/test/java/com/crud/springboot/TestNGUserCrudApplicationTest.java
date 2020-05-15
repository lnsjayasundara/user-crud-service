package com.crud.springboot;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crud.springboot.model.User;
import com.crud.springboot.service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest(classes = UserCrudServiceApplication.class)
public class TestNGUserCrudApplicationTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserService userService;

	private User user;
	private String name = "TestNG Test Name 2";

	@BeforeClass
	public void createUser() {
		user = new User(name, 25, 38000.00);
	}

	@BeforeMethod
	public void beformeth() {
		System.out.println("Run before method>>>>>>>>>");
	}

	@Test
	public void simpleTest() {
		Assert.assertEquals((5 * 2), 10);
	}

	@Test
	public void testCreateUser() throws Exception {
		user = userService.saveOrUpdateUser(user);
		Assert.assertNotNull(user);
	}

	@Test  (dependsOnMethods = { "testCreateUser" }) 
	public void testFindUser() {
		Optional<User> result = (Optional<User>) userService.findUserById(user.getId());
		User u = result.get();
		Assert.assertEquals(u.getName(), name);
	}
	
	@Test(dependsOnMethods = "testFindUser")
	public void testUpdateUser() {
		user.setSalary(45000.00);
		user.setAge(26);
		try {
			user = userService.saveOrUpdateUser(user);
			Assert.assertEquals(user.getSalary(), 45000.00);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("User Update Failed!");
		}
		
		
	}
	
	@DataProvider(name="user-data-provider")
	public Object[][] userDataProvider(){
		return new Object[][] {{new User("Name1", 25, 36000.00),"Name1"}
		,{new User("Name2", 30, 38000.00),"Name2"}
		,{new User("Name3", 32, 42000.00),"Name3"}};
		
	}
	
	@Test(dataProvider = "user-data-provider",groups = {"multiple-user"})
	public void testBulkUserSave(User u,String name) {
		try {
			User testUser = userService.saveOrUpdateUser(u);
			Assert.assertEquals(testUser.getName(), name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
	

	@AfterClass
	public void afterClassMethod() {
		System.out.println("Run After class >>>>>>>>>>>>>>>>>>>");
	}

}
