package trees;

import java.util.ArrayList;

import trees.BTreeQuiz.Node;

public class BTree<E>{
	
	public static class Node<F>{
		// Data fields
		protected F data;
		protected Node<F> left;
		protected Node<F> right;
		
		// Constructors
		public Node(F data, Node<F> left, Node<F> right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		public Node(F data) {
			super();
			this.data = data;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
	}
	
	// Data fields
	protected Node<E> root;
	protected int size;
	
	// Constructors
	BTree() {
		root=null;
		size=0;
	}
	
	BTree(E item) {
		root = new Node<E>(item);
		size = 1;
	}
	
	BTree(E item, BTree<E> left, BTree<E> right) {
		root = new Node<E>(item,left.root,right.root);
		size = 1 + left.size+ right.size;
	}
	
	private StringBuilder toString_helper(Node<E> current, int level) {
		StringBuilder r = new StringBuilder();
		
		for (int i=0; i<level; i++) {
			r.append("--");
		}
		if (current==null) {
			r.append("null\n");
		} else {
			r.append(current.data.toString()+ "\n");
			r.append(toString_helper(current.left,level+1));
			r.append(toString_helper(current.right,level+1));
		}
		return r;
	}
	
	public String toString() {
		return toString_helper(root,0).toString();
	}
	
	private int height(Node<E> current) {
		if(current == null) {
			return 0;
		}
		if(current.left == null && current.right == null) {
			return 1;
		}
		return Math.max(height(current.left), height(current.right)) + 1;
		
		//or
		//return current==null ? 0 : 1+Math.max(height(current.left), height(current.right));
	}
	
	public int height() {
		return height(root);
	}
	
	
	
	//nodes is numNodes, but done with a helper function
	private int nodes(Node<E> current) {
		return current==null ? 0 : 1+ nodes(current.left) + nodes(current.right);
	}
	
	public int nodes() {
		return nodes(root);
	}
	
	private int numLeaves(Node<E> current) {
		if (current==null) {
			return 0;
		}
		if (current.isLeaf()==true) {
			return 1;
		}
		return numLeaves(current.left) + numLeaves(current.right);
	}
	
	public int numLeaves() {
		return numLeaves(root);
	}
	
	//every node has 0 or 2 children
	private boolean isFull(Node<E> current) {
		if(current == null) {
			return false;
		}
		//if(current.left == null && current.right == null)
		if(current.isLeaf()) {
			return true;
		}
		return isFull(current.left) && isFull(current.right);
	}
	
	public boolean isFull() {
		return isFull(root);
	}
	
	public boolean isFull2(Node<E> current) {
		if (current==null || current.left==null && current.right==null) {
			//second side of or statement is leaf case
			return true;
		}
		return current.left!=null && current.right!=null && isFull2(current.left) && isFull2(current.right);
	}
	
	public boolean isFull3(Node<E> current ) {
		return current==null || 
			   current.left==null && current.right==null || 
			   current.left!=null && current.right!=null && isFull3(current.left) && isFull3(current.right);
	}
	//every layer is completely filled
	//tree must have 2^n - 1 nodes, n is height
	
	public int numNodes(Node<E> current) {
		if(current == null) {
			return 0;
		} else {
			return 1 + numNodes(current.left) + numNodes(current.right);
		}
	}
	
	public boolean isPerfect() {
		int nodeCount = numNodes(root);
		int expected = (int) (Math.pow(2, this.height()) - 1);
		
		return nodeCount == expected;
	}
	
	public boolean isBalanced(Node<E> current) {
		if (current==null) {
			return true;
		}
		int leftHeight = height(current.left);
		int rightHeight = height(current.right);
		
		if ((leftHeight-rightHeight) <=1 && 
			(leftHeight-rightHeight) >= -1 &&
			isBalanced(current.left) &&
			isBalanced(current.right)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	private BTree<E> mirror(Node<E> current) {
		if (current==null) {
			return new BTree<E>();
		}
		if (current.isLeaf()) {
			return new BTree<E>(current.data);
		}
		BTree<E> leftMirror = mirror(current.right);
		BTree<E> rightMirror = mirror(current.left);
		return new BTree<E>(current.data,leftMirror,rightMirror);
	}
	
	public BTree<E> mirror() {
		return mirror(root);
	}
	
	public boolean prune(int level, Node<E> current, int i) {
		if (level==i) {
			current = null;
			size--;
			return true;
		} else {
			boolean rightTree = prune(level, current.right, i++);
			boolean leftTree = prune(level, current.right, i++);
			return rightTree && leftTree;
		}
	}
	
	/**
	 * prune(0) yields an empty tree, prune(h) where h=height does not change tree
	 * @param level
	 */
	public void prune(int level) {
		if (level == 0) {
			root = null;
			size=0;
		}
		if (level == height()) {
			return;
		}
		boolean temp = prune(level, root, 1);
	}
	
	private BTree<E> cloneAt(Node<E> current) {
		if (current==null) {
			return new BTree<E>();
		}
		if (current.isLeaf()) {
			return new BTree<E>(current.data);
		}
		return new BTree<E>(current.data,cloneAt(current.left),cloneAt(current.right));
	}
	
	public BTree<E> cloneAt() {
		return cloneAt(root);
	}
	
	private ArrayList<ArrayList<Integer>> paths(Node<E> lr) {
		if (lr==null ) {
			return new ArrayList<ArrayList<Integer>>(); //[]
		} else if (lr.isLeaf()) {
			ArrayList <ArrayList<Integer>> result = new ArrayList <ArrayList<Integer>>();
			result.add (new ArrayList <Integer>());
			return result ; // [[]]
		} else {
			ArrayList < ArrayList < Integer > > resultRight = paths ( lr . right );
			for ( ArrayList < Integer > l : resultRight ) {
				l.add(0,1);
			}
			ArrayList<ArrayList<Integer>>resultLeft = paths(lr.left);
			for (ArrayList<Integer>l : resultLeft) {
				l.add (0 ,0);
			}
			resultLeft . addAll ( resultRight );
			return resultLeft ;
		}
	}
	
	public ArrayList<ArrayList<Integer>>paths() {
		return paths(root);
	}
	
	private boolean isomorphic(Node<E> current1, trees.BTreeQuiz.Node<E> root2) { 
		if (current1==null && root2==null) { 
			// both nodes are empty 
			return true; 
		} 
		// At least one of the nodes is non-empty   
		return current1!=null && root2!=null && current1.data.equals(root2.data) &&  
				((isomorphic(current1.left,root2.left) && isomorphic(current1.right,root2.right)) 
						||(isomorphic(current1.left,root2.right) && isomorphic(current1.right,root2.left)));  
	} 
	
	public boolean isomorphic(BTreeQuiz<E> t2) { return isomorphic(root,t2.root); }

	
	/**
	 * returns list with all subtrees located at a given depth
	 * @param i current depth
	 * @param current current node in tree
	 * @return subtree at depth
	 */
	private ArrayList <BTree<E>> projectLevel (int i,Node<E> current) {
		ArrayList<BTree<E>> r = new ArrayList<BTree<E>>();
		if (i==0 && current!=null) { // found the designated level
			r.add(cloneAt(current));
		} else {
			if (current!=null) {
				r.addAll(projectLevel (i-1,current.left));
				r.addAll(projectLevel (i-1,current.right));
			}
		}
			return r ;
		}

	public ArrayList<BTree<E>> projectLevel (int i) {
		return projectLevel(i,root);
	}
	
	
	/**
	 * returns all subtrees in a binary tree
	 */
	public ArrayList<BTree<E>> st () {
		ArrayList <BTree<E>> r = new ArrayList<BTree<E>>();
		for (int i=0; i < height(); i ++) {
			r.addAll(projectLevel(i));
		}
		return r;
	}
	
	//every layer is filled except the leaf layer
	//public boolean isComplete()
	//	{}
	//binary search tree
	public Boolean isBST(Node<Integer> current) {
        if(current == null) {
        	return true;
        }
        if(current.isLeaf()) {
            return true;
        }
        if(current.left == null) {
            return (current.data < minTree(current.right)) && (isBST(current.right));
        }
        if(current.right == null) {
            return (current.data > maxTree(current.left)) && (isBST(current.left));
        }

        return (current.data > maxTree(current.left)) && 
        		(current.data < minTree(current.right)) && 
        		(isBST(current.left)) && (isBST(current.right));
    }
    
	private int maxTree(Node<Integer> current) {
        while(current.right != null) {
            current = current.right;
        }
        return current.data;
    }
    
    private int minTree(Node<Integer> current) {
        while(current.left != null) {
            current = current.left;
        }
        return current.data;
    }
	
	
}