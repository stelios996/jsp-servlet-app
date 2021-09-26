package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
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
		UserDAO udao = new UserDAO();
		HttpSession session = request.getSession();
		String u_email = request.getParameter("email");
		String u_password = request.getParameter("password");
		session.setAttribute("user_email", null);
		
		if(u_email == "" || u_password == "") {
			session.setAttribute("loginStatus1", "NotOK");
			response.sendRedirect("./WelcomePage.jsp");
		}else{
			session.setAttribute("loginStatus1", "OK");
			User u = udao.login(u_email, u_password);
			if(u==null) {
				session.setAttribute("loginStatus2", "NotOK");
				response.sendRedirect("./WelcomePage.jsp");
			}else if( u.getEmail().equals("admin@gmail.com") ){
				session.setAttribute("user_email", u.getEmail());
				session.setAttribute("user_id", 1);
				session.setAttribute("loginStatus2", "OK");
				response.sendRedirect("./AdminPage.jsp");
			}else {
				session.setAttribute("user_email", u_email);
				session.setAttribute("user_id", u.getUserID());
				session.setAttribute("loginStatus2", "OK");
				response.sendRedirect("./HomePage.jsp");
			}
		}	
		
	}

}
