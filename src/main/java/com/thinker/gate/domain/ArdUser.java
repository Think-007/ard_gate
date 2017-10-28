/**
 * 
 */
package com.thinker.gate.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 用户模型
 * 
 * @author lipengfeia
 *
 */
public class ArdUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户id
	private double userId;

	// 用户名
	private String userName;

	// 用户密码
	private String password;

	// 盐值
	private String salt;

	// 性别 0男 1女
	private int sex;

	// 用户状态 0正常 -1注销 1锁定
	private int status;

	// 用户级别
	private int level;

	// 用户角色
	private List<ArdRole> roles;

	public double getUserId() {
		return userId;
	}

	public void setUserId(double userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<ArdRole> getRoles() {
		return roles;
	}

	public void setRoles(List<ArdRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "ArdUser [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", salt=" + salt + ", sex=" + sex
				+ ", status=" + status + ", level=" + level + ", roles="
				+ roles + "]";
	}

}
