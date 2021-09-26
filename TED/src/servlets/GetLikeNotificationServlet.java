package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import dao.ArticleDAO;
import dao.ArticleLikeDAO;
import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class GetLikeNotificationServlet
 */
@WebServlet("/GetLikeNotificationServlet")
public class GetLikeNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLikeNotificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String ids = request.getParameter("id");
		int id = Integer.parseInt(ids);
		
		ArticleDAO adao = new ArticleDAO();
		List<Integer> articleIDlist = new ArrayList<Integer>();
		articleIDlist = adao.getUserArticles(id);
		
		if(articleIDlist!=null) {
			ArticleLikeDAO al = new ArticleLikeDAO();
			List<Integer> userIDlist = new ArrayList<Integer>();
			userIDlist = al.getUserIDs(articleIDlist);
			
			UserDAO udao = new UserDAO();
			List<User> users = new ArrayList<User>();
			users = udao.getForNotification(userIDlist);
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			String listToJSON = mapper.writeValueAsString(users);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(listToJSON);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
