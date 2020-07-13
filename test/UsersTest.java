import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.bookstore.entity.Users;

public class UsersTest {

	public static void main(String[] args) {
		Users user=new Users();
		user.setEmail("ranurajput123@gmail.com");
		user.setFullName("Ranu Rajput");
		user.setPassword("ranu123");
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager;
		entityManagerFactory=Persistence.createEntityManagerFactory("Bookstore");
		entityManager=entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		System.out.println("user entered");
	}

}
