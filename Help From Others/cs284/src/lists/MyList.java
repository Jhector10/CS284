package lists;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class that implements a list using arrays
 * @author ebonelli
 *
 * @param <E> Type of the contents of the list
 */
public class MyList<E> {
	// data fields
	private E[] data;
	private int free; // index to next available slot
	
	// Constructor
	MyList(int size) {
		super();
		this.data = (E[]) new Object[size];
		this.free = 0;
	}
	
	// Methods

	public E get(int index) {
		if (index<0 || index>= free) {
			throw new IllegalArgumentException("MyList.get: index out of bounds");
		}
		// index is in range!
		return data[index];
	}
	
	public E add(E item) {
		data[free] = item;
		free++;
		return item;
	}
	
	public String toString() {
		return Arrays.toString(data);
	}
	
	public static boolean same_object(Object o1, Object o2 ) {
		return o1.toString()==o2.toString();
	}
	
//	There are two kinds of exceptions in Java: 
//	* Checked (eg. FileNotFoundException) and
//	* Unchecked (eg. NullPointerException).
//	The latter are the most common ones. For the latter, Java does not enforce you to
//	handle them or signal that they are thrown.
//
//	You can define your own exceptions. But in this course we will only be
//	using built-in exceptions.
//
//	You create an exception  using "throw". You handle an exception using
//	try {...}  catch (E e) { ...}
//	
	
	// This code exemplifies the use of checked exceptions.
	// Place a text file with numbers, one per line, called myFile.txt
	// Place it alongside bin and src.
	//
	// Next try removing the "throws IOException" and see what happens
	public static void readFromFile() throws IOException {
		FileInputStream fis = null;
		
		fis = new FileInputStream("myFile.txt");
		int k;
		k = fis.read();
		
		while (k!= -1) {
			System.out.print((char) k);
			k=fis.read();
		}
		fis.close();
	}
	
	public static void main(String[] args) throws IOException {
		MyList<String> l1 = new MyList<String>(10);
		
		l1.add("hello");
		l1.add("hi");
		l1.add("howdy");
				
//		System.out.println(l1.get(2));
		
//		int[] a = new int[7];
//		int[] b = null;
//		
//		
//		same_object(a,b);
		
		MyList<Integer> l2 = null ;
		
//		System.out.println(same_object(l1,l2));

		System.out.println(l1);
		System.out.println(l2);
		
		readFromFile();
		
//		l2.add(1);
//		l2.add(3);
//		l2.add(7);
//				
//		System.out.println(l2.get(2));
//		
	
		
	}
	
	
}
