package application;

import java.time.LocalDate;
import java.util.Date;

public class AVLSNode {

	LocalDate element; // store data
	AVLSNode left; // left child
	AVLSNode right; // right child
	int height; // Height
	public Stack Stack=new Stack();
	
	public AVLSNode(LocalDate element) {
		this(element, null, null);
	}

	public AVLSNode(LocalDate element, AVLSNode left, AVLSNode right) {
		this.element = element;
		this.left = left;
		this.right = right;
		this.height = 0;
	}
	public Stack getStack() {
		return Stack;
	}

}
