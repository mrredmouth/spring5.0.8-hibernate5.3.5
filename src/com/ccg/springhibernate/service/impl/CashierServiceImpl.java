package com.ccg.springhibernate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccg.springhibernate.service.BookShopService;
import com.ccg.springhibernate.service.CashierService;

@Service("cashierService")
public class CashierServiceImpl implements CashierService {

	@Autowired
	private BookShopService bookShopService;
	@Override
	public void checkout(String username, List<String> isbns) {
		for(String isbn:isbns){
			bookShopService.purchase(username, isbn);
		}
	}

}
