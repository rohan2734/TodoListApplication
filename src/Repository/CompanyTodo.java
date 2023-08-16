package Repository;

import java.time.LocalDate;
import java.util.Scanner;

public class CompanyTodo extends Todo {
	private LocalDate byDate;

	public static Scanner s = new Scanner(System.in);

	public CompanyTodo(String content) {
		super(content);
		// TODO Auto-generated constructor stub
//		this.byDate = byDate;

	}

	public LocalDate getByDate() {
		return byDate;
	}

	public void setByDate(LocalDate byDate) {
		this.byDate = byDate;
	}

	public void setTodo() {

		System.out.println("enter the date by which you want to complete");
		String byDateString = s.nextLine();
//		 s.nextLine();
		LocalDate byDate = LocalDate.parse(byDateString);
		setByDate(byDate);
	}

	@Override
	public String toString() {
		return "Todo[ content : " + content + " byDate : " + byDate + " ] ";
	}

}
