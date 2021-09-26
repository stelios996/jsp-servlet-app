package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import dao.ProfileDAO;
import dao.UserDAO;
import model.Profile;
import model.User;

/**
 * Servlet implementation class ServletProfile
 */
@WebServlet("/ServletProfile")
public class ServletProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfile() {
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
		int profID = (int)session.getAttribute("user_id");
		ProfileDAO pdao = new ProfileDAO();
		Profile p = pdao.find(profID);
		
		UserDAO udao = new UserDAO();
		int pID = p.getProfileID();
		String imageName = udao.getProfImageName(pID);
		//String imagePath = "file:///C:/UsersContent/User"+pID+"/"+imageName;
		//String imagePath = getServletContext().getRealPath("/")+"UsersContent/User"+pID+"/"+imageName;
		String imagePath = "UsersContent/User"+pID+"/"+imageName;
		session.setAttribute("p_ImagePath", imagePath);
		
		if(p!=null) {
			request.setAttribute("p_name", p.getName());
			request.setAttribute("p_surname", p.getSurname());
			request.setAttribute("p_age", p.getAge());
			request.setAttribute("p_address", p.getAddress());
			request.setAttribute("p_telephone", p.getTelephone());
			request.setAttribute("p_email", p.getEmail());
			request.setAttribute("p_workHistory", p.getWorkHistory());
			request.setAttribute("p_resTraining", p.getReasearchTraining());
			request.setAttribute("p_education", p.getEducation());
			request.setAttribute("p_profqual", p.getProfQual());
			request.setAttribute("p_computer", p.getComputer());
			request.setAttribute("p_other", p.getOtherInfo());
			request.setAttribute("p_interests", p.getInterests());
			request.getRequestDispatcher("./ProfilePage.jsp").forward(request, response);
		}else {
			request.setAttribute("p_name", "null");
			request.setAttribute("p_surname", "null");
			request.setAttribute("p_age", 0);
			request.setAttribute("p_address", "null");
			request.setAttribute("p_telephone", "null");
			request.setAttribute("p_email", "null");
			request.setAttribute("p_workHistory", "null");
			request.setAttribute("p_resTraining", "null");
			request.setAttribute("p_education", "null");
			request.setAttribute("p_profqual", "null");
			request.setAttribute("p_computer", "null");
			request.setAttribute("p_other", "null");
			request.setAttribute("p_interests", "null");
			request.getRequestDispatcher("./ProfilePage.jsp").forward(request, response);
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
