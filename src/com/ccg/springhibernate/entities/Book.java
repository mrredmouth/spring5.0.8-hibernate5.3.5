package com.ccg.springhibernate.entities;

import lombok.Data;

@Data
public class Book {
	
	private Integer id;
	private String bookName;
	private String isbn;
	private int price;
	private int stock;
	
}
