package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;

import dao.DAO;
import dao.UserDAO;
import dao.ProfileDAO;
import model.User;
import model.Profile;
import java.util.List;

/**
 * Servlet implementation class ServletSignUp
 */
@WebServlet("/ServletSignUp")
@MultipartConfig
public class ServletSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSignUp() {
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
		UserDAO udao = new UserDAO();
		ProfileDAO pdao = new ProfileDAO();
		
		HttpSession session = request.getSession();
		String u_name = request.getParameter("name");
		String u_surname = request.getParameter("surname");
		String u_email = request.getParameter("email");
		String u_password = request.getParameter("password");
		String u_validation = request.getParameter("validation");
		String u_telephone = request.getParameter("telephone");

		Part u_pic = request.getPart("pic");
		String u_picName = null;
		for (String content : u_pic.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            u_picName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
		
		session.setAttribute("user_email", null);
		session.setAttribute("signupStatus", "OK");
		session.setAttribute("passvalidStatus","OK");
		session.setAttribute("existStatus", "OK");
			
		
		if(u_name=="" || u_surname=="" || u_email=="" || u_password=="" || u_validation==""
			|| u_telephone=="" || u_picName=="") {
			
			session.setAttribute("signupStatus", "NotOK");
			response.sendRedirect("./SignUpPage.jsp");

		}else if(u_password.equals(u_validation)==false) {

			session.setAttribute("passvalidStatus","NotOK");
			response.sendRedirect("./SignUpPage.jsp");

		}else if(udao.exists(u_email)){

			session.setAttribute("existStatus", "NotOK");
			response.sendRedirect("./SignUpPage.jsp");

		}else {	

			UserDAO udaolist = new UserDAO();
			List<User> ul = udaolist.Userlist(); 
			User ulast = ul.get(ul.size()-1);
			int lastID = ulast.getUserID();
			
			User u = new User();
			u.setUserID(lastID+1);
			u.setName(u_name);
			u.setSurname(u_surname);
			u.setEmail(u_email);
			u.setPassword(u_password);
			u.setTelephone(u_telephone);
			u.setProfileId(lastID+1);
			
			u.setPhoto(u_picName);
			
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
			String userFolder = folderPath+"/User"+(lastID+1);
			//String path = "C:/UsersContent/User"+(lastID+1);
			File userC = new File(userFolder);
			if(!userC.exists()) {
				try {
					userC.mkdir();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			OutputStream out = null;
		    InputStream filecontent = null;
		    out = new FileOutputStream(new File(userFolder + "/"+ u_picName));
	        filecontent = u_pic.getInputStream();
	        int read = 0;
	        byte[] bytes = new byte[1024];
	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
			out.close();
			filecontent.close();
			
			Profile p = new Profile();
			p.setProfileID(lastID+1);
			p.setName(u_name);
			p.setSurname(u_surname);
			p.setTelephone(u_telephone);
			p.setEmail(u_email);
			
			p.setAgeR(1);
			p.setAddressR(1);
			p.setTelephoneR(1);
			p.setEmailR(1);
			p.setWorkHistoryR(1);
			p.setReTrainR(1);
			p.setEducationR(1);
			p.setProfQualR(1);
			p.setOtherInfoR(1);
			p.setComputerR(1);
			p.setInterestR(1);
			
			//p.setUser(u);
			u.setProfile(p);
			
			//u.setPhoto(u_pic);
			session.setAttribute("user_email", u_email);
			session.setAttribute("user_id", lastID+1);
			//UserDAO udao2 = new UserDAO();
			pdao.signup(p);
			udao.signup(u);
			response.sendRedirect("./HomePage.jsp");

		}
	}
}
