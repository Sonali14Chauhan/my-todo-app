package com.sona.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sona.main.entity.Todo;
import com.sona.main.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	private TodoService service;
	
	@RequestMapping("/")
	public String getAllTask(Model model) {
		List<Todo> ListofTodo = service.getAllTodos();
		model.addAttribute("ListofTodo", ListofTodo);
		return "task";

	}
	
	@RequestMapping(value = "/addtodo", method = RequestMethod.POST)
	public String createTask(@ModelAttribute Todo todo) {
		 service.saveTodo(todo);
		 return "redirect:/";

	}
	
	
	@RequestMapping(value="/updatetodo/{id}")
	public String updateTask(@PathVariable("id") Long id , @ModelAttribute Todo todo) {
		service.updateTodo(id, todo);
		return "redirect:/";

	}

	@RequestMapping("/deletetodo/{id}")
	public String deleteTask(@PathVariable("id") Long id) {
		service.deleteTodo(id);
		return "redirect:/";

	}

	
}
