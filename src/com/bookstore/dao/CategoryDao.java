package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDao extends JpaDao<Category> implements GenericDao<Category> {

	public CategoryDao(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Category create(Category entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public Category update(Category entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public void delete(Object obj) {
		super.delete(Category.class, obj);
	}

	@Override
	public Category get(Object id) {
		// TODO Auto-generated method stub
		return super.find(Category.class,id);
	}

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Category.countAll");
	}
	
	public Category findByName(String name){
		List<Category> catList=super.findWithNamedQuery("Category.findByName","name",name);
		if(catList!=null && catList.size()>0){
			return catList.get(0);
		}
		return null;
	}

}
