package com.sona.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sona.main.TodoApplication;
import com.sona.main.entity.Todo;
import com.sona.main.repository.TodoRepository;

@Service
public class TodoService {

    private final TodoApplication todoApplication;

	@Autowired
	private TodoRepository repository;

    TodoService(TodoApplication todoApplication) {
        this.todoApplication = todoApplication;
    }
	
	public void saveTodo(Todo todo) {
		repository.save(todo);
	}
	
	public List<Todo> getAllTodos(){
		return repository.findAll();
	}
	
	public void updateTodo(Long id, Todo newTodo) {
		Optional<Todo> oldtodoBox = repository.findByID(id);
		if(oldtodoBox.isPresent()) {
			Todo oldTodo = oldtodoBox.get();
			oldTodo.setTaskContent(newTodo.getTaskContent());
			oldTodo.setCompletionStatus(newTodo.getCompletionStatus());
			repository.updateTodo(oldTodo);
		}
	}
	
	public void deleteTodo( Long id) {
		repository.delete(id);
	}

}
