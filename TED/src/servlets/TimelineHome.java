package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import dao.DAO;
import dao.ArticleDAO;
import model.Article;
import dao.FriendsDAO;
import model.Friend;
import dao.UserDAO;
import model.User;
import dao.ArticleLikeDAO;
import model.Articlelike;
import model.ListElement;

/**
 * Servlet implementation class TimelineHome
 */
@WebServlet("/TimelineHome")
public class TimelineHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimelineHome() {
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
		
		ArticleDAO adao = new ArticleDAO();
		List<Article> articleList = adao.getAllArticles();
		
		FriendsDAO fdao = new FriendsDAO();
		List<Integer> friendIDs = fdao.getFriendIDs(id);
		
		List<Article> userTimeline = new ArrayList<Article>();
		for(int i=0; i<articleList.size(); i++) {
			if( (articleList.get(i).getUserID()==id) || (friendIDs.contains(articleList.get(i).getUserID())) ) {
				userTimeline.add(articleList.get(i));
			}
		}
		
		List<ListElement> timeline = new ArrayList<ListElement>();
		for(int i=0; i<userTimeline.size(); i++) {
			ListElement elem = new ListElement();
			Article article = userTimeline.get(i);
			
			elem.setUserID(article.getUserID());
			elem.setArticleID(article.getIdArticles());
			if(article.getText().equals(" ")) {
				elem.setTextFlag(0);
				elem.setText("");
			}else {
				elem.setTextFlag(1);
				elem.setText(article.getText());
			}
			
			if(article.getImage().equals("")) {
				elem.setImageFlag(0);
				elem.setImage("");
			}else {
				elem.setImageFlag(1);
				elem.setImage("UsersContent/User"+article.getUserID()+"/"+article.getImage());
			}
			
			if(article.getVideo().equals("")) {
				elem.setVideoFlag(0);
				elem.setVideo("");
			}else {
				elem.setVideoFlag(1);
				elem.setVideo("UsersContent/User"+article.getUserID()+"/"+article.getVideo());
			}
			
			UserDAO udao = new UserDAO();
			String fullName[] = udao.getFullName(article.getUserID());
			elem.setUserName(fullName[0]);
			elem.setUserSurname(fullName[1]);
			
			ArticleLikeDAO aldao = new ArticleLikeDAO();
			List<Articlelike> alist = new ArrayList<Articlelike>();
			alist = aldao.getLikes(article.getIdArticles());
			elem.setLikes(alist.size());
			
			int found = 0;
			for(int j=0; j<alist.size(); j++) {
				if(alist.get(j).getUserID() == id) {
					found = 1;
				}
			}
			if(found == 0) {
				elem.setLikeFlag(0);
			}else if(found == 1) {
				elem.setLikeFlag(1);
			}
			
			//gia ta div me tous likers, pairnw ta onomata twn user pou exoun kanei like sto article
			UserDAO udao2 = new UserDAO();
			String likersFullNames = "";
			for(int k=0; k<alist.size(); k++) {
				int likerID = alist.get(k).getUserID();
				String fullNamelike[] = udao2.getFullName(likerID);
				likersFullNames+=fullNamelike[0]+" "+fullNamelike[1]+"<br>";
			}
			elem.setLikerFullNames(likersFullNames);
			
			timeline.add(elem);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String listToJSON = mapper.writeValueAsString(timeline);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(listToJSON);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
