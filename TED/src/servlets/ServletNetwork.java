package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import dao.DAO;
import dao.FriendsDAO;
import dao.ProfileDAO;
import dao.UserDAO;
import model.Friend;
import model.Profile;
import model.User;

/**
 * Servlet implementation class ServletNetwork
 */
@WebServlet("/ServletNetwork")
public class ServletNetwork extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNetwork() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("user_id");
		
		FriendsDAO fdao = new FriendsDAO();
		List<Integer> idlist = fdao.getFriendIDs(id);
		
		ProfileDAO pdao = new ProfileDAO();
		List<Profile> plist = pdao.getFriendProfiles(idlist);
		request.setAttribute("plist", plist);
		
		List<String> imagelist = new ArrayList<String>();
		UserDAO udao = new UserDAO();
		for(int i=0; i<idlist.size(); i++) {
			String imageName = udao.getProfImageName(idlist.get(i));
			String imagePath= "UsersContent/User"+idlist.get(i)+"/"+imageName;
			imagelist.add(imagePath);
		}
		request.setAttribute("imagelist", imagelist);
		
		request.getRequestDispatcher("./NetworkPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
