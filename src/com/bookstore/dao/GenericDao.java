package com.bookstore.dao;

import java.util.List;

public interface GenericDao<E> {
	public E create(E entity);

	public E update(E entity);

	public void delete(Object obj);

	public E get(Object id);

	public List<E> listAll();

	public long count();
}
