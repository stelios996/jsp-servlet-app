package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import dao.ProfileDAO;
import dao.UserDAO;
import model.Profile;
import dao.ArticleDAO;
import dao.ArticleLikeDAO;
import dao.FriendsDAO;
import model.Article;
import model.Articlelike;
import model.Friend;

/**
 * Servlet implementation class CreateXML
 */
@WebServlet("/CreateXML")
public class CreateXML extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateXML() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//HttpSession session = request.getSession();
		String ids = request.getParameter("id");
		int id = Integer.parseInt(ids);
		
		String folderPath = "C:\\UsersXML";
		File usersXML = new File(folderPath);
		if(!usersXML.exists()) {
			try {
				usersXML.mkdir();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		String filexml = folderPath+"\\User"+id+"XML.xml";
		
		UserDAO udao = new UserDAO();
		ProfileDAO pdao = new ProfileDAO();
		FriendsDAO fdao = new FriendsDAO();
		ArticleDAO adao = new ArticleDAO();
		ArticleLikeDAO aldao = new ArticleLikeDAO();
		
		Profile p = null;
		List<Integer> friendsID = null;
		List<Integer> articlesID = null;
		
		try {	
			p = pdao.find(id);
			
			friendsID = new ArrayList<Integer>();
			friendsID = fdao.getFriendIDs(id);
			
			articlesID = new ArrayList<Integer>();
			articlesID = adao.getUserArticles(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("UserInfo");
			doc.appendChild(rootElement);
			
			if(p!=null) {
				Element user = doc.createElement("user");
				rootElement.appendChild(user);
				user.setAttribute("id", ""+p.getProfileID()+"");
			
				if(p.getName() != null) {
					Element name = doc.createElement("Name");
					name.appendChild(doc.createTextNode(""+p.getName()+""));
					user.appendChild(name);
				}
			
				if(p.getSurname() != null){
					Element surname = doc.createElement("Surname");
					surname.appendChild(doc.createTextNode(""+p.getSurname()+""));
					user.appendChild(surname);
				}
				
				if(p.getAge() != 0){
					Element age = doc.createElement("Age");
					age.appendChild(doc.createTextNode(""+p.getAge()+""));
					user.appendChild(age);
				}
				
				if(p.getAddress() != null){
					Element address = doc.createElement("Address");
					address.appendChild(doc.createTextNode(""+p.getAddress()+""));
					user.appendChild(address);
				}
				
				if(p.getTelephone() != null){
					Element telephone = doc.createElement("Telephone");
					telephone.appendChild(doc.createTextNode(""+p.getTelephone()+""));
					user.appendChild(telephone);
				}
				
				if(p.getEmail() != null){
					Element email = doc.createElement("Email");
					email.appendChild(doc.createTextNode(""+p.getEmail()+""));
					user.appendChild(email);
				}
				
				Element cv = doc.createElement("CV");
				user.appendChild(cv);
			
				if(p.getWorkHistory() != null){
					Element workHistory = doc.createElement("WorkHistory");
					workHistory.appendChild(doc.createTextNode(""+p.getWorkHistory()+""));
					cv.appendChild(workHistory);
				}
				
				if(p.getReasearchTraining() != null){
					Element researchTraining = doc.createElement("ResearchTraining");
					researchTraining.appendChild(doc.createTextNode(""+p.getReasearchTraining()+""));
					cv.appendChild(researchTraining);
				}
				
				if(p.getEducation() != null){
					Element education = doc.createElement("Education");
					education.appendChild(doc.createTextNode(""+p.getEducation()+""));
					cv.appendChild(education);
				}
				
				if(p.getProfQual() != null){
					Element profQual = doc.createElement("ProfQualifications");
					profQual.appendChild(doc.createTextNode(""+p.getProfQual()+""));
					cv.appendChild(profQual);
				}
				
				if(p.getComputer() != null){
					Element computer = doc.createElement("ComputerKnowledge");
					computer.appendChild(doc.createTextNode(""+p.getComputer()+""));
					cv.appendChild(computer);
				}
				
				if(p.getOtherInfo() != null){
					Element other = doc.createElement("OtherInfo");
					other.appendChild(doc.createTextNode(""+p.getOtherInfo()+""));
					cv.appendChild(other);
				}
				
				if(p.getInterests() != null){
					Element interests = doc.createElement("Interests");
					interests.appendChild(doc.createTextNode(""+p.getInterests()+""));
					cv.appendChild(interests);
				}
				
				Element friends = doc.createElement("Friends");
				user.appendChild(friends);
			
				if(friendsID !=null) {
					//UserDAO udao = new UserDAO();
					for(int i=0; i<friendsID.size(); i++) {
						Element friend = doc.createElement("Friend");
						String[] nameSurname = udao.getFullName(friendsID.get(i));
						String fullname = nameSurname[0]+" "+nameSurname[1];
						friend.appendChild(doc.createTextNode(""+fullname+""));
						friend.setAttribute("id", ""+friendsID.get(i)+"");
						friends.appendChild(friend);
					}
				}
			
				Element articles = doc.createElement("Articles");
				user.appendChild(articles);
				
				if(articlesID != null) {
					for(int j=0; j<articlesID.size(); j++) {
						Element article = doc.createElement("Article");
						article.setAttribute("id", ""+articlesID.get(j)+"");
						articles.appendChild(article);
				
						Article ar = adao.find(articlesID.get(j));
				
						if(!ar.getImage().equals("")) {
							Element image = doc.createElement("Image");
							image.appendChild(doc.createTextNode(""+ar.getImage()+""));
							article.appendChild(image);
						}
				
						if(!ar.getVideo().equals("")) {
							Element video = doc.createElement("Video");
							video.appendChild(doc.createTextNode(""+ar.getVideo()+""));
							article.appendChild(video);
						}
						
						if(!ar.getText().equals(" ")) {
							Element text = doc.createElement("Text");
							text.appendChild(doc.createTextNode(""+ar.getText()+""));
							article.appendChild(text);
						}
						
						Element likes = doc.createElement("Likes");
						article.appendChild(likes);
						
						List<Articlelike> arLikes = null;
						arLikes = new ArrayList<Articlelike>();
						arLikes = aldao.getLikes(articlesID.get(j));
						if(arLikes!=null) {
							Element likesNumber = doc.createElement("LikesNumber");
							likesNumber.appendChild(doc.createTextNode(""+arLikes.size()+""));
							likes.appendChild(likesNumber);
							for(int k=0; k<arLikes.size(); k++) {
								int userid = arLikes.get(k).getUserID();
								String[] nameSurname = udao.getFullName(userid);
								String fullname = nameSurname[0]+" "+nameSurname[1];
								
								Element like = doc.createElement("like");
								like.setAttribute("id", ""+userid+"");
								like.appendChild(doc.createTextNode(""+fullname+""));
								likes.appendChild(like);
							}
						}
						
					}
				}
				
				Element postsLiked = doc.createElement("PostsLiked");
				user.appendChild(postsLiked);
				
				List<Articlelike> posts = null;
				posts = new ArrayList<Articlelike>();
				posts = aldao.getPostsLikedByUser(p.getProfileID());
				if(posts != null) {
					for(int l=0; l<posts.size(); l++) {
						Element post = doc.createElement("Post");
						post.setAttribute("id", ""+posts.get(l).getArticleID() +"");
						postsLiked.appendChild(post);
						
						Article ar1 = adao.find(posts.get(l).getArticleID());
						
						String[] nameSurname = udao.getFullName(ar1.getUserID());
						String fullname = nameSurname[0]+" "+nameSurname[1];
						
						Element user1 = doc.createElement("User");
						user1.setAttribute("id", ""+ar1.getUserID()+"");
						user1.appendChild(doc.createTextNode(""+fullname+""));
						post.appendChild(user1);
						
						if(!ar1.getImage().equals("")) {
							Element image = doc.createElement("Image");
							image.appendChild(doc.createTextNode(""+ar1.getImage()+""));
							post.appendChild(image);
						}
				
						if(!ar1.getVideo().equals("")) {
							Element video = doc.createElement("Video");
							video.appendChild(doc.createTextNode(""+ar1.getVideo()+""));
							post.appendChild(video);
						}
						
						if(!ar1.getText().equals(" ")) {
							Element text = doc.createElement("Text");
							text.appendChild(doc.createTextNode(""+ar1.getText()+""));
							post.appendChild(text);
						}
					}
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filexml));
			transformer.transform(source, result);
			
			request.getRequestDispatcher("./AdminPage.jsp").forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
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
