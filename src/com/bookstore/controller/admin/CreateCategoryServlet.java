package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.services.CategoryService;

@WebServlet("/admin/create_category")
public class CreateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CreateCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryService categoryService=new CategoryService(request, response);
		categoryService.createCategory();
	}

}
