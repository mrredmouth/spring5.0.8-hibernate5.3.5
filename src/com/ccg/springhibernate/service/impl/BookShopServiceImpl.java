package com.ccg.springhibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccg.springhibernate.dao.BookShopDao;
import com.ccg.springhibernate.service.BookShopService;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService{

	@Autowired
	private BookShopDao bookShopDao;
	
	/**
	 * Spring Hibernate 事务的流程：
	 * 1、方法开始之前
	 * 		1）获取Session
	 * 		2）把Session和当前线程绑定，这样就可以在Dao中使用SessionFactory
	 * 		      的getCurrentSession()方法来获取Session
	 * 		3）开启事务
	 * 2、若方法正常结束，没有捕获异常，则：
	 * 		提交事务 -> 解除和当前线程绑定的session -> 关闭Session
	 * 3、若方法出现异常，则：
	 * 		回滚事务 -> 解除和当前线程绑定的session -> 关闭Session
	 */
	@Override
	public void purchase(String username, String isbn) {
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserAccount(username, price);
	}

}
