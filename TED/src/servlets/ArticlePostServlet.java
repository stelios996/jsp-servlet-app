package servlets;

//import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ArticleDAO;
import model.Article;

/**
 * Servlet implementation class ArticlePostServlet
 */
@WebServlet("/ArticlePostServlet")
@MultipartConfig
public class ArticlePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticlePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		String a_text = request.getParameter("postText");
		
		String a_picName = null;
		Part a_pic = null;
		try {
			a_pic = request.getPart("postImage");
			for (String content : a_pic.getHeader("content-disposition").split(";")) {
		        if (content.trim().startsWith("filename")) {
		            a_picName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
		            //session.setAttribute("apicName", "Mphke apicNmae");
		        }
		    }
		}catch(Exception e) {
			//session.setAttribute("exceptionImage", "exceptionImage");
			e.printStackTrace();
		}
		
		String a_videoName = null;
		Part a_video = null;
		try {
			a_video = request.getPart("postVideo");
			for (String content : a_video.getHeader("content-disposition").split(";")) {
		        if (content.trim().startsWith("filename")) {
		            a_videoName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
		            //session.setAttribute("avideoName", "Mphke avideoNmae");
		        }
		    }
		}catch(Exception e) {
			//session.setAttribute("exceptionVideo", "exceptionVideo");
			e.printStackTrace();
		}
		
		int userID = (int)session.getAttribute("user_id");
			
		String contextPath = getServletContext().getRealPath("/");
		String folderPath = contextPath+"/UsersContent";
		File usersContent = new File(folderPath);
		if(!usersContent.exists()) {
			try {
				usersContent.mkdir();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		String userFolder = folderPath+"/User"+userID;
		File userC = new File(userFolder);
		if(!userC.exists()) {
			try {
				userC.mkdir();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		session.setAttribute("1", "1oxi");
		if(!a_picName.equals("")) {
			session.setAttribute("1", "1nai");
			OutputStream out = null;
			InputStream filecontent = null;
			out = new FileOutputStream(new File(userFolder + "/"+ a_picName));
			filecontent = a_pic.getInputStream();
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.close();
			filecontent.close();
		}
		
		session.setAttribute("2", "2oxi");
		if(!a_videoName.equals("")) {
			session.setAttribute("2", "2nai");
			OutputStream out = null;
			InputStream filecontent = null;
			out = new FileOutputStream(new File(userFolder + "/"+ a_videoName));
			filecontent = a_video.getInputStream();
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.close();
			filecontent.close();
		}
		
		/*if(a_picName.equals("")) {
			a_picName = "kenoImage";
		}
		if(a_videoName.equals("")) {
			a_videoName = "kenoVideo";
		}*/
		
		session.setAttribute("a_picName", "//"+a_picName+"\\");
		session.setAttribute("a_videoName", "//"+a_videoName+"\\");
		session.setAttribute("example", "//"+""+"\\");
		
		
		ArticleDAO adao = new ArticleDAO();
		int aID = adao.getNumberOfArticles();
		
		Article a = new Article();
		a.setIdArticles(aID+1);
		a.setText(a_text);
		a.setImage(a_picName);
		a.setVideo(a_videoName);
		a.setUserID(userID);
		
		adao.postArticle(a);
		response.sendRedirect("./HomePage.jsp");
	}

}
