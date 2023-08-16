package Repository;

import java.util.Scanner;

public class GroceriesTodo extends Todo {
	private float quantity;
	public static Scanner s = new Scanner(System.in);

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public GroceriesTodo(String content) {
		super(content);
		// TODO Auto-generated constructor stub
//		this.quantity=quantity;
	}

	public void setTodo() {
		System.out.println("enter the quantity of items you  want to buy");
		float quantity = s.nextFloat();
		s.nextLine();
		setQuantity(quantity);

	}

	@Override
	public String toString() {
		return "Todo[ content : " + content + " quantity : " + quantity + " ] ";
	}

}
