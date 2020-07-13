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

import com.bookstore.dao.CategoryDao;
import com.bookstore.dao.UsersDao;
import com.bookstore.entity.Category;
import com.bookstore.entity.Users;

public class CategoryService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	EntityManager entityManager;
	EntityManagerFactory entityManagerFactory;
	CategoryDao categoryDao;
	
	public CategoryService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		entityManagerFactory=Persistence.createEntityManagerFactory("Bookstore");
		entityManager=entityManagerFactory.createEntityManager();
		categoryDao=new CategoryDao(entityManager);
	}
	
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listcat=categoryDao.listAll();
		request.setAttribute("listcat", listcat);
		request.setAttribute("message", message);
		String page="list_category.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	public void createCategory() throws ServletException, IOException {
		String name=request.getParameter("name");
		Category exist=categoryDao.findByName(name);
		if(exist!=null ){
			String message="Could not create Category with name "+name;
			request.setAttribute("message", message);
			RequestDispatcher dis=request.getRequestDispatcher("message.jsp");
			dis.forward(request, response);
		}else{
		Category newcat=new Category(name);
		categoryDao.create(newcat);
		String message="New category created successfully";
		listCategory(message);
		}
	}

	public void editCategory() throws ServletException, IOException {
		int categoryId=Integer.parseInt(request.getParameter("id"));
		Category cat=categoryDao.get(categoryId);
		request.setAttribute("cat", cat);
		RequestDispatcher dis=request.getRequestDispatcher("category_form.jsp");
		dis.forward(request, response);

	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		String name=request.getParameter("name");
		Category catId=categoryDao.get(categoryId);
		Category exist=categoryDao.findByName(name);
		if(exist!=null && catId.getCategoryId()!=exist.getCategoryId()){
			String message="Category name "+name+" already exist";
			request.setAttribute("message", message);
			RequestDispatcher dis=request.getRequestDispatcher("message.jsp");
			dis.forward(request, response);
		}else{
			
			Category cat=new Category(categoryId,name);
			categoryDao.update(cat);
			String message="Category has been updated successfully";
			listCategory(message);
		}
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId=Integer.parseInt(request.getParameter("id"));
		categoryDao.delete(categoryId);
		String message="Category has been deleted successfully";
		listCategory(message);
	}
	
	
}
