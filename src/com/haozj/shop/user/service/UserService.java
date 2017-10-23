package com.haozj.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.haozj.shop.user.dao.UserDao;
import com.haozj.shop.user.vo.User;
import com.haozj.shop.utils.MailUitls;
import com.haozj.shop.utils.UUIDUtils;
@Transactional
public class UserService {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
	public void save(User user) {
		//0表示未激活
		user.setState(0);
		String code=UUIDUtils.getCode()+UUIDUtils.getCode();
		user.setCode(code);
		userDao.save(user);
		MailUitls.sendMail(user.getEmail(), code);
	}
	//根据激活码查询用户
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	//修改用户的状态
	public void update(User existUser) {
		userDao.update(existUser);
		
	}
	public User login(User user) {
		return userDao.login(user);
	}
}
