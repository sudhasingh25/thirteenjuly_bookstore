package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookstore.entity.Category;
import com.bookstore.services.CategoryService;
import com.bookstore.dao.CategoryDao;

@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public HomeServlet() {
        super();
    }
    
     
    EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("Bookstore");
    EntityManager entityManager=entityManagerFactory.createEntityManager();
    

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDao categoryDao=new CategoryDao(entityManager);
		List<Category> listCategory=categoryDao.listAll();
		request.setAttribute("listCategory", listCategory);
		
		String homepage="frontend/index.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}

}
