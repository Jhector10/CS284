package cs284hw1;

//Name: Simrun Heir
//Section A
//"I pledge my Honor that I have abided by the Stevens Honor System."

public class BinaryNumber{
	// Data fields
	private int length;
	private int data[];
	
	//Constructor
	/**creates binary number of length length that consists of only zeroes
	 * 
	 * @param length integer of how many zeroes make up binary number
	 */
	BinaryNumber(int length){
		this.length = length;
		data = new int[length];
		for (int i=0; i<length; i++) {
			data[i] = 0;
		}
	}
	/**creates a binary number given a string
	 * 
	 * @param str string representation of binary number, ex "1011"
	 */
	BinaryNumber(String str){
		this.length = str.length();
		data = new int[str.length()];
		for (int i=0; i<str.length();i++) {
			char c = str.charAt(i);
			int j = Character.getNumericValue(c);
			data[i] = j;
		}
	}
	
	//methods
	/**determines the length of a binary number
	 * 
	 * @return int for length of binary number
	 */
	public int getLength() {
		return length;
	}
	/**obtains a digit of a binary number given an index. starting index is 0
	 * if index is out of bounds, message is printed on screen indicating this
	 * 
	 * @param index: the index of the digit
	 * @return index of the digit if it is in range, error message if out of range
	 */
	public int getDigit(int index) {
		return data[index];
	}
	/**returns integer array representing the binary number
	 * 
	 * @return integer array of binary number
	 */
	public int[] getInnerArray() {
		return data;
	}
	/**computes the bitwise of the two BinaryNumbers by bitwise or
	 * both argument BinaryNumbers must be of same length for input to be considered valid
	 * @param bn1 first binary number
	 * @param bn2 first binary number
	 * @return bitwise or
	 */
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		if (bn1.getLength() == bn2.getLength()) {
			int[] bwor = new int[bn1.getLength()];
			int[] b1 = bn1.getInnerArray();
			int[] b2 = bn2.getInnerArray();
			for (int i=0; i<bn1.getLength(); i++) {
				if(b1[i]==1 || b2[i]==1) {
					bwor[i] = 1;
				}else {
					bwor[i] = 0;
				}
			}
			return bwor;
		}else {
			System.out.println("Invalid input, strings are not equal length. Return empty array");
			int[] inval = new int[0];
			return inval;
		}
	}
	/**computes the bitwise of the two BinaryNumbers by bitwise and
	 * both argument BinaryNumbers must be of same length for input to be considered valid
	 * @param bn1 first binary number
	 * @param bn2 second binary number
	 * @return bitwise and
	 */
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		if (bn1.getLength() == bn2.getLength()) {
			int[] bwor = new int[bn1.getLength()];
			int[] b1 = bn1.getInnerArray();
			int[] b2 = bn2.getInnerArray();
			for (int i=0; i<bn1.getLength(); i++) {
				if(b1[i]==1 && b2[i]==1) {
					bwor[i] = 1;
				}else {
					bwor[i] = 0;
				}
			}
			return bwor;
		}else {
			System.out.println("Invalid input, strings are not equal length. Return empty array");
			int[] inval = new int[0];
			return inval;
		}
	}
	/**shifts all digits in a binary number any number of places to left or right
	 * 
	 * @param direction indicates a left shift when value is -1, right when value 1, any other value is invalid
	 * @param amount how many digits the BinaryNumber will be shifted, only valid when nonnegative
	 */
	public void bitShift(int direction, int amount) {
		if(direction == -1) {
			//move it to the left
			length += amount;
			int[] tempArray = new int[length];
			for (int i=0; i<length; i++) {
				if (i<data.length) {
					tempArray[i] = data[i];
				}else {
					tempArray[i] = 0;
				}
			}
			data = tempArray;
		}else if (direction == 1) {
			//move it to the right
			if (amount > length) {
				data = new int[1];
				length = 0;
				data[0] = 0;
			}else {
				length -= amount;
				int[] tempArray = new int[length];
				for (int i=0; i<length; i++) {
					//same process as bit shift to left but with a smaller array
					tempArray[i] = data [i];
				}
				data = tempArray;
			}
		}else {
			throw new IllegalStateException();
		}
	}
	/** adds two binary numbers, one is the binary number that receives the message and the other is given as a parameter
	 * if the lengths of the two BinaryNumbers do not coincide, then smaller one should have 0's prepended
	 * @param aBinaryNumber binary number that is added to the binary number receiving the message
	 */
	public void add(BinaryNumber aBinaryNumber) {
		int[] bn2 = aBinaryNumber.getInnerArray();
		int bn2L = aBinaryNumber.getLength();
		
		if(length > bn2L) {
			//prepend zeroes onto aBinaryNumber then add
			bn2 = prepend((length - bn2L), bn2);
		}else if(length < aBinaryNumber.getLength()) {
			//prepend zeroes on to bn1 then add
			data = prepend((bn2L - length), data);
		}
		
		int carry = 0;
		for (int i = length-1; i>=0; i--) {
			int temp1 = data[i];
			int temp2 = bn2[i];
			int val = 0;
			
			if(carry==0) {
				if(temp1==1 && temp2==1) {
					carry = 1;
				}else if((temp1==1 && temp2==0)||(temp1==0 && temp2==1)) {
					val = 1;
				}
				data[i] = val;
			}else {
				if(temp1==1 && temp2==1) {
					carry = 1;
					val = 1;
				}else if((temp1==1 && temp2==0)||(temp1==0 && temp2==1)) {
					carry = 1;
				}else {
					carry = 0;
					val = 1;
				}
				data[i] = val;
			}
		}
		if (carry == 1) {
			int[] temp = data;
			length += 1;
			data = new int[length];
			for (int i=0; i<length; i++) {
				if (i==0) {
					data[i] = 1;
				}else {
					data[i] = temp[i-1];
				}
			}
		}
	}
	/**prepends an amount of 0's to a Binary Number
	 * 
	 * @param amount number of 0's to prepend
	 */
	public static int[] prepend(int amount, int[] bN) {
		int[] copyBn = bN;
		int[] prep = new int[bN.length + amount];
		for (int i=0; i<prep.length; i++) {
			if (i<amount) {
				prep[i] = 0;
			}else {
				prep[i] = copyBn[i-amount];
			}
		}
		return prep;
	}
	/**returns the BinaryNumber as the corresponding encoded string
	 * 
	 */
	public String toString() {
		String bNum = "";
		for(int i=0; i<length; i++) {
			bNum += data[i];
		}
		return bNum;
	}
	/**transforms binary number to decimal notation
	 * 
	 * @return binary number in decimal notation
	 */
	public int toDecimal() {
		//read through array and based on length multiply either 1 or 0 by 10^of whatever place in the number it is and add result and display that
		int number = 0;
		for (int i=0; i<length; i++) {
			number += (data[i] * Math.pow(2, (length - (i+1))));
		}
		return number;
	}
	
	public static void main(String[] args) {
		BinaryNumber k = new BinaryNumber("101");
		System.out.println(k.toString());
	}
	
}