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

import com.bookstore.dao.UsersDao;
import com.bookstore.entity.Users;

public class UserService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	EntityManager entityManager;
	EntityManagerFactory entityManagerFactory;
	UsersDao userDao;
	
	
	public UserService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		entityManagerFactory=Persistence.createEntityManagerFactory("Bookstore");
		entityManager=entityManagerFactory.createEntityManager();
		userDao=new UsersDao(entityManager);
	}


	public void listUser(String message) throws ServletException, IOException {
		List<Users> listuser=userDao.listAll();
		request.setAttribute("listuser", listuser);
		request.setAttribute("message", message);
		String page="list_user.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	public void listUser() throws ServletException, IOException {
		listUser(null);
	}
	
	public void createUser() throws ServletException, IOException {
		String email=request.getParameter("mail");
		String fullname=request.getParameter("fullname");
		String password=request.getParameter("password");
		
		Users exist=userDao.findByEmail(email);
		
		if(exist!=null ){
			String message="Could not create a user "+email+" is already exist.";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}else{
			Users user=new Users(email,fullname,password);
			userDao.create(user);
			listUser("user has been created successfully");
		}
	}


	public void editUser() throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		Users user=userDao.get(id);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher=request.getRequestDispatcher("user_form.jsp");
		dispatcher.forward(request, response);
	}


	public void updateUser() throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("userId"));
		String email=request.getParameter("mail");
		String fullname=request.getParameter("fullname");
		String password=request.getParameter("password");
		
		Users user=userDao.get(id);
		
		Users exist=userDao.findByEmail(email);
		
		if(exist!=null && exist.getUserId()!=user.getUserId()){
			String message="Could not update a user with email "+email;
			request.setAttribute("message", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}else{
			Users newuser= new Users(id,email,fullname,password);
			userDao.update(newuser);
			String message="User has been updated successfully";
			listUser(message);
		}
	}


	public void deleteUser() throws ServletException, IOException {
		int userId=Integer.parseInt(request.getParameter("id"));
		//System.out.println("users id "+userId);
		userDao.delete(userId);
		String message="User has been deleted successfully";
		listUser(message);
	}


	public void login() throws ServletException, IOException {
		String useremail=request.getParameter("mail");
		String password=request.getParameter("password");
		
		boolean loginResult=userDao.checkLogin(useremail, password);
		if(loginResult){
			request.getSession().setAttribute("useremail", useremail);
			RequestDispatcher dis=request.getRequestDispatcher("/admin/");
			dis.forward(request, response);
		}else{
			String message="Login Fail!";
			request.setAttribute("message", message);
			RequestDispatcher dis=request.getRequestDispatcher("login.jsp");
			dis.forward(request, response);
		}
	}

}
