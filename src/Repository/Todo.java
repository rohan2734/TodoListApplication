package Repository;

public abstract class Todo {
	protected String content;

	public Todo(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Todo[ content : " + content + " ] ";
	}

//	public void setTodo() {
//		// TODO Auto-generated method stub
//		
//	}
	
	public abstract void setTodo();

}
