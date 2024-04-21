package application;


public class LinkedList {
	private LNode first;
	private LNode last;
	public int count = 0;

	public LinkedList() {

	}

	
	public boolean isEmpty() {
		if(count==0)
			return true;
		return false;
	}
	public Object getFrist() {
		if (count != 0)
			return first.element;
		else
			return null;
	}

	public Object getLast() {
		if (count != 0)
			return last.element;
		else
			return null;

	}
	public LNode getFirst() {
		
		if(count!=0)
			return first;
		else
			return null;
	}

	public void addFirst(Object  x) {
		if (count == 0)
			first = last = new LNode(x);
		else {
			LNode temp = new LNode(x);
			temp.next = first;
			first = temp;
		}
		count++;
	}

	public void add(Object x, int index) {
		if (index == 0)
			addFirst(x);
		else if (index >= count)
			addLast(x);
		else {
			LNode temp = new LNode(x);
			LNode curr = first;
			for (int i = 0; i < index - 1; i++)
				curr = curr.next;

			temp.next = curr.next;
			curr.next = temp;
			count++;

		}

	}

	public void addLast(Object x) {
		if (count == 0)
			first = last = new LNode(x);
		else {
			LNode temp = new LNode(x);
			last.next = temp;
			last = temp;
		}
		count++;
	}

	public boolean removeFirst() {
		if (count == 0)
			return false;
		else {
			if (count == 1)
				first = last = null;
			else
				first = first.next;
			count--;
			return true;
		}

	}

	public boolean removeObject(Object x) {// remove from give element and you need to remove the node who have this
		// element

		if (count == 0)
			return false;
		else {
			if (first.element.equals(x) == true) {
				return removeFirst();
			}
			else {
				LNode previous = first;
				LNode current = first;
				while (current != null && current.element.equals(x) == false) {
					previous = current;
					current = current.next;
				}
				if (current != null) {
					previous.next = current.next;
					count--;
					return true;
				} else
					return false;
			}

		}
	}
	
	
	public Object get(int index) {
		if (count == 0)
			return null;
		else if (index == 0)
			return getFrist();
		else if (index == count - 1)
			return getLast();
		else if (index > 0 && index < count - 1) {
			LNode current = first;
			for (int i = 0; i < index; i++)
				current = current.next;
			return current.element;
		} else
			return null;
	}

	@Override
	public String toString() {
		if (count == 0) {
			return "";
		}
		String s = "";
		LNode current = first;
		while (current != null) {
			s += current.element + "\n";
			current = current.next;
		}
		return s;
	}


	

}
