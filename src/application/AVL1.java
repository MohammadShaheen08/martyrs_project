package application;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

public class AVL1 {

	
		
		private AvlNode root;
		public int count=0;
		Queue queue=new Queue();

		public AVL1() {
			root = null;
		}
	

		private int height(AvlNode e) {
			if (e == null)
				return -1;
			return e.height;
		}
		  public int displayHeight() {
		        int height = height(root);
		        return height;
		    }

		private AvlNode rotateWithLeftChild(AvlNode k2) {
			AvlNode k1 = k2.left;
			k2.left = k1.right;
			k1.right = k2;
			k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
			k1.height = Math.max(height(k1.left), k2.height) + 1;
			return k1;
		}

		private AvlNode rotateWithRightChild(AvlNode k1) {
			AvlNode k2 = k1.right;
			k1.right = k2.left;
			k2.left = k1;
			k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
			k2.height = Math.max(height(k2.right), k1.height) + 1;
			return k2;
		}

		private AvlNode DoubleWithLeftChild(AvlNode k3) {
			k3.left = rotateWithRightChild(k3.left);
			return rotateWithLeftChild(k3);
		}

		private AvlNode DoubleWithRightChild(AvlNode k1) {
			k1.right = rotateWithLeftChild(k1.right);
			return rotateWithRightChild(k1);
		}

		public void insert(Martyers element) {
			count ++;
			root = insert(element, root);
		}

		private AvlNode insert(Martyers element, AvlNode node) {
			if (node == null) {
				return new AvlNode(element);
			}

			if (element.getName().compareTo(node.element.getName()) < 0) {
				node.left = insert(element, node.left);
			} else if (element.getName().compareTo(node.element.getName()) > 0) {
				node.right = insert(element, node.right);
			} else {
				// Duplicate elements are not allowed in AVL trees
				return node;
			}

			node.height = Math.max(height(node.left), height(node.right)) + 1;

			return balance(node);
		}

		public void remove(Martyers element) {
			root = remove(element, root);
		}

		private AvlNode remove(Martyers element, AvlNode node) {
			if (node == null) {
				return null;
			}

			if (element.getName().compareTo(node.element.getName()) < 0) {
				node.left = remove(element, node.left);
			} else if (element.getName().compareTo(node.element.getName()) > 0) {
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
					AvlNode successor = findMinimum(node.right);
					node.element = successor.element;
					node.right = remove(successor.element, node.right);
				}
			}

			node.height = Math.max(height(node.left), height(node.right)) + 1;

			return balance(node);
		}

		private AvlNode findMinimum(AvlNode node) {
			if (node.left == null) {
				return node;
			}
			return findMinimum(node.left);
		}

		private AvlNode balance(AvlNode node) {
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
		public void printLevelOrder(TextArea txt) {
			
			printLevelOrder(root,txt);
			
		}

		 public void printLevelOrder(AvlNode root,TextArea txt) {
		        if (root == null) {
		            System.out.println("Tree is empty.");
		            return;
		        }

		     
		        queue.enQueue(root);

		        while (!queue.isEmpty()) {
		            int levelSize = queue.size;

		            for (int i = 0; i < levelSize; i++) {
		               AvlNode current = (AvlNode) queue.deQueue();
		               txt.appendText(current.element + " ");

		                if (current.left != null) {
		                    queue.enQueue(current.left);
		                }
		                if (current.right != null) {
		                	 queue.enQueue(current.right);
		                }
		            }

		            txt.appendText("\n");

		        }
		        }
		 public List<Martyers> getNodesInLevelOrder() {
		        List<Martyers> nodes = new ArrayList<>();
		        
		        if (root == null) {
		            return nodes;
		        }

		       
		        queue.enQueue(root);

		        while (!queue.isEmpty()) {
		            int levelSize = queue.size;

		            for (int i = 0; i < levelSize; i++) {
		            	 AvlNode current = (AvlNode) queue.deQueue();
		                nodes.add(current.element);

		                if (current.left != null) {
		                	 queue.enQueue(current.left);
		                }
		                if (current.right != null) {
		                	 queue.enQueue(current.right);
		                }
		            }
		        }

		        return nodes;
		    }
		 public ObservableList<Martyers> searchPart(String name) {
			    ObservableList<Martyers> resultList = FXCollections.observableArrayList();
			    searchPart(root, name, resultList);
			    return resultList;
			}

			private void searchPart(AvlNode node, String name, ObservableList<Martyers> resultList) {
			    if (node != null) {
			        if (node.element.getName().contains(name)) {
			            resultList.add(node.element); // Add the matching node to the list
			        }
			        searchPart(node.left, name, resultList); // Search in the left subtree
			        searchPart(node.right, name, resultList); // Search in the right subtree
			    }
			}
		
		
		 public ObservableList<Martyers> getTreeAsObservableList() {
		        ObservableList<Martyers> observableList = FXCollections.observableArrayList();
		        saveTreeObjects(root, observableList);
		        return observableList;
		    }

		    private void saveTreeObjects(AvlNode node, ObservableList<Martyers> observableList) {
		        if (node != null) {
		           
		            observableList.add(node.element);

		           
		            saveTreeObjects(node.left, observableList);

		           
		            saveTreeObjects(node.right, observableList);
		        }
		    }
		 
		private void printTree(AvlNode t) // inorder traversal
		{
			if (t != null) {
				
				printTree(t.left);
				
				printTree(t.right);
				System.out.println("   " + t.element );
			}
		}

		private int getBalanceFactor(AvlNode node) {
			if (node == null) {
				return 0;
			}
			return height(node.left) - height(node.right);
		}

		// ... additional methods ...
	}


