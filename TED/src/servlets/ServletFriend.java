package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import dao.DAO;
import dao.ProfileDAO;
import dao.UserDAO;
import model.Profile;
import model.User;

import dao.FriendsDAO;
import model.Friend;

/**
 * Servlet implementation class ServletFriend
 */
@WebServlet("/ServletFriend")
public class ServletFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFriend() {
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
		String id =(String) request.getParameter("id");
		
		session.setAttribute("SearchStatus", "OK");
		session.setAttribute("buttonStatus", "Add_Friend");
		//session.setAttribute("ID",id);
		int profID = Integer.parseInt(id);
		if(profID!=-1) {
			ProfileDAO pdao = new ProfileDAO();
			Profile p = pdao.find(profID);
		
			if(p!=null) {
				request.setAttribute("p_name", p.getName());
				request.setAttribute("p_surname", p.getSurname());
				if(p.getAgeR()==1) {
					request.setAttribute("p_age", p.getAge());
				}else {
					request.setAttribute("p_age", "-");
				}
				if(p.getAddressR()==1) {
					request.setAttribute("p_address", p.getAddress());
				}else {
					request.setAttribute("p_address", "-");
				}
				if(p.getTelephoneR()==1) {
					request.setAttribute("p_telephone", p.getTelephone());
				}else {
					request.setAttribute("p_telephone", "-");
				}
				if(p.getEmailR()==1) {
					request.setAttribute("p_email", p.getEmail());
				}else {
					request.setAttribute("p_email", p.getEmail());
				}
				if(p.getWorkHistoryR()==1) {
					request.setAttribute("p_workHistory", p.getWorkHistory());
				}else {
					request.setAttribute("p_workHistory", "-");
				}
				if(p.getReTrainR()==1) {
					request.setAttribute("p_resTraining", p.getReasearchTraining());
				}else {
					request.setAttribute("p_resTraining", "-");
				}
				if(p.getEducationR()==1) {
					request.setAttribute("p_education", p.getEducation());
				}else {
					request.setAttribute("p_education", "-");
				}
				if(p.getProfQualR()==1) {
					request.setAttribute("p_profqual", p.getProfQual());
				}else {
					request.setAttribute("p_profqual", "-");
				}
				if(p.getComputerR()==1) {
					request.setAttribute("p_computer", p.getComputer());
				}else {
					request.setAttribute("p_computer", "-");
				}
				if(p.getOtherInfoR()==1) {
					request.setAttribute("p_other", p.getOtherInfo());
				}else {
					request.setAttribute("p_other", "-");
				}
				if(p.getInterestR()==1) {
					request.setAttribute("p_interests", p.getInterests());
				}else {
					request.setAttribute("p_interests", "-");
				}
				
				FriendsDAO fdao = new FriendsDAO();
				int userid1 = (int) session.getAttribute("user_id");
				int[] bs1 = fdao.ButtonStatus(userid1, profID);
				int[] bs2 = fdao.ButtonStatus(profID, userid1);
				
				if((bs1[0]==0 && bs2[0]==0) && (bs1[1]==1 && bs2[1]==1)) {
					session.setAttribute("buttonStatus", "Friends");
				}else if((bs1[0]==1 || bs2[0]==1) && (bs1[1]==0 && bs2[1]==0)) {
					session.setAttribute("buttonStatus", "Pending");
				}
				
				UserDAO udao = new UserDAO();
				String imageName = udao.getProfImageName(profID);
				String imagePath = "UsersContent/User"+profID+"/"+imageName;
				session.setAttribute("p_FriendImagePath", imagePath);
				
				request.setAttribute("Servl", "AddFriend?id="+profID );
				request.getRequestDispatcher("./UserProfile.jsp").forward(request, response);
			}
		}else {
			session.setAttribute("id", -1);
			session.setAttribute("SearchStatus", "NotOK");
			response.sendRedirect("ServletNetwork");	////!!!
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
