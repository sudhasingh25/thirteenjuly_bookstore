package com.bookstore.services;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	BookDao bookDao;
	CategoryDao categoryDao;
	
	
	public BookService(HttpServletRequest request, HttpServletResponse response
			) {
		super();
		this.request = request;
		this.response = response;
		entityManagerFactory = Persistence.createEntityManagerFactory("Bookstore");
		entityManager = entityManagerFactory.createEntityManager();
		bookDao = new BookDao(entityManager);
		categoryDao=new CategoryDao(entityManager);
	}



	public void listBooks() throws ServletException, IOException {
		List<Book> listBook=bookDao.listAll();
		request.setAttribute("listBook", listBook);
		
		RequestDispatcher dis=request.getRequestDispatcher("list_book.jsp");
		dis.forward(request, response);
	}



	public void createBook() throws ServletException, IOException {
		List<Category> listCategory=categoryDao.listAll();
		request.setAttribute("listCategory", listCategory);
		
		String newPage="book_form.jsp";
		RequestDispatcher dis=request.getRequestDispatcher(newPage);
		dis.forward(request, response);
	}

}
