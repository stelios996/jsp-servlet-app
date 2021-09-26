package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the articles database table.
 * 
 */
@Entity
@Table(name="articles")
@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idArticles;

	private String image;

	private String text;

	private String video;

	//bi-directional many-to-one association to Articlelike
	/*@OneToMany(mappedBy="article")
	private List<Articlelike> articlelikes;*/

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserID", referencedColumnName="userID")
	private transient User user;

	private int userID;
	//getter.setter
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setUserID(int id) {
		this.userID = id;
	}
	
	public Article() {
	}

	public int getIdArticles() {
		return this.idArticles;
	}

	public void setIdArticles(int idArticles) {
		this.idArticles = idArticles;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	/*public List<Articlelike> getArticlelikes() {
		return this.articlelikes;
	}

	public void setArticlelikes(List<Articlelike> articlelikes) {
		this.articlelikes = articlelikes;
	}

	public Articlelike addArticlelike(Articlelike articlelike) {
		getArticlelikes().add(articlelike);
		articlelike.setArticle(this);

		return articlelike;
	}

	public Articlelike removeArticlelike(Articlelike articlelike) {
		getArticlelikes().remove(articlelike);
		articlelike.setArticle(null);

		return articlelike;
	}*/

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}