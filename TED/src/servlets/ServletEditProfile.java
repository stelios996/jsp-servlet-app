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
import model.Profile;

/**
 * Servlet implementation class ServletEditProfile
 */
@WebServlet("/ServletEditProfile")
public class ServletEditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditProfile() {
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
		HttpSession session = request.getSession();
		String p_name = request.getParameter("name");
		String p_surname = request.getParameter("surname");
		String p_age = request.getParameter("age");
		//String p_gender = request.getParameter("gender");
		String p_address = request.getParameter("address");
		String p_telephone = request.getParameter("telephone");
		String p_email = request.getParameter("email");

		//session.setAttribute("p1Status", "OK");
		//session.setAttribute("p2Status", "OK");
		//session.setAttribute("p3Status", "OK");
		//session.setAttribute("p4Status", "OK");
		//session.setAttribute("p5Status", "OK");
		//session.setAttribute("p6Status", "OK");
		//session.setAttribute("p7Status", "OK");
		
		String p_workHistory = request.getParameter("workHistory");
		String p_restraining = request.getParameter("ResearchTraining");
		String p_university = request.getParameter("university");
		String p_profqual = request.getParameter("profqual");
		String p_computer = request.getParameter("computer");
		String p_other = request.getParameter("other");
		String p_interests = request.getParameter("interests");
		
		//session.setAttribute("p8Status", "OK");
		//session.setAttribute("p9Status", "OK");
		//session.setAttribute("p10Status", "OK");
		//session.setAttribute("p11Status", "OK");
		//session.setAttribute("p12Status", "OK");
		//session.setAttribute("p13Status", "OK");
		//session.setAttribute("p14Status", "OK");
		
		//get the checkboxes values
		String p_ageR = request.getParameter("ageR");
		String p_addressR = request.getParameter("addressR");
		String p_telephoneR = request.getParameter("telephoneR");
		String p_emailR = request.getParameter("emailR");
		String p_workHistoryR = request.getParameter("workHistoryR");
		String p_reasearchR = request.getParameter("reasearchR");
		String p_universityR = request.getParameter("universityR");
		String p_profqualR = request.getParameter("profqualR");
		String p_computerR = request.getParameter("computerR");
		String p_otherR = request.getParameter("otherR");
		String p_interestsR = request.getParameter("interestsR");
		
		
		if(p_name == "") {
			session.setAttribute("p1Status", "");
		}else if(p_surname == "") {
			session.setAttribute("p2Status", "");
		}else if(p_age == "") {
			session.setAttribute("p3Status", "");
		}else if(p_address == "") {
			session.setAttribute("p5Status", "");
		}else if(p_telephone == "") {
			session.setAttribute("p6Status", "");
		}else if(p_email == "") {
			session.setAttribute("p7Status", "");
		}
		
		if(p_workHistory == "") {
			session.setAttribute("p8Status", "");
		}else if(p_restraining == "") {
			session.setAttribute("p9Status", "");
		}else if(p_university == "") {
			session.setAttribute("p10Status", "");
		}else if(p_profqual == "") {
			session.setAttribute("p11Status", "");
		}else if(p_computer == "") {
			session.setAttribute("p12Status", "");
		}else if(p_other == "") {
			session.setAttribute("p13Status", "");
		}else if(p_interests == "") {
			session.setAttribute("p14Status", "");
		}
		
		int ageR, addressR, telephoneR, emailR, workHistoryR, reasearchR, universityR, profqualR, 
				computerR, otherR, interestsR;
		ageR = addressR = telephoneR = emailR = workHistoryR = reasearchR = universityR = profqualR = 
				computerR = otherR = interestsR = 0;
		/*if(p_ageR.equals("selected")) {
			ageR = 1;
		}
		if(p_addressR.equals("selected")) {
			addressR = 1;
		}
		if(p_telephoneR.equals("selected")) {
			telephoneR = 1;
		}
		if(p_emailR.equals("selected")) {
			emailR = 1;
		}
		if(p_workHistoryR.equals("selected")) {
			workHistoryR = 1;
		}
		if(p_reasearchR.equals("selected")) {
			reasearchR = 1;
		}
		if(p_universityR.equals("selected")) {
			universityR = 1;
		}
		if(p_profqualR.equals("selected")) {
			profqualR = 1;
		}
		if(p_computerR.equals("selected")) {
			computerR = 1;
		}
		if(p_otherR.equals("selected")) {
			otherR = 1;
		}
		if(p_interestsR.equals("selected")) {
			interestsR = 1;
		}*/
		if(p_ageR!=null) {
			ageR = 1;
		}
		if(p_addressR!=null) {
			addressR = 1;
		}
		if(p_telephoneR!=null) {
			telephoneR = 1;
		}
		if(p_emailR!=null) {
			emailR = 1;
		}
		if(p_workHistoryR!=null) {
			workHistoryR = 1;
		}
		if(p_reasearchR!=null) {
			reasearchR = 1;
		}
		if(p_universityR!=null) {
			universityR = 1;
		}
		if(p_profqualR!=null) {
			profqualR = 1;
		}
		if(p_computerR!=null) {
			computerR = 1;
		}
		if(p_otherR!=null) {
			otherR = 1;
		}
		if(p_interestsR!=null) {
			interestsR = 1;
		}
		
		//NA TA BALW OLA PUBLIC STO SIGNUP
		
		//dao bazw ta stoixeia tou profil sth bash,3erw to username
		
		ProfileDAO pdao = new ProfileDAO();

		int pid = (int)session.getAttribute("user_id");
		int p_age1 = Integer.parseInt(p_age);
		Profile p = pdao.editProfile(p_name, p_surname, p_age1, p_address, p_telephone, p_email
										, p_workHistory, p_restraining, p_university, p_profqual, p_computer
										, p_other, p_interests, pid,
										ageR, addressR, telephoneR, emailR, workHistoryR, reasearchR, 
										universityR, profqualR, computerR, otherR, interestsR);
		//if(p != null){
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
		//	session.setAttribute("EditPStatus", "OK");
		//	response.sendRedirect(./ProfilePage.jsp);
		//}else{
		//	session.setAttribute("EditPStatus", "NotOK");
		//	response.sendRedirect(./EditProfile.jsp);
		//}
	}

}
