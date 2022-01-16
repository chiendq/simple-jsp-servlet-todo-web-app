package com.chiendq.servlet;

import com.chiendq.dao.ITaskDAO;
import com.chiendq.dao.impl.TaskDAOImpl;
import com.chiendq.entities.Item;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CreatServlet
 */
@WebServlet(urlPatterns = {"/create-controller"})
public class CreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CreateController() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String des = request.getParameter("description");
		System.out.println(des);
		Item item = new Item(des, 0);
		ITaskDAO taskDAO = new TaskDAOImpl();
		taskDAO.create(item);
		System.out.println("Create successfully");
		response.sendRedirect(request.getContextPath()+"/home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
