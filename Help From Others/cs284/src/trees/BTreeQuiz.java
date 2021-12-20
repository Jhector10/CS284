package trees;

public class BTreeQuiz<E>{

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
		};
		
	
	}
	// Data fields
	protected Node<E> root;
	private int size;
	
	// Constructors
	BTreeQuiz() {
		root=null;
		size=0;
	}
	
	BTreeQuiz(E item) {
		root = new Node<E>(item);
		size = 1;
	}
	
	BTreeQuiz(E item, BTreeQuiz<E> left, BTreeQuiz<E> right) {
		root = new Node<E>(item,left.root,right.root);
		size = 1 + left.size+ right.size;
	}
	
	private StringBuilder toString(Node<E> current, int level) {
		 StringBuilder r = new StringBuilder();
		 
		 for (int i=0; i<level; i++) {
			 r.append("--");
		 }
		 if (current==null) {
			 r.append("null\n");
			 return r;
		 }
		 r.append(current.data.toString()+"\n");
		 r.append(toString(current.left,level+1));
		 r.append(toString(current.right,level+1));
		 return r;
		
	}
	public String toString() {
		return toString(root,0).toString();
		
	}
	
	private int height(Node<E> current) {
		return current==null ? 0 : 1+Math.max(height(current.left), height(current.right));
	}
	
	public int height() {
		return height(root);
	}
	
	private int nodes(Node<E> current) {
		return current==null ? 0 : 1 + nodes(current.left) + nodes(current.right);
	}
	
	public int nodes() {
		return nodes(root);
	}
	
	private boolean isFull(Node<E> current) {
		if (current==null ||  (current.left==null && current.right==null)) {
			return true;
		}
		return current.left!=null && current.right!=null && isFull(current.left) && isFull(current.right); 
	}
	
	private boolean isFull2(Node<E> current) {
		return (current==null ||  (current.left==null && current.right==null)) || (current.left!=null && current.right!=null && isFull2(current.left) && isFull2(current.right)); 
	}
	
	public boolean isFull() {
		return isFull2(root);
	}
	
	public void prune(int level, Node<E> current, int i) {
		if(current == null) {
			return;
		}
		if (level==i) {
			current = null;
			size--;
			return;
		} else {
			prune(level, current.left, i++); //prunes left
			prune(level, current.right, i++); //prunes right
			return;
		}
	}
	
	/**
	 * prune(0) yields an empty tree, prune(h) where h=height does not change tree
	 * @param level
	 */
	public void prune(int level) {
		if (level<0) {
			throw new IllegalStateException("prune: cannot have a negative level");
		} else if (level == 0) {
			root = null;
			size=0;
		} else if (level == height()) {
			return;
		} else {
			prune(level, root, 1);
		}
	}
	
	private boolean isomorphic (Node<E> t1Current, Node<E> t2Current) {
		if (t1Current==null && t2Current==null) {
			return true;
		}
		if (t1Current.data==t2Current.data) {//current nodes on both trees are the same
			boolean leftMirror = isomorphic(t1Current.left,t2Current.right); //checks to see if next level is mirrored
			boolean rightMirror = isomorphic(t1Current.right,t2Current.left); //checks to see if next level is mirrored
			boolean leftSame = isomorphic(t1Current.left,t2Current.left); //checks to see if next level is the same
			boolean rightSame = isomorphic(t1Current.right,t2Current.right); //checks to see if next level is the same
			
			if (leftMirror==true && rightMirror==true && leftSame==false && rightSame==false) {
				return true;
			} 
			if (leftMirror==false && rightMirror==false && leftSame==true && rightSame==true) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * if two trees are isomorphic that means one tree is a mirrored version of the other tree
	 * @param t2 tree that current tree is being compared too
	 * @return returns true if trees are isomorphic and false if not
	 */
	public boolean isomorphic (BTreeQuiz<E> t2) {
		if (root==null && t2.root == null) { //both trees are empty
			return true; 
		} else if (root==null || t2.root==null) { //one tree is empty
			return false;
		} else {
			//if the left branch of t1 is the same as the right branch of t2, at every node, then return true
			return isomorphic(root, t2.root);
		}
	}
	
	
	
	
	
//	
//	
//	public static void main(String[] args) {
//		BTree<Integer> t1 = new BTree<>(4);
//		
//		BTree<Integer> t2 = new BTree<>(33,new BTree<>(24), new BTree<>(44));
//				
//		BTree<Integer> t = new BTree<>(12,t1,t2);
//		
//		System.out.println(t);
//		System.out.println("Height: "+t.height());
//		System.out.println("Nodes: "+t.nodes());
//		System.out.println("IsFull: "+t.isFull());
//		
//	}
//	
//	
	
	
}
