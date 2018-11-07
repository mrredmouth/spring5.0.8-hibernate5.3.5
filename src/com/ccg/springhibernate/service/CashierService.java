package com.ccg.springhibernate.service;

import java.util.List;

public interface CashierService {

	/**
	 * 多本书一起买，测试事务的传播行为
	 * @param username
	 * @param isbns
	 */
	public void checkout(String username,List<String> isbns);
}
