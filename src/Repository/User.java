package Repository;

import java.util.HashMap;
import java.util.TreeMap;

import Service.TodoService;

public class User {

	private String email;
	private String password;
//	private HashMap<Integer,Todo> todoList;
	private TreeMap<Integer, HashMap<Integer, Todo>> sortedTodoList;
	private TodoService todoService;

	public User(String email, String password) {
		this.email = email;
		this.setPassword(password);
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSortedTodoList(TreeMap<Integer, HashMap<Integer, Todo>> sortedTodoList) {
		this.sortedTodoList = sortedTodoList;
	}

	public TreeMap<Integer, HashMap<Integer, Todo>> getSortedTodoList() {
//		return this.todoList;
		return this.sortedTodoList;

	}

	public TodoService getTodoService() {
		return todoService;
	}

	public void setTodoService(TodoService todoService) {
		this.todoService = todoService;
	}

	public String toString() {
		return "User [ email : " + email + " password : " + password + " ] ";
	}

}
