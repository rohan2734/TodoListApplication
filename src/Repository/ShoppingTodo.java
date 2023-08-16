package Repository;

import java.util.Scanner;

public class ShoppingTodo extends Todo {

	private int quantity;
	public static Scanner s = new Scanner(System.in);

	public ShoppingTodo(String content) {
		super(content);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setTodo() {
		System.out.println("enter the quantity of items you  want to buy");
		int quantity = s.nextInt();
		s.nextLine();
		setQuantity(quantity);

	}

	@Override
	public String toString() {
		return "Todo[ content : " + content + " quantity : " + quantity + " ] ";
	}

}
