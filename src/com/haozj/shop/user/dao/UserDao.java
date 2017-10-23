package com.haozj.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.haozj.shop.user.vo.User;

/**
 * 用户模块持久层
 * 
 * @author haozj
 *
 */
public class UserDao extends HibernateDaoSupport {
	public User findByUsername(String username) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		String sql = "from User where username=?";
		List<User> list = (List<User>) hibernateTemplate.find(sql, username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		hibernateTemplate.save(user);
	}

	public User findByCode(String code) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		String sql = "from User where code=?";
		List<User> list = (List<User>) hibernateTemplate.find(sql, code);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void update(User existUser) {
		// TODO Auto-generated method stub
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		hibernateTemplate.update(existUser);
	}

	public User login(User user) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		String sql = "from User where username=? and password=? and state=?";
		List<User> list = (List<User>) hibernateTemplate.find(sql, user.getUsername(), user.getPassword(),1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
