package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {
	private static Logger logger = Logger.getLogger(RequestHelper.class);
	private static EmployeeService eserv = new EmployeeService(new EmployeeDAO());
	private static ObjectMapper om = new ObjectMapper();
	
	
	
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		logger.info("User attempted to login with username " + username);
		
		Employee e = eserv.confirmLogin(username, password);
		
		if (e != null) {
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println(om.writeValueAsString(e));
		}
		else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");
			response.setStatus(204);
		}
	}
	
public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		
		List<Employee> allEmployees = eserv.findAll();
		
		String jsonString = om.writeValueAsString(allEmployees);
		
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}
	
public static void processError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("error.html").forward(request, response);
		
	}
}
