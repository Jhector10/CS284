package trees;

public class BSTree<Key extends Comparable<Key>, Value> {
	
	public class Node {
		
		private Key key;
		private Value value;
		private Node right;
		private Node left;
		private int size;
		
		public Node() {
			key = null;
			value = null;
			right = null;
			left = null;
			size = 1;
		}
		
		public Node(Key key, Value value, int size) {
			this.key = key;
			this.value = value;
			this.size = size;
			this.left = null;
			this.right = null;
		}
	}
	
	private Node root;
	
	public void inOrder() {
		inOrder(root);
	}
	
	private void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			System.out.println(node.key);
			inOrder(node.right);
		}
	}
	
	private Node insert(Node node, Key key, Value value) {
		if (node == null) {
			node = new Node(key, value, 1);
		} 
		
		int compare = key.compareTo(node.key);
		// key < node.key -> -1, key > node.key -> 1, key = x.key -> 0
		
		if (compare < 0) {
			node.left = insert(node.left, key, value);
		} else if (compare > 0) {
			node.right = insert(node.right, key, value);
		}
		
		node.size = 1 + size(node.left)+ size(node.right); 
		return node;
		
	}
	
	public void insert(Key key, Value value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Key/Value is null");
		}
		
		insert(root, key, value);
	}
	
	public Node search(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("Called with null key to search");
		} else {
			return search(root, key);
		}
	}
	
	private Node search(Node node, Key key) {
		if (node == null || node.key == key) {
			return node;
		}
		
		int compare = key.compareTo(node.key);
		if (compare < 0) {
			return search(node.left, key);
		} else if (compare > 0) {
			return search(node.right, key);
		} else {
			return node;
		}
	}
	
	public Node min(Node node) {
		if (node.left == null) {
			return node;
		} else {
			return min(node.left); // while it is not null, keep going left
		}
	}
	
	public Node min2(Node node) {
		Node temp = node;
		
		while (temp.left != null && temp != null) {
			temp = temp.left;
		}
		
		return temp;
	}
	
	public Node max(Node node) {
		if (node.right == null) {
			return node;
		} else {
			return max(node.right);
		}
	}
	
	public Node successor(Node root, Key key) {
		Node node = search(key);
		if (node.right != null) {
			return min(node.right);
		} 
		
		Node successor = null;
		while (root != null) {
			int compare = key.compareTo(root.key);
			if (compare  < 0) {
				successor = root;
				root = root.left;
			} else if (compare > 0) {
				root = root.right;
			} else {
				break;
			}
		}
		return successor;
	}
	
	public Node predecessor(Node root, Key key) {
		Node node = search(key);
		
		if (node.left != null) {
			return max(node.left);
		}
		
		Node predecessor = null;
		
		while (root != null) {
			int compare = key.compareTo(root.key);
			if (compare < 0) {
				root = root.left;
			} else if (compare > 0) {
				predecessor = root;
				root = root.right;
			} else {
				break;
			}
		}
		
		return predecessor;
	}
	
	public int size() {
		return size(root);
	}
	
	public int size (Node node) {
		if (node == null) {
			return 0;
		}
		else {
			return node.size;
		}
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		if (node.left == null) {
			return node.right;
		} 
		
		node.left = deleteMin(node.left);
		node.size = size(node.left) + size(node.right) + 1; 
		return node;
	}
	
	public void delete(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("Called delete() with null key");
		}
		root = delete(root, key);
	}
	
	private Node delete(Node node, Key key) {
		if (node == null) {
			return null;
		}
		
		int compare = key.compareTo(node.key);
		if (compare < 0) {
			node.left = delete(node.left, key);
		} 
		else if (compare > 0) {
			node.right = delete(node.right, key);
		} 
		else {
			if (node.right == null) {
				return node.left;
			}
			else if (node.left == null) {
				return node.right;
			}
			
			Node temp = node;
			node = min(temp.right);
			node.right = deleteMin(temp.right);
			node.left = temp.left;
		}
		
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}

	public static void main(String[] args) {
		BSTree<Integer, Integer> btree = new BSTree<Integer, Integer>();
		int value = 1;
		btree.insert(22, value);
		btree.insert(10,  value);
		btree.insert(30, value);
		btree.insert(3,  value);
		btree.insert(12,  value);
		btree.insert(5,  value);
		btree.insert(28,  value);
		btree.insert(34,  value);
		btree.insert(25,  value);
		btree.insert(29,  value);

		System.out.println("In Order traversal called:");
		btree.inOrder();
		
		System.out.println("Delete 5 in the tree");
		btree.delete(5);
		
		btree.inOrder();
	}

}
