package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import dao.FriendsDAO;
import model.Friend;

/**
 * Servlet implementation class AddFriend
 */
@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//pairnw id apo parameter
		//bazw sto friends table user_id, id, status 1
		//redirect sto servletFriend me to id
		String id1 =(String) request.getParameter("id");
		int profID = Integer.parseInt(id1);
		
		HttpSession session = request.getSession();
		FriendsDAO fdao = new FriendsDAO();
		int userID = (int) session.getAttribute("user_id");
		fdao.insert(userID, profID);
		//fdao.insert(profID, userID);
		
		String Servl = "ServletFriend?id="+id1;
		request.getRequestDispatcher(Servl).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
