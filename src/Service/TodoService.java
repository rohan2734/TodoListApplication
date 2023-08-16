package Service;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import Repository.CompanyTodo;
import Repository.GroceriesTodo;
import Repository.ShoppingTodo;
import Repository.Todo;
import Utils.TodoNotFoundException;

public class TodoService {

//	private SortedMap<Integer,HashMap<Integer,Todo>> sortedTodoList = new TreeMap<>();
	private TreeMap<Integer, HashMap<Integer, Todo>> sortedTodoList = new TreeMap<>();
	private HashMap<Integer, Todo> finishedTodoList = new HashMap<>();
	private int id = 0;

	public void showTodoList() {
		if (sortedTodoList.size() == 0) {
			System.out.println("you dont have any Todos, just sit and chill! :) , or add new todos");
			return;
		}
		Set s = sortedTodoList.entrySet();
		Iterator i = s.iterator();
		while (i.hasNext()) {
			Map.Entry m = (Map.Entry) i.next();

			Integer key = (Integer) m.getKey();

			HashMap<Integer, Todo> value = (HashMap<Integer, Todo>) m.getValue();
			System.out.println("Priority : " + key + " Todo : " + value);
		}
	}

	public void showFinishedTodoList() {
		if (finishedTodoList.size() == 0) {
			System.out.println("you dont have any finished Todos, finish todos and come back :)");
			return;
		}

		Iterator ftlIterator = finishedTodoList.entrySet().iterator();
		while (ftlIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) ftlIterator.next();

			Integer todoId = (Integer) mapElement.getKey();
			Todo todo = (Todo) mapElement.getValue();

			System.out.println("TodoId : " + todoId + " : " + todo);

		}

	}

	public void addTodo(String todoContent, int priority) {

		System.out.println("Choose the Todo you want to add");
//		System.out.println("1. Simple Todo");
		System.out.println("1. Shopping Todo");
		System.out.println("2. Groceries Todo");
		System.out.println("3. Company Todo");

		Scanner s = new Scanner(System.in);

		int todoChoice = 0;

		boolean validInput = false;

		while (!validInput) {
			try {
				System.out.println("Enter your choice here: ");
				todoChoice = s.nextInt();
				s.nextLine();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("invalid input try again");
				s.nextLine();
			}
		}

		Todo todo = null;

		switch (todoChoice) {
//		case 1:
//			todo = new Todo(todoContent);
//				 todo.setTodo();
//			break;
		case 1:
			// shoppingTodo
			todo = new ShoppingTodo(todoContent);
			todo.setTodo();
			break;

		case 2:
			// groceries todo
			todo = new GroceriesTodo(todoContent);
			todo.setTodo();
			break;

		case 3:
//				 //company todo
			todo = new CompanyTodo(todoContent);
			todo.setTodo();
			break;

		default:
			System.out.println("Please enter valid inputs");
			break;

		}
		HashMap<Integer, Todo> todoMap = new HashMap<>();
		todoMap.put(id, todo);

		sortedTodoList.put(priority, todoMap);
		System.out.println(todo + " added ");
		id += 1;

	}

	public void editTodo(int todoId, String todoContent, int priority) throws TodoNotFoundException {
		if (sortedTodoList.containsKey(priority)) {
			HashMap<Integer, Todo> todoMap = sortedTodoList.get(priority);

			if (todoMap.containsKey(todoId)) {
				Todo todo = todoMap.get(todoId);
				todo.setContent(todoContent);

				todo.setTodo();

				todoMap.put(todoId, todo);
			} else {
				String errorMessage = "Todo with " + todoId + "doesnt exist, try again! :(";
//				System.out.println("Todo with " + todoId + "doesnt exist, try again! :(");
				throw new TodoNotFoundException(errorMessage);

			}
			sortedTodoList.remove(priority);

			sortedTodoList.put(priority, todoMap);

		} else {
			System.out.println("Todo with " + priority + "  doesnt exist");
			String errorMessage = "Todo with " + priority + "doesnt exist, try again! :(";
			throw new TodoNotFoundException(errorMessage);
		}
	}

	public void finishTodo(int todoId, int priority) throws TodoNotFoundException {
		if (sortedTodoList.containsKey(priority)) {
			HashMap<Integer, Todo> todoMap = sortedTodoList.get(priority);

			if (todoMap.containsKey(todoId)) {
				finishedTodoList.put(todoId, todoMap.get(todoId));

				if (sortedTodoList.containsKey(priority)) {
					sortedTodoList.remove(priority);
				}

				System.out.println("finished todo " + todoMap.get(todoId) + " succesfully");

			} else {

				String errorMessage = "Todo with " + todoId + "doesnt exist, try again! :(";
//				System.out.println("Todo with " + todoId + "doesnt exist, try again! :(");
				throw new TodoNotFoundException(errorMessage);
			}
		} else {
			System.out.println("Todo with " + priority + "  doesnt exist");
			String errorMessage = "Todo with " + priority + "doesnt exist, try again! :(";
			throw new TodoNotFoundException(errorMessage);
		}
	}

	public void unFinishTodo(int todoId, int priority) throws TodoNotFoundException {
		if (finishedTodoList.containsKey(todoId)) {
			Todo todo = finishedTodoList.get(todoId);

			// remove todo from finishedtodoList
			finishedTodoList.remove(todoId);
			// put todo inot sortedTodoList with priority
			HashMap<Integer, Todo> todoMap = new HashMap<>();
			todoMap.put(todoId, todo);
			sortedTodoList.put(priority, todoMap);

			System.out.println("unfinished todo " + sortedTodoList.get(priority) + " successfully ");
		} else {
			String errorMessage = "Todo with " + todoId + "doesnt exist, try again! :(";
//			System.out.println("Todo with " + todoId + "doesnt exist, try again! :(");
			throw new TodoNotFoundException(errorMessage);
		}
	}

	public void deleteTodo(int todoId, int priority) throws TodoNotFoundException {

		if (sortedTodoList.containsKey(priority)) {

			HashMap<Integer, Todo> todoMap = sortedTodoList.get(priority);

			if (todoMap.containsKey(todoId)) {
				sortedTodoList.remove(priority);
				todoMap.remove(todoId);
			} else {
				String errorMessage = "Todo with " + todoId + "doesnt exist, try again! :(";
				throw new TodoNotFoundException(errorMessage);
			}

		} else {
//			System.out.println("Todo with " +priority +"  doesnt exist");
			String errorMessage = "Todo with " + priority + "doesnt exist, try again! :(";
			throw new TodoNotFoundException(errorMessage);
		}

	}

	public void deleteAllTodos() {
		showTodoList();

		sortedTodoList.clear();

		System.out.println("All Todos are deleted");

	}

	public void deleteAllFinishedTodos() {
		showFinishedTodoList();

		finishedTodoList.clear();

		System.out.println("All Finished Todos are deleted");

	}

	public void printMenu() {
		System.out.println("============Menu======================");
		System.out.println("1. Show All Todos ====================");
		System.out.println("2. Show Finished Todos ===============");
		System.out.println("3. Add Todo ==========================");
		System.out.println("4. Edit Todo =========================");
		System.out.println("5. Finish a Todo =====================");
		System.out.println("6. UnFinish a Todo ===================");
		System.out.println("7. Delete a Todo =====================");
		System.out.println("8. Clear All Todos ===================");
		System.out.println("9 Clear All Finished Todos ==========");
		System.out.println("0. Quit===============================");
	}

	public void manageTodos() {

		System.out.println("Welcome to Rohan's TodoList Application");

		Scanner s = new Scanner(System.in);
		String todoContent = "";
		int choice = -1;
		int todoId = 0;
		int priority = 0;

		do {
			// print menu
			printMenu();

			boolean validInput = false;

			while (!validInput) {
				try {
					System.out.println("Enter your choice here: ");
					choice = s.nextInt();
					s.nextLine();
					validInput = true;
				} catch (InputMismatchException e) {
					System.out.println("invalid input try again");
					s.nextLine();
				}
			}

			switch (choice) {
			case 1:
				showTodoList();
				break;

			case 2:
				showFinishedTodoList();
				break;

			case 3:
				// add todo
				System.out.println("Enter you todo here to add");
				todoContent = s.nextLine();// next line takes entire line, not just word

				System.out.println("Enter your Todo priority here");
				priority = s.nextInt();

				addTodo(todoContent, priority);
				break;

			case 4:
				// edit todo
				System.out.println("enter the todo id you want to edit");
				todoId = s.nextInt();
				s.nextLine();// to consume \n

				System.out.println("Enter your priority here");
				priority = s.nextInt();
				s.nextLine();// to consume \n

				System.out.println("enter the content of updated todo here");
				todoContent = s.nextLine();// next line takes entire line, not just word

				try {
					editTodo(todoId, todoContent, priority);
				} catch (TodoNotFoundException e) {
					System.out.println(e);
				}

				break;

			case 5:
				// finish todo
				System.out.println("enter the todo id you want to finish");
				todoId = s.nextInt();
				s.nextLine();// to consume \n

				System.out.println("Enter your priority here");
				priority = s.nextInt();
				s.nextLine();// to consume \n

				try {
					finishTodo(todoId, priority);
				} catch (TodoNotFoundException e) {
					System.out.println(e);
				}

				break;

			case 6:
				// unfinish todo
				System.out.println("enter the todo id you want to unfinish");
				todoId = s.nextInt();
				s.nextLine();// to consume \n

				System.out.println("Enter your priority here");
				priority = s.nextInt();
				s.nextLine();// to consume \n

				try {
					unFinishTodo(todoId, priority);
				} catch (TodoNotFoundException e) {
					System.out.println(e);
				}

				break;

			case 7:
				// delete a todo
				System.out.println("enter the todo id you want to delete");
				todoId = s.nextInt();

				System.out.println("Enter your priority here");
				priority = s.nextInt();

				try {
					deleteTodo(todoId, priority);
				} catch (TodoNotFoundException e) {
					System.out.println(e);
				}

				break;

			case 8:
				// delete all todos
				deleteAllTodos();
				break;

			case 9:
				// delete all finished todos
				deleteAllFinishedTodos();
				break;

			case 0:
				System.out.println("Thankyou for using Rohan's todo Application,come back soon! :) ");
				break;

			default:
				System.out.println("Invalid Input choise, please choose the choice fromm the menu");
				break;

			}

		} while (choice != 0);

	}

	public TreeMap<Integer, HashMap<Integer, Todo>> getSortedTodoList() {
		return this.sortedTodoList;

	}
}
