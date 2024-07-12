package com.study.dvd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form")
public class FormDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET 요청 들어옴~~");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String[] chks = req.getParameterValues("chk"); //chkbox 가 2개라 배열로 !
		String rdo = req.getParameter("rdo");
		System.out.println(username);
		System.out.println(password);
		
		for(String chk : chks) { // chks 배열 반복 돌리기
			System.out.println(chk);
		}
		
		System.out.println(rdo);
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println("응답데이터입니다!!");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST 요청 들어옴~~");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String[] chks = req.getParameterValues("chk");
		String rdo = req.getParameter("rdo");
		System.out.println(username);
		System.out.println(password);
		
		for(String chk : chks) { 
			System.out.println(chk);
		}
		
		System.out.println(rdo);
	}
	
}
