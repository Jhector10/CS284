/* Name: Joshua Hector
 * Pledge: I pledge my honor that I have abided by the Stevens Honor SyStem
 * */
package hw4;

import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;

public class Treap<E extends Comparable<E>> {
	
	private static class Node<E> {
		
		//data fields
		
		private E data; // key for the search
		public int priority; // random heap priority
		private Node<E> left;
		private Node<E> right;
		
		//Constructor
		/**
         * Creates a new Node with data and priority set to parameters along with
         * left and right children initially set to null. Checks to see if data is null
         * @param data can't be null
         * @param priority random priority assigned to Node by call
         */
		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException("Node data cannot be null");
			}
			
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}
		
		/**
		 * Function that rotates the tree right
		 * @return returns the rotated tree
		 */
		public Node<E> rotateRight() {
			// like a swap method
			if (this.left == null) {
				return this;
			}
			
			Node<E> node = this.left;
			this.left = node.right;
			node.right = this;
			return node;
		}
		
		/**
		 * Function that rotates the tree left
		 * @return returns the rotated node
		 */
		public Node<E> rotateLeft() {
			if (this.right == null) {
				return this;
			}
			
			Node<E> node = this.right;
			this.right = node.left;
			node.left = this;
			return node;
		}
		
		@Override
        public String toString() {
            return "(key=" +data+", priority=" +priority+ ")";
        }
	}
	
	// data fields for Treap
	
	private Node<E> root;
	private Random priorityGenerator;
	private ArrayList<Integer> priorityList;
	
	public Treap() {
		root = null;
		priorityGenerator = new Random();
		priorityList = new ArrayList<Integer>();
	}
	
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
		priorityList = new ArrayList<Integer>();
	}
	
	
	// Operations for Treap
	
	/**
	 * add function that has a random priority, but throws
	 * error if key is null
	 * 
	 * @param key item to be inserted into treap
	 * @return true if done, false if key is there already
	 */
	public boolean add(E key) {
		if (key == null) {
			throw new IllegalArgumentException("Key is null");
		}
		
		int randomInt = priorityGenerator.nextInt(1000);
		
		while (priorityList.contains(randomInt)) {
			randomInt = priorityGenerator.nextInt(1000);
		}
		
		return add(key, randomInt);
	}
	
	/**
	 * Add method that uses reheap for a helper function, adds Node to treap
	 * 
	 * @param key item to be inserted into treap
	 * @param priority given to the node
	 * @return true if done, false if key is already there
	 */
	public boolean add(E key, int priority) {
		if (key == null || priorityList.contains(priority)) {
			throw new IllegalArgumentException("Key is null or Priority must be unique");
		}
		
		priorityList.add(priority);
		
		
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		}
		
		Node<E> node = new Node<E>(key, priority);
		Node<E> curr = root;
		Stack<Node<E>> stack = new Stack<Node<E>>();
		
		while(curr != null) {
			
			int compare = key.compareTo(curr.data);
			
			if (compare == 0) {
				return false;
				// means that a node is the same as the given key
				// break out loop
			}
			
			if (compare > 0) {
				stack.push(curr);
				if (curr.right == null) {
					curr.right = node;
					curr = curr.right;
					break;
				} else {
					curr = curr.right;
				}
			} else { // compare < 0
				stack.push(curr);
				if (curr.left == null) {
					curr.left = node;
					curr = curr.left;
					break;
				} else {
					curr = curr.left;
				}
			}
		}
		
		reheap(curr, stack);
		return true;
	}
	
	/**
	 * Helper method for add(node, priority). Changes tree to comply with heap
	 * characteristics
	 * 
	 * @param curr: node that is added to the tree
	 * @param stack: same stack from add to make references to the parent nodes
	 */
	private void reheap(Node<E> curr, Stack<Node<E>> stack) {
		
		if (stack.empty()) { // if the stack is empty, just place the node inside the heap
			root = curr;
			return;
		} else if (curr.priority < stack.peek().priority) {
			return;
		} 
		
		while(curr.priority > stack.peek().priority) { 
			if (stack.peek().left == curr) {
				Node<E> parent = stack.peek();
				if(stack.size() == 1) {
					root = parent.rotateRight();
					break;
				} else {
					stack.pop();
					if (stack.peek().left == parent) {
						stack.peek().left = parent.rotateRight();
					} else {
						stack.peek().right = parent.rotateRight();
					}
				}
			} 
			else {
				Node<E> parent = stack.peek();
				if(stack.size() == 1) {
					root = parent.rotateLeft();
					break;
				} else {
					stack.pop();
					if (stack.peek().left == parent) {
						stack.peek().left = parent.rotateLeft();
					} else {
						stack.peek().right = parent.rotateLeft();
					}
				}
			}
		}
	}
	
	/**
	 * Deletes a given key from the treap
	 * @param key: node that is to be deleted from the treap
	 * @param stack: stack holding all of the nodes
	 * @return true if deleted from treap, false if it can't 
	 * 		find key in treap
	 */
	public boolean delete(E key) {
		boolean deleted = false;
		
		if (!find(key)) {
			return false;
		}
		
		if (root.data.equals(key)) { // if the root is equal to the key
			if (root.left == null && root.right == null) { // if root is only one in the treap
				root = null;
				return true;
			}
			if (root.left != null && root.right != null) {
				if (root.left.priority > root.right.priority) {
					root.rotateRight();
				} else {
					root.rotateLeft();
				}
			} 
			if (root.right != null && root.left == null) {
				root = root.rotateLeft();
			}
			if (root.left != null && root.right == null) {
				root = root.rotateRight();
			}
		}
		
		
		Node<E> current = root;
		Node<E> parent = null;
		Node<E> temp = null;
		
		int compare = current.data.compareTo(key);
		
		// find the node by using the same implementation as find() method
		while (current != null && compare != 0) {
			if (compare > 0) { // go left
				parent = current;
				current = current.left;
			} else if (compare < 0) { // go right
				parent = current;
				current = current.right;
			}
		}
		
		if (current == null) {
			return false;
		}
		
		
		while (!(current.left == null && current.right == null)) {
			// gets out while loop when node has no more children
			
			if (current.right == null) {
				// if node has only a left child
				temp = current.left;
				if (temp.right != null && temp.right.equals(current)) {
					parent.right = current.rotateRight();
				} else {
					parent.left = current.rotateRight();
				}
				parent = temp;
			}
			
			if (current.left == null) {
				// if node has only right child
				temp = current.right;
				if (temp.right != null && temp.right.equals(current)) {
					parent.right = current.rotateLeft();
				} else {
					parent.left = current.rotateLeft();
				}
				parent = temp;
			}
			
			if (current.left != null && current.right != null){
				if (current.left.priority > current.right.priority) {
					temp = current.left;
					if (temp.right.equals(current)) {
						parent.right = current.rotateRight();
					} else {
						parent.left = current.rotateRight();
					}
					parent = temp;
				} 
				else {
					temp = current.right;
					if(temp.right.equals(current)) {
						parent.right = current.rotateLeft();
					} else {
						parent.left = current.rotateLeft();
					}
					parent = temp;
				}
			}
		}
		
		if (parent.left != null && parent.left.equals(current)) {
			parent.left = null;
		} else if (parent.right != null && parent.left.equals(current)){
			parent.right = null;
		}
		
		current = null;
		deleted = true;
		return deleted;
	}
	
	/**
     * Helper method for the find(E key) method
     * @param root Node being looked at
     * @param key item being looked for
     * @return True if found, false if not
     */
	private boolean find(Node<E> root, E key) { // from BSTree.java file in class
		if (root == null) {
			return false;
		}
		
		int compare = key.compareTo(root.data);
		
		
		if (compare == 0) {
			return true;
		} else if (compare < 0) {
			return find(root.left, key);
		} else {
			return find(root.right, key);
		}
		
	}
	
	/**
	 * Finds the key in the treap
	 * @param key: item being looked for
	 * @return true if found, false if not
	 */
	public boolean find(E key) {
		if (key == null) {
			throw new IllegalArgumentException("Called with null key to search");
		} else {
			return find(root, key);
		}
	}
	
	/**
     * Helper function for toString() that traverses the treap via preorder
     * @param current
     * @param level
     * @return string
     */
	public String toString(Node<E> current, int level) { // from BTree.java file in class
        StringBuilder s = new StringBuilder();
        
        for(int i = 0; i < level; i++) {
            s.append("  ");
        }
        
        if(current == null) {
            s.append("null\n");
        } else {
            s.append("(key = " + current.data + ", priority = " + current.priority + ")\n");
            s.append(toString(current.left, level + 1));
            s.append(toString(current.right, level + 1));
        }
        return s.toString();
    }
	
	@Override
	public String toString() {
		return toString(root, 0);
	}

	public static void main(String[] args) {
		
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4 ,19);
		testTree.add(2 ,31);
		testTree.add(6 ,70);
		testTree.add(1 ,84);
		testTree.add(3 ,12);
		testTree.add(5 ,83);
		testTree.add(7 ,26);
		
		testTree.delete(4);
		
		System.out.println(testTree.toString());

	}

}
