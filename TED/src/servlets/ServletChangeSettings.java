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
 * Servlet implementation class ServletChangeSettings
 */
@WebServlet("/ServletChangeSettings")
public class ServletChangeSettings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletChangeSettings() {
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
		String u_confirmPass = request.getParameter("confirmPassword");
		session.setAttribute("fieldStatus", "OK");
		session.setAttribute("passStatus", "OK");
		session.setAttribute("emailStatus", "OK");
		session.setAttribute("confirmPassStatus", "OK");
		//session.setAttribute("changeSetStatus", "OK");
		session.setAttribute("printStatus", "NotOK");
		
		String current_email = (String)session.getAttribute("user_email");
		if(u_email=="" && u_password=="" && u_confirmPass=="") {
			session.setAttribute("fieldStatus", "NotOK");
			response.sendRedirect("./SettingsPage.jsp");
		}
		else if((u_password=="" && u_confirmPass!="") || (u_password!="" && u_confirmPass=="")) {
			session.setAttribute("passStatus", "NotOK");
			response.sendRedirect("./SettingsPage.jsp");
		}
		//String current_email = (String)session.getAttribute("user_email");
		else if(u_email!="" && u_password=="") {
			if(udao.exists(u_email)) {
				session.setAttribute("emailStatus", "NotOK");
				response.sendRedirect("./SettingsPage.jsp");
			}else {
				//kalw apo userdao gia query email
				if(udao.changeSettings(current_email, u_email, null)==false) {
					session.setAttribute("changeSetStatus", "NotOK");
					session.setAttribute("printStatus", "OK");
					response.sendRedirect("./SettingsPage.jsp");
				}else {
					session.setAttribute("changeSetStatus", "OK");
					session.setAttribute("printStatus", "OK");
					session.setAttribute("user_email", u_email);
					response.sendRedirect("./SettingsPage.jsp");
				}
			}
			//response.sendRedirect("./SettingsPage.jsp");
		}else if(u_email!="" && u_password!="") {
			if(udao.exists(u_email)) {
				session.setAttribute("emailStatus", "NotOK");
				response.sendRedirect("./SettingsPage.jsp");
			}
			else if(u_password.equals(u_confirmPass)==false) {
				session.setAttribute("confirmPassStatus", "NotOK");
				response.sendRedirect("./SettingsPage.jsp");
			}
			//kalw apo userdao gia query email/pass
			else{
				if(udao.changeSettings(current_email, u_email, u_password)==false) {
					session.setAttribute("changeSetStatus", "NotOK");
					session.setAttribute("printStatus", "OK");
					response.sendRedirect("./SettingsPage.jsp");
				}else {
					session.setAttribute("changeSetStatus", "OK");
					session.setAttribute("printStatus", "OK");
					session.setAttribute("user_email", u_email);
					response.sendRedirect("./SettingsPage.jsp");
				}
			}
		}else if(u_email=="" && u_password!="") {
			if(u_password.equals(u_confirmPass)==false) {
				session.setAttribute("confirmPassStatus", "NotOK");
				response.sendRedirect("./SettingsPage.jsp");
			}
			//kalw apo userdao gia query trexon email/pass
			else {
				if(udao.changeSettings(current_email, current_email, u_password)==false) {
					session.setAttribute("changeSetStatus", "NotOK");
					session.setAttribute("printStatus", "OK");
					response.sendRedirect("./SettingsPage.jsp");
				}else {
					session.setAttribute("changeSetStatus", "OK");
					session.setAttribute("printStatus", "OK");
					response.sendRedirect("./SettingsPage.jsp");
				}
			}
		}
		
	}

}
