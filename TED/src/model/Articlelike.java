package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the articlelikes database table.
 * 
 */
@Entity
@Table(name="articlelikes")
@NamedQuery(name="Articlelike.findAll", query="SELECT a FROM Articlelike a")
public class Articlelike implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idArticleLikes;

	//bi-directional many-to-one association to Article
	@ManyToOne
	@JoinColumn(name="ArticleID", referencedColumnName="idArticles")
	private transient Article article;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserID", referencedColumnName="userID")
	private transient User user;
	
	private int articleID;
	private int userID;
	//getter.setter
	
	public void setArticleID(int aID) {
		this.articleID = aID;
	}
	
	public void setUserID(int uID) {
		this.userID = uID;
	}
	
	public int getArticleID() {
		return this.articleID;
	}
	
	public int getUserID() {
		return this.userID;
	}

	public Articlelike() {
	}

	public int getIdArticleLikes() {
		return this.idArticleLikes;
	}

	public void setIdArticleLikes(int idArticleLikes) {
		this.idArticleLikes = idArticleLikes;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}