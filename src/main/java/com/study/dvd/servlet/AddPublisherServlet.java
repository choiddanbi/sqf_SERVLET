package com.study.dvd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.dvd.dao.PublisherDao;
import com.study.dvd.entity.Publisher;


@WebServlet("/publisher/add")
public class AddPublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/add_publisher.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String publisherName = req.getParameter("publisher"); // req.getParameter("publisher") 는 add_publisher.jsp 의 input에 있는 publisher!!!!
		Publisher publisher = Publisher.builder().publisherName(publisherName).build(); // input 에 있는 publisherName 값을 가지고 publisher 객체 생성
		boolean isSuccess = PublisherDao.save(publisher) > 0;
		req.setAttribute("isSuccess", isSuccess);
		req.getRequestDispatcher("/WEB-INF/views/add_publisher_result.jsp").forward(req, resp);
	}
}
