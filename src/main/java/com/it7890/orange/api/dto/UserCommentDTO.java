package com.it7890.orange.api.dto;

import com.avos.avoscloud.AVObject;
import com.it7890.orange.api.entity.UserLikeFavorite;
import com.it7890.orange.api.util.DateUtil;

public class UserCommentDTO{
	
	private String commentId;
	private String userId;
	private String articleId;
	private String content;
	private long createTime;
	private int status;
	private String userName;
	private String userEmail;
	private String nickName;

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public static UserCommentDTO AVOBbjToDTO(AVObject tmp) {
		UserCommentDTO userCommentDTO = null;
		if(null != tmp) {
			userCommentDTO = new UserCommentDTO();
			userCommentDTO.setCommentId(tmp.getObjectId());
			userCommentDTO.setUserId(tmp.getAVObject("userObj").getObjectId());
			userCommentDTO.setUserName(tmp.getAVObject("userObj").getString("username"));
			userCommentDTO.setUserEmail(tmp.getAVObject("userObj").getString("email"));
			userCommentDTO.setNickName(tmp.getAVObject("userObj").getString("nickName"));
			userCommentDTO.setArticleId(tmp.getAVObject("articleObj").getObjectId());
			userCommentDTO.setStatus(tmp.getInt("status"));
			userCommentDTO.setContent(tmp.getString("content"));
			userCommentDTO.setCreateTime(tmp.getCreatedAt().getTime());
		}
		return userCommentDTO;
	}
}
