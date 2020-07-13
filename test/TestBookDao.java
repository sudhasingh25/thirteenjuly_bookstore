import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class TestBookDao {

	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;
	public static BookDao bookDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory=Persistence.createEntityManagerFactory("Bookstore");
		entityManager=entityManagerFactory.createEntityManager();
		bookDao=new BookDao(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newbook=new Book();
		Category category=new Category("Programming");
		category.setCategoryId(9);
		newbook.setCategory(category);
		newbook.setAuthor("Lutz Mark");
		newbook.setDescription("Programming Python is a complete resource for novice computer programmers trying to learn the Python scripting language. The book deals with the basics, teaching readers about the data structures, the syntax and the Zen of Python. ");
		newbook.setIsbn("0987654321");
		newbook.setTitle("Programming Python");
		newbook.setPrice(67.98f);
		
		String imagePath="D:\\projectBookStore\\dummy-image\\programming-python.JPEG";
		byte[] imageBytes=Files.readAllBytes(Paths.get(imagePath));
		newbook.setImage(imageBytes);
		
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("05/21/2009");
		newbook.setPublishDate(publishDate);
		
		Book createBook=bookDao.create(newbook);
		assertTrue(createBook.getBookId()>0);
	}

	@Test
	public void testUpdateBook() throws IOException, ParseException  {
		Book newbook=new Book();
		newbook.setBookId(8);
		Category category=new Category("Programming");
		category.setCategoryId(9);
		newbook.setCategory(category);
		newbook.setAuthor("Lutz Mark");
		newbook.setDescription("Programming Python is a complete resource for novice computer programmers trying to learn the Python scripting language. The book deals with the basics, teaching readers about the data structures, the syntax and the Zen of Python. ");
		newbook.setIsbn("0987654321");
		newbook.setTitle("Programming Python");
		newbook.setPrice(67.98f);
		
		String imagePath="D:\\projectBookStore\\dummy-image\\programming-python.JPEG";
		byte[] imageBytes=Files.readAllBytes(Paths.get(imagePath));
		newbook.setImage(imageBytes);
		
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("05/21/2009");
		newbook.setPublishDate(publishDate);
		
		Book updateBook=bookDao.update(newbook);
		
		assertEquals(updateBook.getAuthor(),"Lutz Mark" );
	}

	@Test
	public void testDeleteBookSuccess(){
		Integer bookId=8;
		bookDao.delete(bookId);
		assertNull(bookDao.get(bookId));
	}

	@Test(expected=EntityNotFoundException.class)
	public void testDeleteBookFail(){
		Integer bookId=88;
		bookDao.delete(bookId);
	}
	
	@Test
	public void testGetBookSuccess(){
		Integer bookId=3;
		assertNotNull(bookDao.get(bookId));
	}
	
	@Test
	public void testGetBookFail(){
		Integer bookId=1;
		assertNull(bookDao.get(bookId));
	}
	
	@Test
	public void testListAll(){
		List<Book> listbook=bookDao.listAll();
		
		for(Book book:listbook){
			System.out.println(book.getTitle());
		}
		
		assertTrue(listbook.size()>0);
	}
	
	@Test
	public void testFindByTitleExist(){
		String book="Programming Python";
		
		Book exist=bookDao.findByTitle(book);
		
		assertNotNull(exist);
	}
	
	@Test
	public void testFindByTitleNotExist(){
		String book="Programming Python in bookstore";
		
		Book exist=bookDao.findByTitle(book);
		
		assertNull(exist);
	}
	
	@Test
	public void testCount(){
		long totalBook=bookDao.count();
		assertEquals(5, totalBook);
	}
}
