package dao;

import model.User;
import model.Profile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.NoResultException;

import jpautils.EntityManagerHelper;

public class UserDAO extends DAO<User>{
	
	public UserDAO() {
		super(User.class);
	}
	
	public User login(String email, String password) {
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email= :email"
							+" AND u.password= :password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		User u = null;
		try{
			u = (User)q.getSingleResult();
			return u;
		}catch(NoResultException e){
			return null;
		}
		
	}
	
	public boolean exists(String email) {
		em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email= :email");
		q.setParameter("email", email);
		User u = null;
		try{
			u = (User)q.getSingleResult();
			return true;
		}catch(NoResultException e){
			return false;
		}
		
	}
	
	public boolean changeSettings(String curr_email, String new_email, String password) {
		em = EntityManagerHelper.getEntityManager();
		Query q=null;
		boolean ret= true;
		if(password == null) {
			q = em.createQuery("UPDATE User u SET u.email= :new_email"
										+" WHERE u.email= :curr_email");
			q.setParameter("new_email", new_email);
			q.setParameter("curr_email", curr_email);
			//check with select, set flag
			em.getTransaction().begin();
			q.executeUpdate();
			em.getTransaction().commit();
			
			Query q2 = em.createQuery("SELECT u FROM User u WHERE u.email= :new_email");
			q2.setParameter("new_email", new_email);
			try {
				User u = (User)q2.getSingleResult();
				ret=true;
			}catch(NoResultException e) {
				ret=false;
			}
			//em.close();/////////
			
		}else if(password != null) {
			q = em.createQuery("UPDATE User u SET u.email= :new_email , u.password= :password  WHERE u.email= :curr_email");
			q.setParameter("new_email", new_email);
			q.setParameter("password", password);
			q.setParameter("curr_email", curr_email);
			//check with select, set flag
			em.getTransaction().begin();
			q.executeUpdate();
			em.getTransaction().commit();
			
			Query q3 = em.createQuery("SELECT u FROM User u WHERE u.email= :new_email"
										+" AND u.password= :password");
			q3.setParameter("new_email", new_email);
			q3.setParameter("password", password);
			try {
				User u = (User)q3.getSingleResult();
				ret=true;
			}catch(NoResultException e) {
				ret=false;
			}
			//em.close();/////////
			
		}
		//return flag
		//em.getTransaction().begin();
		//q.executeUpdate();
		//em.getTransaction().commit();
		return ret;
	}
	
	public void signup(User u) {
		em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		//em.close();
	}
	
	 public List<User> Userlist() {
		  em = EntityManagerHelper.getEntityManager();
		  Query q = em.createNamedQuery("User.findAll");
		  @SuppressWarnings("unchecked")
		  List<User> users = q.getResultList();  
		  return users;
	}
	 
	public List<User> SearchFullName(String name, int user_id){
		em = EntityManagerHelper.getEntityManager();
		Query q2=em.createQuery("SELECT u FROM User u WHERE u.userID= :user_id");
		q2.setParameter("user_id", user_id);
		User u = (User)q2.getSingleResult();
		String uname = u.getName();
		String usurname = u.getSurname();
		
		Query q = em.createQuery("SELECT u1 FROM User u1 WHERE (u1.name LIKE :name AND u1.name NOT LIKE :uname) OR (u1.surname LIKE :name AND u1.surname NOT LIKE :usurname)");
		q.setParameter("name", name+"%");
		q.setParameter("uname", uname+"%");
		q.setParameter("usurname", usurname+"%");
		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();  
		return users;
	}
	
	public List<User> getForNotification(List<Integer> user1ids){
		em = EntityManagerHelper.getEntityManager();
		List<User> users = new ArrayList<User>();
		Iterator<Integer> itr = user1ids.iterator();
		while(itr.hasNext()) {
			int user_id = (int) itr.next();
			Query q=em.createQuery("SELECT u FROM User u WHERE u.userID= :user_id");
			q.setParameter("user_id", user_id);
			User user = (User) q.getSingleResult();
			users.add(user);
		}
		return users;
	}
	
	public String getProfImageName(int pID) {
		em = EntityManagerHelper.getEntityManager();
		Query q=em.createQuery("SELECT u FROM User u WHERE u.userID= :user_id");
		q.setParameter("user_id", pID);
		User u = (User)q.getSingleResult();
		String uphoto = u.getPhoto();
		return uphoto;
	}
	
	public String[] getFullName(int userid) {
		em = EntityManagerHelper.getEntityManager();
		Query q=em.createQuery("SELECT u FROM User u WHERE u.userID= :user_id");
		q.setParameter("user_id", userid);
		User u = (User)q.getSingleResult();
		
		String[] fullName = new String[2];
		fullName[0] = u.getName();
		fullName[1] = u.getSurname();
		return fullName;
	}
}