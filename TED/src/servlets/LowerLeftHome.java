package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import dao.DAO;
import dao.FriendsDAO;
import dao.ProfileDAO;
import model.Profile;

/**
 * Servlet implementation class LowerLeftHome
 */
@WebServlet("/LowerLeftHome")
public class LowerLeftHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LowerLeftHome() {
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
		
		FriendsDAO fdao = new FriendsDAO();
		List<Integer> idlist = fdao.getFriendIDs(id);
		ProfileDAO pdao = new ProfileDAO();
		List<Profile> plist = pdao.getFriendProfiles(idlist);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String listToJSON = mapper.writeValueAsString(plist);
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
