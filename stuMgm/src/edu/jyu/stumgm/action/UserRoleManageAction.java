package edu.jyu.stumgm.action;

import com.opensymphony.xwork2.ActionSupport;

import edu.jyu.stumgm.bo.UserBO;

public class UserRoleManageAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String userName = "";
	private String password = "";
	private boolean loginError = false;
	private UserBO userBO;

	public UserBO getUserBO() {
		return userBO;
	}

	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
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
	
	public boolean isLoginError(){
		return loginError;
	}
	
	/**
	 * 
	 */
	
	@Override
	public String execute() throws Exception {
		
		return "usermenu";
	}
}
