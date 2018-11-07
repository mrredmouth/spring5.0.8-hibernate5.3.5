package com.ccg.springhibernate;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccg.springhibernate.dao.BookShopDao;
import com.ccg.springhibernate.service.BookShopService;
import com.ccg.springhibernate.service.CashierService;

public class TestSpringHibernate {

	private ApplicationContext ctx = null;
	private BookShopDao bookShopDao;
	private BookShopService bookShopService;
	private CashierService cashierService;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopDao = (BookShopDao) ctx.getBean("bookShopDao");
		bookShopService = (BookShopService) ctx.getBean("bookShopService");
		cashierService = (CashierService) ctx.getBean("cashierService");
	}
	
	/**
	 * 如果配置了Hibernate的SessionFactory，则再次运行时会生成SessionFactory的bean实例，
	 * 数据库会创建映射文件的表（oracle方言创建）：SH_ACCOUNT，SH_BOOK
	 * 同时创建了native自增的序列：create sequence hibernate_sequence start with 1 increment by  1
	 * @throws SQLException
	 */
	@Test
	public void testDataSource() throws SQLException{
		DataSource dataSource = (DataSource)ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testBookShopDao(){
		System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
	}
	@Test
	public void testSHPurchase(){
		/*expression="execution(* com.ccg.springhibernate.service.*.*(..))"
			给service包下面的所有方法应用事务，事务里面给purchase方法加了
			所以不会出现书少了，钱没少
		*/
		bookShopService.purchase("aa", "1001");
	}
	/**
	 * 测试事务的传播行为
	 */
	@Test
	public void testSHCheckout(){
		cashierService.checkout("aa", Arrays.asList("1001","1002"));
	}
}
