package dao;

import model.Friend;
import model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.NoResultException;

import jpautils.EntityManagerHelper;

public class FriendsDAO extends DAO<Friend>{
	
	public FriendsDAO() {
		super(Friend.class);
	}
	
	public int[] ButtonStatus(int userid1, int userid2) {
		
		//1:Pending, 2:Friends
		int[] buttonStatus = new int[2];
		
		em = EntityManagerHelper.getEntityManager();
		for(int i=0; i<2; i++) {
			Query q = em.createQuery("SELECT f FROM Friend f WHERE f.User1= :userid1"
							+" AND f.User2= :userid2 AND f.requestStatus= :status ");
			q.setParameter("userid1", userid1);
			q.setParameter("userid2", userid2);
			q.setParameter("status", i+1);
			@SuppressWarnings("unchecked")
			List<User> status = q.getResultList();
			if(status.size()==0) {	//not exist
				buttonStatus[i] = 0;
			}else {					//exist
				buttonStatus[i] = 1;
			}
		}
		return buttonStatus;
	}
	
	public void insert(int user1, int user2) {
		em = EntityManagerHelper.getEntityManager();
		
		int fID;
		int i;
		for(i=1; ;i++) {
			try {
				Query q = em.createQuery("SELECT f FROM Friend f WHERE f.friendID= :id ");
				q.setParameter("id", i);
				Friend qres = (Friend) q.getSingleResult();
			}catch(NoResultException e) {
					fID=i;
					break;
			}
		}
		
		Friend f = new Friend();
		f.setFriendID(fID);
		f.setUSER1(user1);
		f.setUSER2(user2);
		f.setRequestStatus(1);
		
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
		//em.close();
	}
	
	public List<Friend> searchRequests(int id){
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT f FROM Friend f WHERE f.User2= :id AND f.requestStatus= :status ");
		q.setParameter("id", id);
		q.setParameter("status", 1);
		@SuppressWarnings("unchecked")
		List<Friend> friends_req = q.getResultList();
		return friends_req;
	}
	
	public void acceptFriendRequest(int user1, int user2) {
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("UPDATE Friend f SET f.requestStatus= :status WHERE f.User1= :user1 AND f.User2= :user2 ");
		q.setParameter("status", 2);
		q.setParameter("user1", user1);
		q.setParameter("user2", user2);
		
		em.getTransaction().begin();
		q.executeUpdate();
		em.getTransaction().commit();
		
		//na kanw kai to antistrofo accept;
		try {
			Query q2 = em.createQuery("SELECT f FROM Friend f WHERE f.User1= :user2 AND f.User2= :user1 AND f.requestStatus= :status ");
			q2.setParameter("user1", user1);
			q2.setParameter("user2", user2);
			q2.setParameter("status", 1);
			Friend qres = (Friend) q2.getSingleResult();
			
			//uparxei h sxesh sto Friends table
			Query q3 = em.createQuery("UPDATE Friend f SET f.requestStatus= :status WHERE f.User1= :user2 AND f.User2= :user1 ");
			q3.setParameter("status", 2);
			q3.setParameter("user1", user1);
			q3.setParameter("user2", user2);
			
			em.getTransaction().begin();
			q3.executeUpdate();
			em.getTransaction().commit();
			
		}catch(NoResultException e) {
			//den uparxei h sxesh sto Friends table
			this.insert(user2, user1);
			Query q4 = em.createQuery("UPDATE Friend f SET f.requestStatus= :status WHERE f.User1= :user2 AND f.User2= :user1 ");
			q4.setParameter("status", 2);
			q4.setParameter("user1", user1);
			q4.setParameter("user2", user2);
			
			em.getTransaction().begin();
			q4.executeUpdate();
			em.getTransaction().commit();
			
			
		}
		
	}
	
	public void declineFriendRequest(int user1, int user2) {
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT f FROM Friend f WHERE f.User1= :user1 AND f.User2= :user2 ");
		q.setParameter("user1", user1);
		q.setParameter("user2", user2);
		Friend f = (Friend) q.getSingleResult();
		
		em.getTransaction().begin();
		em.remove(f);
		em.getTransaction().commit();
		
	}
	
	public List<Integer> getFriendIDs(int id){
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT f FROM Friend f WHERE f.User2= :user2 AND f.requestStatus= :status ");
		q.setParameter("user2", id);
		q.setParameter("status", 2);
		@SuppressWarnings("unchecked")
		List<Friend> friends = q.getResultList();
		
		List<Integer> friendsIDs = new ArrayList<Integer>();
		
		Iterator<Friend> itr = friends.iterator();
		while(itr.hasNext()) {
			Friend f = (Friend) itr.next();
			int fID = f.getUSER1();
			friendsIDs.add(fID);
		}
		return friendsIDs;
	}
 }
