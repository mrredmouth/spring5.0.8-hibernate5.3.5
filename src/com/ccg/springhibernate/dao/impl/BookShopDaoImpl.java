package com.ccg.springhibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccg.springhibernate.dao.BookShopDao;
import com.ccg.springhibernate.exception.BookStockException;
import com.ccg.springhibernate.exception.UserAccountException;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 不推荐使用HibernateTemplate 和  HibernateDaoSupport,
	 * 因为这样会使得Dao和Spring的API高度耦合，移植性变差
	 */
	//private HibernateTemplate hibernateTemplate;
	
	
	/**
	 * 获取和当前线程绑定的Session。
	 * SessionFactory是线程安全的。
	 * @return
	 */
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public int findBookPriceByIsbn(String isbn) {
		String hql = "select b.price from Book b where b.isbn = ?0";
		Query<?> query = getSession().createQuery(hql).setParameter(0,isbn);
		return (Integer) query.uniqueResult();
	}

	@Override
	public void updateBookStock(String isbn) {
		//验证书的库存是否充足
		String hql2 = "select b.stock from Book b where b.isbn = ?0";
		Query<Integer> query2 = getSession().createQuery(hql2, Integer.class).setParameter(0, isbn);
		int stock = query2.uniqueResult();
		if(stock == 0){
			throw new BookStockException("库存不足!");
		}
		String hql = "update Book b set b.stock = b.stock - 1 where b.isbn = ?0";
		getSession().createQuery(hql).setParameter(0, isbn).executeUpdate();

	}

	@Override
	public void updateUserAccount(String username, int price) {
		//验证余额是否足够
		String hql2 = "select balance from Account where username = ?0";
		int balance = getSession().createQuery(hql2, Integer.class).setParameter(0, username).uniqueResult();
		if(balance < price){
			throw new UserAccountException("余额不足!");
		}
		String hql = "update Account set balance = balance - ?0 where username = ?1";
		getSession().createQuery(hql).setParameter(0, price).setParameter(1, username) .executeUpdate();
	}

}
