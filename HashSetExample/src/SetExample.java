import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SetExample {

	public static void main(String[] args) {
		String[] listA = {"Ann", "Sally", "Jill", "Sally"};
		String[] listB = {"Bob", "Bill", "Ann", "Jill"};
		
		Set<String> setA = new HashSet<String>();
		Set<String> setB = new HashSet<String>();
		Set<String> setAcopy = new HashSet<String>();
		
		for (String s: listA) {
			setA.add(s);
			setAcopy.add(s);
		}
		
		for(String s: listB) {
			setB.add(s);
		}
		
		System.out.println("The 2 sets are : " + "\n" + setA + "\n" + setB);
		
		setA.addAll(setB); // Set Union
		System.out.println("Union: " + setA);
		
		setAcopy.retainAll(setB); // Set Intersection
		System.out.println("Intersection: " + setAcopy);
		
		/*
		 * HashMap Example
		 */
		
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("J", "Jane");
		aMap.put("B", "Bill");
		aMap.put("Z", "Zebra");
		
		System.out.println(aMap.get("Z"));
		System.out.println(aMap.get("Bill"));
		
		for (String a: aMap.values()) {
			System.out.println(a);
		}
		
		for (Map.Entry<String, String> entry: aMap.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}

	}

}
