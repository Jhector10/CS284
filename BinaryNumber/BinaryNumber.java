
public class BinaryNumber {
	
	private int[] data;
	private int length;
	
	// Constructors
	
	public BinaryNumber(int length) {
		// Creating a binary number of int length consisting of 0s
		
		if (length <= 0 ) {
			throw new IllegalArgumentException("Length must be more than 0.");
		}
		
		data = new int[length];
		this.length = length;
	}
	
	public BinaryNumber(String str) {
		// given a binary number, the corresponding binary number is created
		
		this.length = str.length();
        this.data = new int[this.length];

        for (int i = 0; i < this.length; i++) {
            char num = str.charAt(i);
            int cha = Character.getNumericValue(num);
            if (cha == 1) {
                this.data[i] = cha;
            } 
            else if (cha == 0) {
                // Do nothing
            } else {
                throw new IllegalArgumentException("Invalid bit given '" + num + "'.");
            }
        }
	}

	// Getters
	
	public int getLength() {
		// returns the length of the binary number
		return this.length;
	}
	
	public int[] getInnerArray() {
		return this.data;
	}
	
	public int getDigit(int index) {
		// gets the digit based on the index parameter
		try {
			return this.data[index];
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new IllegalArgumentException("Invalid argument");
		}
	}
	
	// Methods
	
	//public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {}
	
	//public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) { }
	
	public void bitShift(int direction, int amount) {
		if (direction == 1) {
			if (amount > this.length || amount < 0) {
				throw new IndexOutOfBoundsException("The amount entered for the Bit Shift "
						+ "must be an integer between 0 and the length of the binary number.");
			}
			
			int[] emptyArray = new int[this.data.length - amount];
			for(int i = 0; i > emptyArray.length; i++) {
				emptyArray[i] = this.data[i];
			}
			
			this.data = emptyArray;
			this.length = emptyArray.length;
		}
		
		else if (direction == -1) {
			if (amount > this.length || amount < 0) {
				throw new IndexOutOfBoundsException("The amount entered for the Bit Shift "
						+ "must be an integer between 0 and the length of the binary number.");
			}
			
			int[] emptyArray = new int[this.data.length + amount];
			for(int i = 0; i > emptyArray.length; i++) {
				emptyArray[i] = this.data[i];
			}
			
			this.data = emptyArray;
			this.length = emptyArray.length;
			
		} 
		
		else if (amount < 0) {
			throw new IndexOutOfBoundsException("The amount must be more than greater "
					+ "than or equal to 0.");
		}
		
		else {
			throw new IndexOutOfBoundsException("The direction entered for the Bit Shift is invalid."
					+ " Must be 1 or -1.");
		}
		
	}
	
	public void add(BinaryNumber aBinaryNumber) {
		
	}
	
	public String toString() {
		// returns a String of the binary number in memory
		
		String number = "";
		
		for (int i = 0; i < this.length; i++) {
			number += this.data[i];
		}
		
		return "Binary number: " + number;
	}
	
	public int toDecimal() {
		int num = 0;
		int pow = this.length - 1;
		int i = 0;
		
		while (i < this.length) {
			num += this.data[i] * Math.pow(2.0, pow);
			pow--;
			i++;
		}
		
		return num;
	}
	
	private void prepend(int amount) {
		// prepends a given number of 0's to the beginning of a binary number.
		
		int[] newArray = new int[this.data.length + amount];
		
		for (int i = amount; i < newArray.length; i++) {
			newArray[i] += this.getDigit(i - amount);
		}
		
		this.data = newArray;
		this.length = newArray.length;
	}

	public static void main(String[] args) {
		
		BinaryNumber bin = new BinaryNumber("11010");
		BinaryNumber num = new BinaryNumber(5);
				
		System.out.println(bin.toString());
		System.out.println(num.toString());
		
		System.out.println();
		
		System.out.println(bin.toDecimal());
		System.out.println(num.toDecimal());
		
	}

}