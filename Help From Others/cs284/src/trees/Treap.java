package trees;
/*
 * Creator: Simrun Heir
 * CS 284 hw 5
 * "I pledge my Honor that I have abided by the Stevens Honor System."
 */

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	
	public static class Node<E>{
		// Data fields
		public E data; //key for the search
		public int priority; //random heap priority
		public Node<E> left;
		public Node<E> right;
		
		// Constructors
		public Node(E data, int priority) {
			if (data==null) {
				throw new IllegalStateException("Node: data is null");
			}
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}
		
		//methods
		
		/**
		 * rotates current node right
		 * left child becomes parent of previous parent node
		 * previous parent node becomes right child of previous left child
		 * previous left child's right child becomes left child of previous parent
		 * @return returns new parent node obtained by rotating right
		 */
		public Node<E> rotateRight() {
			Node<E> temp = this.left;
			this.left = temp.right;
			temp.right = this;
			return temp;
		}
		/**
		 * rotates current node left
		 * right child becomes parent of previous parent node
		 * previous parent node becomes left child of previous right child
		 * previous right child's left child becomes right child of previous parent
		 * @return returns new parent node obtained by rotating left
		 */
		public Node<E> rotateLeft() {
			Node<E> temp = this.right;
			this.right = temp.left;
			temp.left = this;
			return temp;
		}
	}
	
	//data fields
	private Random priorityGenerator;
	private Node<E> root;
	
	//constructors
	public Treap() {
		this.root=null;
		this.priorityGenerator = new Random();
	}
	
	public Treap(long seed) {
		this.root=null;
		this.priorityGenerator = new Random(seed);
	}
	
	//methods
	
	/**
	 * adds a node with key key and a random priority to the treap
	 * @param key key of node
	 * @return true if added successfully, false if not added
	 */
	public boolean add (E key) {
		return add(key,priorityGenerator.nextInt());
	}
	
	/**
	 * adds a node with key key and priority priority to treap
	 * @param key key of node
	 * @param priority integer representing priority
	 * @return
	 */
	public boolean add (E key, int priority) {
		if (root==null) {//tree is empty
			root = new Node<E>(key,priority);
			return true;
		} else {//tree is not empty
			if (find(key)==true) {
				return false;
			}
			Node<E> addedNode = new Node<E>(key,priority); //node that is being added
			Node<E> current = root; 
			Node<E> trace = null; //keeps track of path through Treap, is parent node of current if a new node was added
			Stack<Node<E>> path = new Stack<Node<E>>(); //stores path through Treap
			
			while(current!=null) {
				trace = current;
				path.push(current);
				int i = key.compareTo(current.data);
				if (i<0) {
					current = current.left;
					path.push(trace);
				} 
				if (i>0){ 
					current = current.right;
					path.push(trace);
				}
			}
			if (trace==null) { //adding a node to an empty treap
				trace = addedNode;
				return true;
			} else {
				int i = key.compareTo(trace.data);
				if (i<0) {
					trace.left = addedNode;
				}
				if (i>0) {
					trace.right = addedNode;
				}
			}
			reheap(addedNode,path);
			return true;
		}
	}

	/**
	 * helper function for add
	 * reorders treap so after adding a node proper priority ordering is kept
	 * @param parent parent node, parent and child's priorities are to be compared
	 * @param path path of nodes from root to where added node is inserted
	 */
	public void reheap(Node<E> parent, Stack<Node<E>> path) {
		while (path.empty()!=true) {
			parent = path.pop();
			path.pop();
			Node<E> grandparent;
			if (path.empty()!=true) {
				grandparent = path.peek();
			} else {
				grandparent = null;
			}
			
			if (parent.left!=null && parent.priority < parent.left.priority) {
				if (grandparent==null) {
					Node<E> temp = parent.rotateRight();
					grandparent = temp;
					root = grandparent;
				} else if (grandparent.left!=null && grandparent.left.equals(parent)) {
					Node<E> temp = parent.rotateRight();
					grandparent.left = temp;
				} else {
					Node<E> temp = parent.rotateRight();
					grandparent.right = temp;
				}
				
			} else if (parent.right!=null && parent.priority < parent.right.priority) {
				if (grandparent==null) {
					Node<E> temp = parent.rotateLeft();
					grandparent = temp;
					root = grandparent;
				} else if (grandparent.left!=null && grandparent.left.equals(parent)) {
					Node<E> temp = parent.rotateLeft();
					grandparent.left = temp;
				} else {
					Node<E> temp = parent.rotateLeft();
					grandparent.right = temp;
				}
			}
		}
	}
	
	/**
	 * deletes a node in the treap that has key key and maintains treap structure
	 * @param key key of node to be deleted
	 * @return return true if node was deleted, false if not
	 */
	public boolean delete (E key) {
		if (root==null) {
			//throw new IllegalStateException("delete: attempted to delete from an empty treap");
			return false;
		} else if (key==null) {
			//throw new IllegalStateException("delete: no key entered");
			return false;
		} else if (find(key)==false) {
			return false;
		} else {
			Node<E> parent = findParent(root,key); //parent of node to be deleted
			Node<E> nodeDelete; //node to be deleted
			if (parent.equals(root)==true) {
				nodeDelete = root;
			} else if (parent.left.data.compareTo(key)==0) { //if node to be removed is left child
				nodeDelete = parent.left;
			} else { //node to be removed is right child
				nodeDelete = parent.right;
			}
			if (isLeaf(nodeDelete)==true) {
				nodeDelete=null;
				return true;
			} else {
				trickleDown(parent,nodeDelete,key);
				return true;
			}
		}
	}
	
	/**
	 * helper function for delete
	 * goes through and finds position of key in treap
	 * once found, trickles down node with key until it is leaf, then removes it
	 * @return returns true if node is deleted, returns false if not
	 */
	private void trickleDown (Node<E> parent,Node<E> nodeDelete,E key) {
		while (nodeDelete.left!=null || nodeDelete.right!=null) {
			if (nodeDelete.left==null) { //only left child
				if (parent.left.equals(nodeDelete)==true) {
					parent.left = nodeDelete.rotateLeft();
				} else {
					parent.right = nodeDelete.rotateLeft();
				}
				parent = parent.left;
				nodeDelete = parent.left;
			} else if (nodeDelete.right==null) { //only right child
				if (parent.left.equals(nodeDelete)==true) {
					parent.left = nodeDelete.rotateRight();
				} else {
					parent.right = nodeDelete.rotateRight();
				}
				parent = parent.right;
				nodeDelete = parent.right;
			} else { //two children
				if (nodeDelete.left.priority > nodeDelete.right.priority) {
					if (parent.left.equals(nodeDelete)==true) {
						parent.left = nodeDelete.rotateRight();
					} else {
						parent.right = nodeDelete.rotateRight();
					}
					parent = parent.right;
					nodeDelete = parent.right;
				} else {
					if (parent.left.equals(nodeDelete)==true) {
						parent.left = nodeDelete.rotateLeft();
					} else {
						parent.right = nodeDelete.rotateLeft();
					}
					parent = parent.left;
					nodeDelete = parent.left;
				}
			}
		}
		if (parent.left!=null && parent.right==null) {
			parent.left = null;
		} else {
			parent.right = null;
		}
	}
		
	/**
	 * goes through treap and finds parent node of node containing key
	 * @param current current node
	 * @param key key being searched for
	 * @return
	 */
	private Node<E> findParent (Node<E> current,E key) {
		if (current==null) {
			return null;
		} else if (current.data.compareTo(key)==0) { //if key is apart of root node
			return current; 
		} else if (current.left!=null && current.left.data.compareTo(key)==0) { //if key is left child
			return current;
		} else if (current.left!=null && current.right.data.compareTo(key)==0) { //if key is right child
			return current;
		} else {
			Node<E> left = findParent(current.left,key); //checks left branch
			Node<E> right = findParent(current.right,key); //checks right branch
			 
			if (left==null) { //if parent not in left branch
				return right;
			} else { //if parent not in right branch
				return left; 
			}
		}
	}
	
	/**
	 * checks if current node is a leaf by looking at its child nodes
	 * @param current node being checked
	 * @return return true if leaf, false if not leaf
	 */
	public boolean isLeaf(Node<E> current) {
		return current.left==null && current.right==null;
	}
	
	/**
	 * determines whether key is in treap by going through nodes recursively
	 * @param current current node being checked
	 * @param key key being looked for
	 * @return return true if key is within treap, return false if key is not in treap
	 */
	private boolean find(Node<E> current, E key) {
		if (current==null) {
			return false;
		}
		if (current.data==key) {
			return true;
		} else {
			boolean left = find(current.left, key);
			boolean right = find(current.right, key);
			return left||right;
		}
	}
	
	/**
	 * checks to see if key is present within a node in the treap
	 * if the key is null, throws an exception
	 * if the treap is empty, throws an exception
	 * @param key key being searched for
	 * @return return true if key is in tree, otherwise return false
	 */
	public boolean find(E key) {
		if (root==null) {
			//throw new IllegalStateException("find: attempted to search through empty treap");
			return false;
		} else if (key==null) {
			//throw new IllegalStateException("find: no key entered");
			return false;
		} else {
			return find(root, key);
		}
	}
	
	
	private StringBuilder toString_helper(Node<E> current, int level) {
		StringBuilder r = new StringBuilder();
		
		for (int i=0; i<level; i++) {
			r.append("  ");
		}
		if (current==null) {
			r.append("null\n");
		} else {
			r.append("(key="+current.data.toString()+", priority="+current.priority+')'+ "\n");
			r.append(toString_helper(current.left,level+1));
			r.append(toString_helper(current.right,level+1));
		}
		return r;
	}
	
	public String toString() {
		return toString_helper(root,0).toString();
	}
	
	public static void main(String[] args) {
//		Treap<Integer> testTree = new Treap<Integer>();
//		
//		System.out.println("Before adding nodes:\n"+testTree);
//		testTree.add(4,19);
//		testTree.add(2,31);
//		testTree.add(6,70);
//		testTree.add(1,84);
//		testTree.add(3,12);
//		testTree.add(5,83);
//		testTree.add(7,26);
//		
//		System.out.println("Final Tree:\n"+testTree);
	}
}
