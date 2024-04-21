package application;


public class DNode {
	public Object element;
	public DNode prev, next;
	private AVL1 AVL1=new AVL1();
	private AVLStack AVLStack=new AVLStack();

	
	public DNode(Object element) {
		this(element, null, null);
		
	}

	public DNode(Object element, DNode prev, DNode next) {
		this.element = element;
		this.prev = prev;
		this.next = next;

	}

	public AVL1 getFirstTree() {
		return AVL1;
	}
	
	public AVLStack getStackTree() {
		return AVLStack;
	}
		
	
	
	
	

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public DNode getPrev() {
		return prev;
	}

	public void setPrev(DNode prev) {
		this.prev = prev;
	}

	public DNode getNext() {
		return next;
	}

	public void setNext(DNode next) {
		this.next = next;
	}
}
