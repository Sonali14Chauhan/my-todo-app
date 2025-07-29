package com.sona.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sona.main.entity.Todo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class TodoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void save(Todo todo) {
		entityManager.persist(todo);
	}
	
	public List<Todo> findAll(){
		TypedQuery<Todo> typedQuery = entityManager.createQuery("from Todo", Todo.class);
		return  typedQuery.getResultList();
	}
	
	public Optional<Todo> findByID(Long Id){
		Todo todo = entityManager.find(Todo.class, Id);
		return Optional.ofNullable(todo);
	}
	
	@Transactional
	public void updateTodo(Todo todo) {
		entityManager.merge(todo);
	}
	
	@Transactional
	public void delete(Long id) {
		Todo todo = entityManager.find(Todo.class, id);
		if(todo != null) {
			entityManager.remove(todo);
		}
	}
	
}
