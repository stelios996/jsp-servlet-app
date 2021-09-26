package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dao.DAO;
import dao.UserDAO;
import model.User;
import dao.FriendsDAO;
import model.Friend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Servlet implementation class NotificationServlet
 */
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id_req = request.getParameter("id");
		int id = Integer.parseInt(id_req);
		
		FriendsDAO fdao = new FriendsDAO();
		List<Friend> flist = fdao.searchRequests(id);
		List<Integer> user1ids = new ArrayList<Integer>();
		Iterator<Friend> itr = flist.iterator();
		while(itr.hasNext()) {
			Friend f = (Friend) itr.next();
			user1ids.add(f.getUSER1());
		}
		UserDAO udao = new UserDAO();
		List<User> ulist = udao.getForNotification(user1ids);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String listToJSON = mapper.writeValueAsString(ulist);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(listToJSON);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
