import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.CategoryDao;
import com.bookstore.dao.UsersDao;
import com.bookstore.entity.Category;

public class TestCategoryDao {


	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;
	public static CategoryDao categoryDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory=Persistence.createEntityManagerFactory("Bookstore");
		entityManager=entityManagerFactory.createEntityManager();
		categoryDao=new CategoryDao(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testCreateCategory() {
		Category cat=new Category("EEE");
		Category newcat=categoryDao.create(cat);
		assertNotNull(newcat);
	}

	@Test
	public void testUpdateCategory(){
		
		Category cat=new Category("MMM");
		cat.setCategoryId(22);
		Category newcat=categoryDao.update(cat);
		assertEquals(cat.getCategoryId(),newcat.getCategoryId());
	}
	
	@Test
	public void testGet(){
		int catId=22;
		Category cat=categoryDao.get(catId);
		assertNotNull(cat);
	}
	
	@Test
	public void testListAllCategory(){
		List<Category> listCat=categoryDao.listAll();
		assertTrue(listCat.size()==7);
	}
	
	@Test
	public void testDeleteCategory(){
		categoryDao.delete(22);
		Category cat=categoryDao.get(22);
		assertNull(cat);
	}
	
	@Test
	public void testCountCategory(){
		long totalCat=categoryDao.count();
		assertEquals(6, totalCat);
	}
}
