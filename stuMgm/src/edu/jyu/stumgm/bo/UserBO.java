package edu.jyu.stumgm.bo;

import edu.jyu.stumgm.dao.IUserDAO;
import edu.jyu.stumgm.entity.User;

public class UserBO {
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	private IUserDAO userDAO;
	
	//	用户登录系统教校验
	public User login(String userName, String password) {
		
		User u=userDAO.getByName(userName);
		
		if(u!=null&&u.getPassword().equals(password)) 
			return u;
		else 
			return null;
	}
	/**
	 * 添加 用户账号拓展功能模块代码
	 */
	//修改用户账号权限
	public User editUserRole(String userName,String password){
		return null;
	}
	//新增用户账号
	public boolean addUserRole(User user){
		return false;
	}
	//删除用户账号
	public boolean deleteUserRole(User user){
		return false;
	}
}
