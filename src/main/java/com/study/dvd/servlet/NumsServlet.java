package com.study.dvd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/nums")
public class NumsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		
		
		StringBuilder builder = new StringBuilder();
		builder.append("<html>");
		builder.append("<head>");
		builder.append("</head>");
		builder.append("<body>");
		builder.append("<ul>");
		
		for(int i = 0; i < 50; i++) {
			builder.append("<li>김준일" + ( i + 1 ) + "</li>");
		}
		
		builder.append("</ul>");
		builder.append("</body>");
		builder.append("</html>");
		
		resp.getWriter().println(builder.toString());
		
		
		
		/*String str = "";
		for (int i=0; i<50; i++) {
			str += "<li>" + "김준일" + (i+1) + "</li>";		
		}
		
		
		
		resp.getWriter().println(""
				+"<html>"
				+ "<head>"
				+ "<body>"
				+ "<ul>"
				+ str
				+ "</ul>"
				+ "</body>"
				+ "</head>"
				+ "</html>"
				
				);*/
	}

}
