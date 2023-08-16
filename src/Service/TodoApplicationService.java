package Service;

import java.util.Scanner;

import Repository.User;

public class TodoApplicationService {

//	public static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Welcome to Rohan's TodoList Application :) ");

		Scanner s = new Scanner(System.in);
		int choice = 0;

		UserService userService = new UserService();

		do {
			System.out.println("Select a option from the menu");
			System.out.println("1. Login");
			System.out.println("2. Quit");
			choice = s.nextInt();
			switch (choice) {
			case 1:

				User loggedInUser = null;

				while (loggedInUser == null) {
					loggedInUser = userService.authenticateUser();
				}

				boolean authenticationStatus = true;

				if (loggedInUser.getEmail().equals("QUIT")) {
					authenticationStatus = false;
				}

				if (authenticationStatus) {
					userService.manageTodoService(loggedInUser);

					System.out.println(loggedInUser.getSortedTodoList());

				}
				break;

			case 2:
				System.out.println("Bye :) ");
				break;

			default:
				System.out.println("enter valid option");
				break;
			}

		} while (choice != 2);

	}

}
