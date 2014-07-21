package com.example.CommunityOutreach.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.CommunityOutreach.data.ArticleManager;
import com.example.CommunityOutreach.model.Article;

/**
 * Servlet implementation class DesktopLatestArticle
 */
@WebServlet("/DesktopLatestArticle")
public class DesktopLatestArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DesktopLatestArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArticleManager am = new ArticleManager();
		List<Article> artList = am.retrieveAllPendingLatestArticles();
		request.setAttribute("artList", artList);
		
		RequestDispatcher rd = request.getRequestDispatcher("desktopLatestArticle.jsp");
		rd.forward(request,response);
	}

}
