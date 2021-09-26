package dao;

import model.Profile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.NoResultException;

import jpautils.EntityManagerHelper;

public class ProfileDAO extends DAO<Profile>{

	public ProfileDAO() {
		super(Profile.class);
	}
	
	public void signup(Profile p) {
		em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		//em.close();
	}
	
	public Profile find(int profile_id) {
		  em = EntityManagerHelper.getEntityManager();
		  Profile prof = em.find(Profile.class, profile_id); 
		  return prof;
	}
	
	public Profile editProfile(String name, String surname, int age, String address, String telephone,
								String email, String workHistory, String restraining, String university,
								String profqual, String computer, String other, String interests,
								int pid,
								int ageR, int addressR, int telephoneR, int emailR, int workHistoryR,
								int reasearchR, int universityR, int profqualR, int computerR,
								int otherR, int interestsR) {

		em = EntityManagerHelper.getEntityManager();
		Query q=em.createQuery("UPDATE Profile p SET p.name= :name , p.surname= :surname , p.age= :age , "
									+ "p.address= :address , p.telephone= :telephone , p.email= :email , "
									+ "p.workHistory= :workHistory , p.reasearchTraining= :restraining , "
								+ "p.education= :university , p.profQual= :profqual , p.computer= :computer , "
								+ "p.otherInfo= :other , p.interests= :interests "
								+ "WHERE p.profileID= :pID");
		q.setParameter("name", name);
		q.setParameter("surname", surname);
		q.setParameter("age", age);
		q.setParameter("address", address);
		q.setParameter("telephone", telephone);
		q.setParameter("email", email);
		q.setParameter("workHistory", workHistory);
		q.setParameter("restraining", restraining);
		q.setParameter("university", university);
		q.setParameter("profqual", profqual);
		q.setParameter("computer", computer);
		q.setParameter("other", other);
		q.setParameter("interests", interests);
		q.setParameter("pID", pid);
		
		em.getTransaction().begin();
		q.executeUpdate();
		em.getTransaction().commit();
		
		Query q2 = em.createQuery("UPDATE Profile p SET p.ageR= :ageR , p.addressR= :addressR , "
				+ "p.telephoneR= :telephoneR , p.emailR= :emailR , p.workHistoryR = :workHistoryR , "
				+ "p.reTrainR= :reasearchR , p.educationR= :universityR , p.profQualR= :profqualR , "
				+ "p.otherInfoR= :otherR, p.computerR= :computerR , p.interestR= :interestsR "
				+ "WHERE p.profileID= :pID");
		
		q2.setParameter("ageR", ageR);
		q2.setParameter("addressR", addressR);
		q2.setParameter("telephoneR", telephoneR);
		q2.setParameter("emailR", emailR);
		q2.setParameter("workHistoryR", workHistoryR);
		q2.setParameter("reasearchR", reasearchR);
		q2.setParameter("universityR", universityR);
		q2.setParameter("profqualR", profqualR);
		q2.setParameter("otherR", otherR);
		q2.setParameter("computerR", computerR);
		q2.setParameter("interestsR", interestsR);
		q2.setParameter("pID", pid);
		
		em.getTransaction().begin();
		q2.executeUpdate();
		em.getTransaction().commit();
		
		Profile p = new Profile();
		p.setName(name);
		p.setSurname(surname);
		p.setAge(age);
		p.setAddress(address);
		p.setTelephone(telephone);
		p.setEmail(email);
		p.setWorkHistory(workHistory);
		p.setReasearchTraining(restraining);
		p.setEducation(university);
		p.setProfQual(profqual);
		p.setComputer(computer);
		p.setOtherInfo(other);
		p.setInterests(interests);
		
		p.setAgeR(ageR);
		p.setAddressR(addressR);
		p.setTelephoneR(telephoneR);
		p.setEmailR(emailR);
		p.setWorkHistoryR(workHistoryR);
		p.setReTrainR(reasearchR);
		p.setEducationR(universityR);
		p.setProfQualR(profqualR);
		p.setComputerR(computerR);
		p.setOtherInfoR(otherR);
		p.setInterestR(interestsR);
		
		return p;
	}
	
	public List<Profile> getFriendProfiles(List<Integer> idlist){
		em = EntityManagerHelper.getEntityManager();
		Iterator<Integer> itr = idlist.iterator();
		List<Profile> plist = new ArrayList<Profile>();
		while(itr.hasNext()) {
			int current_id = (int) itr.next();
			Profile prof = em.find(Profile.class, current_id);
			plist.add(prof);
		}
		return plist;
	}
}
