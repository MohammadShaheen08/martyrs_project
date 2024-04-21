package application;


import java.lang.String;

public class DoubleList {

	public DNode first, last;
	private int count;

	public DoubleList() {
		first = last = null;
		count = 0;
	}

	public Object getFirst() {
		if (count == 0)
			return null;
		else
			return first.element;
	}

	public DNode getfirstNode() {
		return first;
	}

	public DNode getlastNode() {
		return last;
	}

	public Object getLast() {
		if (count == 0)
			return null;
		else
			return last.element;
	}

	public void clear() {
		first = last = null;
		count = 0;
	}

	public boolean isEmpty() {
		if (count == 0)
			return true;
		return false;
	}

	public Object get(int index) {
		if (count == 0)
			return null;
		else if (index == 0)
			return getFirst();
		else if (index == count - 1)
			return getLast();
		else if (index > 0 && index < count - 1) {
			DNode current = first;
			for (int i = 0; i < index; i++)
				current = current.next;
			return current.element;
		} else
			return null;
	}

	public int find(Object o) {
		for (int i = 0; i < count; i++) {
			if (get(i).equals(o)) {
				return i;
			}
		}
		return -1;
	}

	public DNode getFirstNode() {
		if (count == 0)
			return null;
		else
			return first;
	}

	public DNode getLastNode() {
		if (count == 0)
			return null;
		else
			return last;
	}

	public DNode getNode(int index) {
		if (count == 0)
			return null;
		else if (index == 0)
			return getFirstNode();
		else if (index == count - 1)
			return getLastNode();
		else if (index > 0 && index < count - 1) {
			DNode current = first;
			for (int i = 0; i < index; i++)
				current = current.next;
			return current;
		} else
			return null;
	}
	public DNode findNode( Object target) {
	    DNode current = first;

	    while (current != null) {
	        if (current.element.equals(target)) {
	            return current;  // Object found
	        }
	        current = current.next;
	    }

	    return null;  // Object not found
	}

	public void addLast(Object element) {
		if (count == 0) {
			first = last = new DNode(element);
		} else {

			DNode current = first;
			DNode newNode = new DNode(element);
			DNode temp = null;
			while (current != null && ((String) current.element).compareTo((String) newNode.element) < 0) {
				temp = current;
				current = current.next;

			}
			if (temp == null)

			{
				newNode.next = first;
				first.prev = newNode;
				first = newNode;
			} else if (current == null) {
				last.next = newNode;
				newNode.prev = last;
				last = newNode;

			}

			else {
				temp.next = newNode;
				newNode.next = current;
				current.prev = newNode;
				newNode.prev = temp;

			}

		}

		count++;
	}
	public void addLast(DNode newNode) {
		if (count == 0) {
			first = last = newNode;
		} else {

			DNode current = first;
			
			DNode temp = null;
			while (current != null && ((String) current.element).compareTo((String) newNode.element) < 0) {
				temp = current;
				current = current.next;

			}
			if (temp == null)

			{
				newNode.next = first;
				first.prev = newNode;
				first = newNode;
			} else if (current == null) {
				last.next = newNode;
				newNode.prev = last;
				last = newNode;

			}

			else {
				temp.next = newNode;
				newNode.next = current;
				current.prev = newNode;
				newNode.prev = temp;

			}

		}

		count++;
	}

	public void addFirst(Object element) {
		if (first == null) {
			first = last = new DNode(element);
		} else {
			DNode temp = new DNode(element);
			first.setPrev(temp);
			temp.setNext(first);
			first = temp;
		}
		count++;
	}

	public void add(int index, Object element) {

		if (index == 0)

			addFirst(element);

		else if (index >= count)

			addLast(element);

		else {

			DNode newDNode = new DNode(element);

			DNode curent = first;

			for (int i = 0; i < index - 1; i++)

				curent = curent.next;

			newDNode.next = curent.next;

			newDNode.prev = curent;

			curent.next = newDNode;

			newDNode.next.prev = newDNode;

			count++;

		}

	}

	public boolean removeLast() {
	    if (last != null) {
	        if (last.prev != null) {
	            last.prev.next = null;
	            last = last.prev;
	        } else {
	            // The list contains only one node
	            first = null;
	            last = null;
	        }
	        count--;
	        return true; // Removal successful
	    } else {
	        // The list is empty
	       
	        return false; // Removal unsuccessful
	    }
	}
	public boolean removeFirst() {
		if (count == 0) {
			return false;
		}
		if (count == 1) {
			first = last = null;
		} else {
			DNode c = first;
			first = first.getNext();
			first.setPrev(null);
			c = null;
		}
		count--;
		return true;
	}

	public boolean remove(int index) {
		System.out.println(count);
		DNode prev = first;
		if (count == 0)
			return false;
		if (index == 0) {
			return removeFirst();
		}
		if (index == count - 1) {
			return removeLast();
		}
		if (index <= 0 || index > count) {
			return false;
		} else {
			DNode current = first;
			for (int i = 0; i < index - 1; i++) {
				prev = current;
				current = current.getNext();
			}
			prev.setNext(current.getNext());
			current.getNext().setPrev(prev);
			count--;
			return true;
		}
	}

	public boolean remove(Object element) {
		if (count == 0)
			return false;
		if (element.equals(first.getElement()))
			return removeFirst();
		if (element.equals(last.getElement()))
			return removeLast();
		else {
			DNode current = first.getNext();
			for (int i = 1; i < count - 1; i++) {
				if (current.getElement().equals(element))
					return remove(i);
				current = current.getNext();
			}
			return false;
		}
	}
	public void update(String oldLocation, String newLocation) {
	    DNode curr = first;

	    while (curr != null) {
	        if (curr.element.equals(oldLocation)) {
	            curr.element = newLocation;

	            // Remove the node from the current position
	            removeNode(curr);

	            // Find the correct position to insert the node based on the new location
	            DNode insertPos = findInsertionPosition(curr);

	            // Insert the node at the correct position
	            insertAfter(insertPos, curr);

	            return; // Exit the method after updating the location and sorting the list
	        }

	        curr = curr.next;
	    }

	    // If the node with the given location is not found
	    System.out.println("Node not found in the list.");
	}

	private DNode findInsertionPosition(DNode node) {
	    DNode curr = first;

	    while (curr != null && ((String) curr.element).compareTo((String) node.element) < 0) {
	        curr = curr.next;
	    }

	    return (curr != null) ? curr.prev : null; // Return null if node should be inserted at the beginning
	}

	

	private void insertAfter(DNode prevNode, DNode newNode) {
	    if (prevNode != null) {
	        newNode.prev = prevNode;
	        newNode.next = prevNode.next;

	        if (prevNode.next != null) {
	            prevNode.next.prev = newNode;
	        } else {
	            last = newNode;
	        }

	        prevNode.next = newNode;
	    } else {
	        // Insert at the beginning of the list
	        newNode.prev = null;
	        newNode.next = first;

	        if (first != null) {
	            first.prev = newNode;
	        } else {
	            last = newNode;
	        }

	        first = newNode;
	    }
	}
	        // If the node with the given value is not found
	       	  public void removeNode(DNode node) {
	        // Check if the node is the head
	        if (node == first) {
	        	first = first.next;
	            if (first != null) {
	            	first.prev = null;
	            }
	            return;
	        }
	        
	        // Check if the node is the tail
	        if (node.next == null) {
	            node.prev.next = null;
	            return;
	        }
	        
	        // The node is somewhere in the middle
	        node.prev.next = node.next;
	        node.next.prev = node.prev;
	    }
	

	public boolean contains(Object o) {
		for (int i = 0; i < count; i++) {
			if (((String) get(i)).compareTo((String) o) == 0)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		if (count == 0) {
			return "";
		}
		DNode current = first;
		String s = "";
		while (current != null) {
			s += current.getElement() + "\n";

			current = current.getNext();
		}
		return s;
	}
	 public void printList(Object o) {
	        DNode current = first;

	        while (current != null) {
	            if (current.element.equals(o)) {
	                System.out.println(current.element);
	              
	                return; // Found the element and printed it, so we can exit the method
	            }
	            current = current.next;
	        }

	        System.out.println("Element not found in the list.");
	    }
	
}
