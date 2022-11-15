package com.roadjava.studentroom.bean.entity;

import java.util.Date;
/**
 *
  * 
 * 和管理员表manager对应的实体类
 */
public class ManagerDO {
	private Long id;
	/**
	 * 管理员账号
	 */
	private String userName;
	/**
	 * 管理员密码
	 */
	private String pwd;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
