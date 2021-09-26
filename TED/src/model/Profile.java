package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the profile database table.
 * 
 */
@Entity
@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int profileID;

	private String address;

	private int addressR;

	private int age;

	private int ageR;

	@Lob
	private String computer;

	private int computerR;

	@Lob
	private String education;

	private int educationR;

	private String email;

	private int emailR;

	private int interestR;

	@Lob
	private String interests;

	private String name;

	@Lob
	private String otherInfo;

	private int otherInfoR;

	@Lob
	private String profQual;

	private int profQualR;

	@Lob
	private String reasearchTraining;

	private int reTrainR;

	private String surname;

	private String telephone;

	private int telephoneR;

	@Lob
	private String workHistory;

	private int workHistoryR;

	//bi-directional one-to-one association to User
	@OneToOne
	@PrimaryKeyJoinColumn(name="profileID", referencedColumnName="ProfileID")
	private transient User user;

	public Profile() {
	}

	public int getProfileID() {
		return this.profileID;
	}

	public void setProfileID(int profileID) {
		this.profileID = profileID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAddressR() {
		return this.addressR;
	}

	public void setAddressR(int addressR) {
		this.addressR = addressR;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAgeR() {
		return this.ageR;
	}

	public void setAgeR(int ageR) {
		this.ageR = ageR;
	}

	public String getComputer() {
		return this.computer;
	}

	public void setComputer(String computer) {
		this.computer = computer;
	}

	public int getComputerR() {
		return this.computerR;
	}

	public void setComputerR(int computerR) {
		this.computerR = computerR;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public int getEducationR() {
		return this.educationR;
	}

	public void setEducationR(int educationR) {
		this.educationR = educationR;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmailR() {
		return this.emailR;
	}

	public void setEmailR(int emailR) {
		this.emailR = emailR;
	}

	public int getInterestR() {
		return this.interestR;
	}

	public void setInterestR(int interestR) {
		this.interestR = interestR;
	}

	public String getInterests() {
		return this.interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOtherInfo() {
		return this.otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public int getOtherInfoR() {
		return this.otherInfoR;
	}

	public void setOtherInfoR(int otherInfoR) {
		this.otherInfoR = otherInfoR;
	}

	public String getProfQual() {
		return this.profQual;
	}

	public void setProfQual(String profQual) {
		this.profQual = profQual;
	}

	public int getProfQualR() {
		return this.profQualR;
	}

	public void setProfQualR(int profQualR) {
		this.profQualR = profQualR;
	}

	public String getReasearchTraining() {
		return this.reasearchTraining;
	}

	public void setReasearchTraining(String reasearchTraining) {
		this.reasearchTraining = reasearchTraining;
	}

	public int getReTrainR() {
		return this.reTrainR;
	}

	public void setReTrainR(int reTrainR) {
		this.reTrainR = reTrainR;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getTelephoneR() {
		return this.telephoneR;
	}

	public void setTelephoneR(int telephoneR) {
		this.telephoneR = telephoneR;
	}

	public String getWorkHistory() {
		return this.workHistory;
	}

	public void setWorkHistory(String workHistory) {
		this.workHistory = workHistory;
	}

	public int getWorkHistoryR() {
		return this.workHistoryR;
	}

	public void setWorkHistoryR(int workHistoryR) {
		this.workHistoryR = workHistoryR;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}