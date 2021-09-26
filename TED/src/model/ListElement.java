package model;

import java.util.ArrayList;
import java.util.List;

public class ListElement {

	private int articleID;
	private String text;
	private String image;
	private String video;
	private int textFlag;
	private int imageFlag;
	private int videoFlag;
	private int userID;
	private String userName;
	private String userSurname;
	private int likes;
	private int likeFlag;
	//private List<String> likerFullName;
	private String likerFullNames;
	
	public ListElement() {
		
	}
	
	public void setArticleID(int id) {
		this.articleID = id;
	}
	
	public void setText(String txt) {
		this.text = txt;
	}
	
	public void setImage(String img) {
		this.image = img;
	}
	
	public void setVideo(String vd) {
		this.video = vd;
	}
	
	public void setTextFlag(int txtfl) {
		this.textFlag = txtfl;
	}
	
	public void setImageFlag(int imgfl) {
		this.imageFlag = imgfl;
	}
	
	public void setVideoFlag(int vdfl) {
		this.videoFlag = vdfl;
	}
	
	public void setUserID(int userid) {
		this.userID = userid;
	}
	
	public void setUserName(String name) {
		this.userName = name;
	}
	
	public void setUserSurname(String surname) {
		this.userSurname = surname;
	}
	
	public int getArticleID() {
		return this.articleID;
	}
	
	public String getText() {
		return this.text;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public String getVideo() {
		return this.video;
	}
	
	public int getTextFlag() {
		return this.textFlag;
	}
	
	public int getImageFlag() {
		return this.imageFlag;
	}
	
	public int getVideoFlag() {
		return this.videoFlag;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getUserSurname() {
		return this.userSurname;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public int getLikes() {
		return this.likes;
	}
	
	public void setLikeFlag(int lf) {
		this.likeFlag = lf;
	}
	
	public int getLikeFlag() {
		return this.likeFlag;
	}
	
	public void setLikerFullNames(String s) {
		this.likerFullNames = s;
	}
	
	public String getLikerFullNames() {
		return this.likerFullNames;
	}
}
