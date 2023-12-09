package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.JobDAO;
import com.entity.Jobs;
@WebServlet("/add_job")
public class AddPostServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String ti=req.getParameter("title");
			String loc=req.getParameter("location");
			String categ=req.getParameter("category");
			String stat=req.getParameter("status");
			String des=req.getParameter("desc");
			
			Jobs j =new Jobs();
			j.setTitle(ti);
			j.setLocation(loc);
			j.setCategory(categ);
			j.setStatus(stat);
			j.setDescription(des);
			
			HttpSession session =req.getSession();
			JobDAO dao =new JobDAO(DBConnect.getConn());
				boolean f=dao.addJobs(j);
				if(f) {
					session.setAttribute("succMsg","job posted sucessfully");
					resp.sendRedirect("add_job.jsp");
				}
				else {
					session.setAttribute("succMsg","something wrong on server");
					resp.sendRedirect("add_job.jsp");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
