package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpautils.EntityManagerHelper;

public class DAO<T>{
	
	final private Class<T> type;
	EntityManager em;
	//EntityTransaction tx;

	public DAO(Class<T> type) {
		this.type = type;
	}

}