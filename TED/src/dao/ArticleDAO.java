package dao;

import model.Article;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import jpautils.EntityManagerHelper;

public class ArticleDAO extends DAO<Article>{

	public ArticleDAO() {
		super(Article.class);
	}
	
	public void postArticle(Article a) {
		em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		//em.close();
	}
	
	public int getNumberOfArticles() {
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createNamedQuery("Article.findAll");
		@SuppressWarnings("unchecked")
		List<Article> articles = q.getResultList();
		int numberOfArticles = articles.size();
		return numberOfArticles;
	}
	
	public List<Article> getAllArticles(){
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createNamedQuery("Article.findAll");
		@SuppressWarnings("unchecked")
		List<Article> articles = q.getResultList();
		return articles;
	}
	
	public List<Integer> getUserArticles(int userID){
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT a FROM Article a WHERE a.userID= :uID ");
		q.setParameter("uID", userID);
		@SuppressWarnings("unchecked")
		List<Article> articles = q.getResultList();
		if(articles.size()!=0) {
			List<Integer> arIDs = new ArrayList<Integer>();
			for(int i=0; i<articles.size(); i++) {
				arIDs.add(articles.get(i).getIdArticles());
			}
			return arIDs;
		}else {
			return null;
		}
		
	}
	
	public Article find(int article_id) {
		em = EntityManagerHelper.getEntityManager();
		Article ar = em.find(Article.class, article_id);
		return ar;
	}
	
}
