package sorting;

public class BubbleSort {
	
	private int radius;
	
	public static <E extends Comparable<E>> E[] sort (E[] table) {
		int pass = 1;
		boolean exchanges;
		do {
			exchanges = false;
			for(int i=0; i < table.length - pass; i++) {
				if(table[i].compareTo(table[i+1])>0) {
					//Swapping-Exchanging the elements.
					E temp = table[i];
					table[i] = table[i + 1];
					table[i + 1] = temp; 
					exchanges = true;
				}	
			}
			pass++;
		}while(exchanges);
		return table;
	}
	
	public static <E> void main(String[] args) {
		Integer[] array = {5, 7, 2, 4, 3};
		array = BubbleSort.sort(array);
		for(Integer i:array) {
			System.out.println(i);
		}
	}
	
}
