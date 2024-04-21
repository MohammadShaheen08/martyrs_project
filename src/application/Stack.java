package application;

public class Stack {
	private static LinkedList stack = new LinkedList();
	public static int count=0;

	public void push(Object data) {
		count++;
		stack.addFirst(data);
	}

	public static Object pop() {
		
		LNode ob=stack.getFirst();
		 stack.removeFirst();
		 count--;
		 return ob.element;
	}

	public Object peek() {
		return stack.getLast();
	}

	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	 
	
	
	public void clear() {
		stack = new LinkedList();
	}
	
	@Override
	public String toString() {
		return stack.toString();
	}

}