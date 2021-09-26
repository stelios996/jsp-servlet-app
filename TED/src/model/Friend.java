package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the friends database table.
 * 
 */
@Entity
@Table(name="friends")
@NamedQuery(name="Friend.findAll", query="SELECT f FROM Friend f")
public class Friend implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int friendID;

	private int requestStatus;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User1", referencedColumnName="userID")
	private transient User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User2", referencedColumnName="userID")
	private transient User user2;

	private int User1;
	private int User2;
	
	public Friend() {
	}

	public int getFriendID() {
		return this.friendID;
	}

	public void setFriendID(int friendID) {
		this.friendID = friendID;
	}

	public int getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
	
	public int getUSER1() {
		return this.User1;
	}

	public void setUSER1(int user1) {
		this.User1 = user1;
	}
	
	public int getUSER2() {
		return this.User2;
	}

	public void setUSER2(int user2) {
		this.User2 = user2;
	}

}