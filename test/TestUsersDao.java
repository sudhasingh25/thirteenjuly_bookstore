import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.UsersDao;
import com.bookstore.entity.Users;


public class TestUsersDao {

	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;
	public static UsersDao userDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory=Persistence.createEntityManagerFactory("Bookstore");
		entityManager=entityManagerFactory.createEntityManager();
		userDao=new UsersDao(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testCreateUsers() {
		Users user=new Users();
		user.setEmail("tannu123@gmail.com");
		user.setFullName("tannu");
		user.setPassword("tannu123");
		
		Users user1=userDao.create(user);
		assertTrue(user1.getUserId()>0);
	}
	
	@Test(expected=PersistenceException.class)
	public void testCreateUsersFieldNotSet() {
		Users user=new Users();
		Users user1=userDao.create(user);
	}
	
	@Test
	public void tetUpdateUsers(){
		Users user=new Users();
		user.setUserId(40);
		user.setEmail("ritika123@gmail.com");
		user.setFullName("ritika dev");
		user.setPassword("ritika123");
		user=userDao.update(user);
		String expected="ritika dev";
		String actual=user.getFullName();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCheckLoginSuccess(){
		String email="nanu123@gmail.com";
		String password="nanu123";
		
		boolean result=userDao.checkLogin(email, password);
		
		assertTrue(result);
	}
	
	@Test
	public void testCheckLoginFail(){
		String email="nanu123@gmail.com";
		String password="KKK123";
		
		boolean result=userDao.checkLogin(email, password);
		
		assertFalse(result);
	}

	@Test
	public void testFindByEmail() {
		String email="tannu123@gmail.com";
		Users user=userDao.findByEmail(email);
		assertNotNull(user);
	}
	
	@Test
	public void testDeleteUsers(){
		Integer id=28;
		userDao.delete(id);
		Users user=userDao.get(id);
		assertNull(user);
	}

	@Test(expected=EntityNotFoundException.class)
	public void testDeleteUsersNonExist(){
		Integer id=22;
		userDao.delete(id);
		
	}
	
	@Test
	public void testListAll(){
		List<Users> list=userDao.listAll();
		
		assertTrue(list.size()>0);
	}

	
	@Test
	public void testGetUserFound() {
		Integer id=9;
		Users user=userDao.get(id);
		assertNotNull(user);
	}
	
	@Test
	public void testGetUserNotFound() {
		Integer id=1;
		Users user=userDao.get(id);
		assertNull(user);
	}

	@Test
	public void testCount() {
		long users=userDao.count();
		assertEquals(26,users);
	}

	@Test
	public void testGetObject1() {
		fail("Not yet implemented");
	}

	

}
