package com.study.dvd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello") // 주소
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String name = "김준일";
//		
//		resp.setContentType("text/html");
//		resp.setCharacterEncoding("utf-8");
//		
//		resp.getWriter().println(""
//				+ "<html>"
//				+ "<head>"
//				+ "<title>hello</title>"
//				+ "</head>"
//				+ "<body>"
//				+ "<h1>Hello Servlet!!!</h1>"
//				+ "<h2>" + name +"</h2>"
//				+ "</body>"
//				+ "</html>");
//	}
//	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		
		System.out.println(name);
		System.out.println(age);
		
		req.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(req, resp);
	}
}
