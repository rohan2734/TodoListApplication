package Service;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Repository.User;

public class UserService{

	public  HashMap<String,User> usersMap = new HashMap<>();
//	public TodoService todoService = null;

	
	public  void printMenu() {
		System.out.println("here is the menu: ");
		System.out.println("1. New User ? Signup");
		System.out.println("2. Already exisitng user ? Login");
		System.out.println("3. Quit");
		System.out.println("enter your choice here:");
		
	}
	
	public  boolean signupUser(String email,String password,String confirmPassword) {
		Pattern pattern = Pattern.compile("^(.+)@(.+)$",Pattern.CASE_INSENSITIVE);
		Matcher matcher  = pattern.matcher(email);
		
		if(usersMap.containsKey(email)) {
			System.out.println("user already exists");
			return false;
		}
		
		if(!matcher.find()) {
			System.out.println("email is invalid");
			return false;
		}
				
		if(!password.equals(confirmPassword)) {
			System.out.println("password and confirm password are not same! :( ");
			return false;
		}
		usersMap.put(email,new User(email,password));

		
		return true;
	}
	
	public  boolean loginUser(String email, String password) {
		boolean hasLoggedIn=false;
		
		if(usersMap.containsKey(email)) {
			if(password.equals(usersMap.get(email).getPassword())) {
				return true;
			}else {
				System.out.println("you have given wrong credentials, try again! :( ");
				return false;
			}
		}else{
			System.out.println("email doesnt exist, check the email entered or signup if you are a new user ");
			return false;
			
		}
		
	}
	
	public User authenticateUser() {
		Scanner s = new Scanner(System.in);
		
		int choice=0;
		do {
			 //print menu
			 printMenu();

			 boolean validInput=false;
			 
			 while(!validInput) {
				 try {
				    	choice=s.nextInt();
				    	s.nextLine();
				    	validInput=true;
				    }catch(InputMismatchException e) {
				    	System.out.println("invalid input try again");
				    	s.nextLine();
				    }
			 }
			 
			 
			 switch(choice) {
			 	case 1:
			 		
			 		boolean isValidEmail = false;
			 		while(!isValidEmail) {
			 			
			 			System.out.println("Enter your email");
				 		String email = s.nextLine();
				 		System.out.println("enter password");
				 		String password = s.nextLine();
				 		System.out.println("enter confirm password");
				 		String confirmPassword = s.nextLine();

			 			isValidEmail = signupUser(email,password,confirmPassword);
			 			if(isValidEmail) {
			 				System.out.println("user has signed up successfully");
			 				return usersMap.get(email);
			 			}
			 			
			 			
			 		}
			 					 		
			 		
			 		break;
			 		
			 	case 2:
			 		System.out.println("enter your email");
			 		String email = s.nextLine();
			 		System.out.println("enter password");
			 		String password = s.nextLine();
			 		
			 		boolean hasLoggedIn=false;
			 		
			 		hasLoggedIn = loginUser(email,password);
			 		
			 		if(hasLoggedIn) {
			 			System.out.println("you have logged in successfully! :)");
			 			return usersMap.get(email);//return user 
			 		}
			 		
			 		break;
			 		
			 	case 3:
			 		System.out.println("thanks for using TodoListApplication,see you soon! :) ");
			 		return new User("QUIT","QUIT");
//			 		break;
			 		
			 	default:
			 		System.out.println("Please give valid inputs from the menu only");
			 		break;
			 }
		}while(choice!=3);
		
		return null;
	}
	
	public HashMap<String,User> getUsersMap() {
		return this.usersMap;
	}

	public void manageTodoService(User user) {
		TodoService todoService=null;
		
		if(user.getTodoService()==null) {
			 todoService= new TodoService();
			user.setTodoService(todoService);
		}else {
			todoService=user.getTodoService();
		}
		
		 
		todoService.manageTodos();
		
		user.setSortedTodoList(todoService.getSortedTodoList());
		
		
	}
	
	
}

	

