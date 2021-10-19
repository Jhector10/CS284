package JavaExamples;

import java.util.Arrays;

public class SortList {
	
	public static int[] Sort(int[] list1, int[] list2) {
		// when given two strings of different lengths, return the list combined and sorted
		// assume that the lists are already sorted
		
		int total = list1.length + list2.length;
		int x = 0;
		int y = 0;
		int i = 0;
		int[] list3 = new int[total];
		
		while (x < list1.length && y < list2.length) {
			if (list1[x] < list2[y]) {
				list3[i] = list1[x];
				i++;
				x++;
			} else {
				list3[i] = list2[y];
				i++;
				y++;
			}
		}
		
		while (x < list1.length) {
			list3[i] = list1[x];
			i++;
			x++;
		}
		
		while (y < list2.length) {
			list3[i] = list2[y];
			i++;
			y++;
		}
		
		return list3;
	}

	public static void main(String[] args) {
		
		int[] s1;
		int[] ex1 = {1, 2, 3};
		int[] ex2 = {2, 5, 6};
		
		s1 = SortList.Sort(ex1, ex2);
	
		System.out.println(Arrays.toString(s1));

	}

}
