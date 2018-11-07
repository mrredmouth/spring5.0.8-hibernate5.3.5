package com.ccg.springhibernate.dao;

public interface BookShopDao {

	//根据书号isbn获取书的单价
	public int findBookPriceByIsbn(String isbn);
	
	//更新书的库存：库存-1
	public void updateBookStock(String isbn);
	
	//更新账户余额：账户余额减去price
	public void updateUserAccount(String username,int price);
}
