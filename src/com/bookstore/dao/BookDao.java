package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Book;

public class BookDao extends JpaDao<Book> implements GenericDao<Book> {

	public BookDao(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}

	@Override
	public void delete(Object bookId) {
		super.delete(Book.class, bookId);
	}

	@Override
	public Book get(Object bookId) {
		return super.find(Book.class, bookId);
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery( "Book.findAll");
	}

	@Override
	public long count() {
		return (long)super.countWithNamedQuery("Book.countAll");
	}

	public Book findByTitle(String book) {
		List<Book> listbook=findWithNamedQuery("Book.findByTitle", "title", book);
		if(!listbook.isEmpty()){
			return listbook.get(0);
		}
		return null;
	}

}
