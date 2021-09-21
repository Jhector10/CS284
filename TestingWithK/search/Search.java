package search;

public class Search {
	private int[] x;
	
	public Search() {
		x = new int[] {5,12,15,4,8,12,7};
}

	public Search(int[] y) {
		x = y;
		}
	
	public int search(int target) {
		for (int i = 0; i < x.length ;) {
			if(x[i]==target)
				return i;
		return -1;
		}
		return target;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
