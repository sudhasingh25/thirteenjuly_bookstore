package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UsersDao extends JpaDao<Users> implements GenericDao<Users> {

	private EntityManager entityManager;
	
	
	public UsersDao(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public Users create(Users entity) {
		return super.create(entity);
	}

	@Override
	public Users update(Users entity) {
		return super.update(entity);
	}

	@Override
	public Users get(Object id) {
		return super.find(Users.class,id);
	}
	
	public void delete(Object id){
		super.delete(Users.class,id);
	}

	public List<Users> listAll(){
		return super.findWithNamedQuery("Users.findAll");
	}
	
	public long count(){
		return (long)super.countWithNamedQuery("Users.countAll");
	}
	
	public Users findByEmail(String email){
		List<Users> listusr=super.findWithNamedQuery("Users.findByEmail","email", email);
		
		if(listusr != null && listusr.size()>0){
			return listusr.get(0);
		}
		return null;
	}
	
	public boolean checkLogin(String email,String password){
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);
		
		List<Users> listuser=super.findWithNamedQuery("Users.checkLogin", parameters);
		
		if(listuser.size()==1){
			return true;
		}
		return false;
	}
}
