package application;


public class Queue {
	
	
	private LinkedList queue = new LinkedList();
	public int size=0;
	
	public Queue() {
		queue=new LinkedList();
	}
	
	
	public void enQueue(Object x) {
		queue.addLast(x);
		size++;
		
	}
	public Object deQueue() {
		LNode cur=queue.getFirst();
		Object x = queue.getFrist();
		queue.removeFirst();
		size--;
		return x;
	}
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	public Object peak() {
		
		return queue.getFrist();
	}
	
	@Override
	public String toString() {
		return queue.toString();
	}

}
