package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dao.FriendsDAO;
import model.Friend;

/**
 * Servlet implementation class FriendRequestHandler
 */
@WebServlet("/FriendRequestHandler")
public class FriendRequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendRequestHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String s_user1 = request.getParameter("user1");
		String s_user2 = request.getParameter("user2");
		String s_status = request.getParameter("status");
		
		int user1 = Integer.parseInt(s_user1);
		int user2 = Integer.parseInt(s_user2);
		int status = Integer.parseInt(s_status);
		
		if(status == 0) { //accept
			FriendsDAO fdao1 = new FriendsDAO();
			fdao1.acceptFriendRequest(user1, user2);
		}else if(status == 1) { //decline
			FriendsDAO fdao2 = new FriendsDAO();
			fdao2.declineFriendRequest(user1, user2);
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
