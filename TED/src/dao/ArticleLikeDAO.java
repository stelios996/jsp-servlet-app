package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Article;
import model.Articlelike;

public class ArticleLikeDAO extends DAO<Articlelike>{

	public ArticleLikeDAO() {
		super(Articlelike.class);
	}
	
	public int getNumberOfArticleLikes() {
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createNamedQuery("Articlelike.findAll");
		@SuppressWarnings("unchecked")
		List<Articlelike> articlelikes = q.getResultList();
		int numberOfArticleLikes = articlelikes.size();
		return numberOfArticleLikes;
	}
	
	public void insertLike(int userID, int articleID) {
		em = EntityManagerHelper.getEntityManager();
		
		int alID;
		int i;
		for(i=1; ;i++) {
			try {
				Query q = em.createQuery("SELECT al FROM Articlelike al WHERE al.idArticleLikes= :ID ");
				q.setParameter("ID", i);
				Articlelike like = (Articlelike) q.getSingleResult();
			}catch(NoResultException e) {
					alID=i;
					break;
			}
		}
		
		Articlelike al = new Articlelike();
		al.setIdArticleLikes(alID);
		al.setArticleID(articleID);
		al.setUserID(userID);
		
		em.getTransaction().begin();
		em.persist(al);
		em.getTransaction().commit();
		//em.close();
	}
	
	public List<Articlelike> getLikes(int articleID){
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT al FROM Articlelike al WHERE al.articleID= :id ");
		q.setParameter("id", articleID);
		@SuppressWarnings("unchecked")
		List<Articlelike> articlelikes = q.getResultList();
		return articlelikes;
	}
	
	public boolean exists(int userID, int articleID) {
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT al FROM Articlelike al WHERE al.articleID= :arID AND al.userID= :uID ");
		q.setParameter("arID", articleID);
		q.setParameter("uID", userID);
		Articlelike like = null;
		try {
			like = (Articlelike) q.getSingleResult();
			return true;
		}catch(NoResultException e){
			return false;
		}
	}
	
	public void removelike(int userID, int articleID) {
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT al FROM Articlelike al WHERE al.articleID= :arID AND al.userID= :uID ");
		q.setParameter("arID", articleID);
		q.setParameter("uID", userID);
		Articlelike al = (Articlelike) q.getSingleResult();
		
		em.getTransaction().begin();
		em.remove(al);
		em.getTransaction().commit();
	}
	
	public List<Integer> getUserIDs(List<Integer> articleIDs){
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createNamedQuery("Articlelike.findAll");
		@SuppressWarnings("unchecked")
		List<Articlelike> articlelikes = q.getResultList();
		
		List<Integer> userIDs = new ArrayList<Integer>();
		Articlelike al = new Articlelike();
		for(int i=0; i<articlelikes.size(); i++) {
			al = articlelikes.get(i);
			if(articleIDs.contains(al.getArticleID())) {
				userIDs.add(al.getUserID());
			}
		}
		return userIDs;
	}
	
	public List<Articlelike> getPostsLikedByUser(int userID){
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createNamedQuery("Articlelike.findAll");
		@SuppressWarnings("unchecked")
		List<Articlelike> articlelikes = q.getResultList();
		
		List<Articlelike> likes= new ArrayList<Articlelike>();
		for(int i=0; i<articlelikes.size(); i++) {
			if(articlelikes.get(i).getUserID()==userID) {
				likes.add(articlelikes.get(i));
			}
		}
		return likes;
	}
}
