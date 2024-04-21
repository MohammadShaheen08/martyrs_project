package application;

import java.time.LocalDate;
import java.util.Date;

import javafx.scene.control.TextArea;

public class AVLStack {

	
		public AVLSNode root;

		public AVLStack() {
			root = null;
		}

		private int height(AVLSNode e) {
			if (e == null)
				return -1;
			return e.height;
		}

		private AVLSNode rotateWithLeftChild(AVLSNode k2) {
			AVLSNode k1 = k2.left;
			k2.left = k1.right;
			k1.right = k2;
			k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
			k1.height = Math.max(height(k1.left), k2.height) + 1;
			return k1;
		}

		private AVLSNode rotateWithRightChild(AVLSNode k1) {
			AVLSNode k2 = k1.right;
			k1.right = k2.left;
			k2.left = k1;
			k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
			k2.height = Math.max(height(k2.right), k1.height) + 1;
			return k2;
		}

		private AVLSNode DoubleWithLeftChild(AVLSNode k3) {
			k3.left = rotateWithRightChild(k3.left);
			return rotateWithLeftChild(k3);
		}

		private AVLSNode DoubleWithRightChild(AVLSNode k1) {
			k1.right = rotateWithLeftChild(k1.right);
			return rotateWithRightChild(k1);
		}

		public void insert(LocalDate localDate) {
			root = insert(localDate, root);
		}

		private AVLSNode insert(LocalDate element, AVLSNode node) {
			if (node == null) {
				return new AVLSNode(element);
			}

			if (element.isBefore(node.element)) {
				node.left = insert(element, node.left);
			} else if (element.isAfter(node.element) ) {
				node.right = insert(element, node.right);
			} else {
				// Duplicate elements are not allowed in AVL trees
				return node;
			}

			node.height = Math.max(height(node.left), height(node.right)) + 1;

			return balance(node);
		}

	

		public void remove(LocalDate element) {
			root = remove(element, root);
		}

		private AVLSNode remove(LocalDate element, AVLSNode node) {
			if (node == null) {
				return null;
			}

			if (element.compareTo(node.element) < 0) {
				node.left = remove(element, node.left);
			} else if (element.compareTo(node.element) > 0) {
				node.right = remove(element, node.right);
			} else {
				// Found the node to be removed
				if (node.left == null && node.right == null) {
					// Case 1: Node is a leaf
					return null;
				} else if (node.left == null) {
					// Case 2: Node has only a right child
					return node.right;
				} else if (node.right == null) {
					// Case 2: Node has only a left child
					return node.left;
				} else {
					// Case 3: Node has both left and right children
					AVLSNode successor = findMinimum(node.right);
					node.element = successor.element;
					node.right = remove(successor.element, node.right);
				}
			}

			node.height = Math.max(height(node.left), height(node.right)) + 1;

			return balance(node);
		}

		private AVLSNode findMinimum(AVLSNode node) {
			if (node.left == null) {
				return node;
			}
			return findMinimum(node.left);
		}

		private AVLSNode balance(AVLSNode node) {
			if (node == null) {
				return null;
			}

			int balanceFactor = getBalanceFactor(node);

			if (balanceFactor > 1) {
				if (getBalanceFactor(node.left) >= 0) {
					// Left-Left case
					return rotateWithLeftChild(node);
				} else {
					// Left-Right case
					return DoubleWithLeftChild(node);
				}
			} else if (balanceFactor < -1) {
				if (getBalanceFactor(node.right) <= 0) {
					// Right-Right case
					return rotateWithRightChild(node);
				} else {
					// Right-Left case
					return DoubleWithRightChild(node);
				}
			}

			return node;
		}
		
		
		public boolean isEmpty() {
			return root == null;
		}
		
		public void printTree() {
			if (isEmpty())
				System.out.println("Empty tree");
			else
				printTree(root);

		}
		public AVLSNode find(LocalDate x) {
			return find(x, root);

		}

		private AVLSNode find(LocalDate x, AVLSNode t) {
			while (t != null) {
				if (x.isBefore(t.element))
					t = t.left;
				else if (x.isAfter(t.element))
					t = t.right;
				else
					return t; // Match

			}
			return null; // No match
		}

		
		
		 
		private void printTree(AVLSNode t) // inorder traversal
		{
			if (t != null) {
				
				printTree(t.left);
				
				printTree(t.right);
				System.out.println("   " + t.element );
			}
		}

		private int getBalanceFactor(AVLSNode node) {
			if (node == null) {
				return 0;
			}
			return height(node.left) - height(node.right);
		}
		
		public void printDatesBackward(TextArea textArea) {
			
			printDatesBackward(root,textArea);
			
		}
		private void printDatesBackward(AVLSNode node, TextArea textArea) {
		    if (node != null) {
		       
		    	
		    	 printDatesBackward(node.left, textArea);
		    	textArea.appendText(node.element.toString()+"\n" + node.getStack().toString()+"\n");
		        printDatesBackward(node.right, textArea);
		        
		     
		        
		        
		      
		        printDatesBackward(node.left, textArea);
		        
		    }
		}
		private int height(AvlNode e) {
			if (e == null)
				return -1;
			return e.height;
			
			
			
		}
		public int max() {
			return max(root,0);
			
		}
		
		private int max(AVLSNode node, int max) {
	        if (node == null)
	            return max;

	       
	        max = max(node.left, max);

	        
	        if (node.getStack().count > max)
	            max = node.getStack().count;

	        // Traverse the right subtree
	        max = max(node.right, max);

	        return max;
	    }
		
		int max=0;
		LocalDate maxDate;
		public LocalDate findMaximum() {
			
			return findMaximum(root,maxDate);
		}
		
		 LocalDate findMaximum(AVLSNode node,LocalDate maxDate) {
		        if (node == null)
		            return maxDate;

		        // Traverse the left subtree
		        maxDate = findMaximum(node.left, maxDate);

		        // Compare the current node with the maxDate
		        if (node.getStack().count>max)
		            maxDate = node.element;

		        // Traverse the right subtree
		        maxDate = findMaximum(node.right, maxDate);

		        return maxDate;
		    }
		  public int displayHeight() {
		        int height = height(root);
		        return height;
		    }

		// ... additional methods ...
	}


