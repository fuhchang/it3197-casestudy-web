package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.model.Article;

/**
 * Servlet implementation class DesktopFeedbackSelectedArticle
 */
@WebServlet("/DesktopFeedbackSelectedArticle")
public class DesktopFeedbackSelectedArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DesktopFeedbackSelectedArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int articleID=Integer.parseInt(request.getParameter("id"));
		
		ArticleManager am = new ArticleManager();
		Article articleDetail = am.retrieveArticle(articleID);
	/*	for(int i=0; i<artList.size();i++){
			artList.get(i).getTitle();
			System.out.println();
			System.out.println(artList.get(i).getTitle());
		}*/
		
		response.getWriter().print(articleDetail.getTitle());
	//	request.setAttribute("artList", articleDetail);
		String title = articleDetail.getTitle();
		String user = articleDetail.getUserNRIC();
		String content = articleDetail.getContent();
		String location = articleDetail.getLocation();
		Date articleDate = articleDetail.getDateTime();
		DateFormat df = new SimpleDateFormat("E, dd MMMM yyyy - hh:mm a");
		String articleSubmittedDate = df.format(articleDate);
		// Print what date is today!
		System.out.println("Article Date: " + articleSubmittedDate);
		String articleUserName = articleDetail.getArticleUser();
		double dbLat = articleDetail.getDbLat();
		double dbLon = articleDetail.getDbLon();
		
		//System.out.println(title);
		request.setAttribute("idArticle", articleID);
		request.setAttribute("articleTitle", title);
		request.setAttribute("articleUserName", articleUserName);
		request.setAttribute("articleContent", content);
		request.setAttribute("articleLocation", location);
		request.setAttribute("articleDate", articleSubmittedDate);
		request.setAttribute("dbLat", dbLat);
		request.setAttribute("dbLon", dbLon);
		RequestDispatcher rd = request.getRequestDispatcher("desktopFeedbackSelectedArticle.jsp");
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
