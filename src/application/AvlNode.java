package application;


public class AvlNode {

	Martyers element; // store data
	AvlNode left; // left child
	AvlNode right; // right child
	int height; // Height

	public AvlNode(Martyers element) {
		this(element, null, null);
	}

	public AvlNode(Martyers element, AvlNode left, AvlNode right) {
		this.element = element;
		this.left = left;
		this.right = right;
		this.height = 0;
	}

}
