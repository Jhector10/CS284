package anagrams;

/*
 * Name: Joshua Hector
 * I pledge my honor that I have abided by the Stevens Honor System
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Anagrams {
	
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable = new HashMap<Character,Integer>();
	Map<Long,ArrayList<String>> anagramTable;

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	
	/**
	 * Creates a letter table for the anagrams that we will be tracking
	 */
	public void buildLetterTable() {
		
		Character[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 
				'x', 'y', 'z'};
		
		for (int i = 0; i < letters.length; i++) {
			letterTable.put(letters[i], primes[i]);
		}
		
	}
	
	/**
	 * 
	 * @param s: an inputted string that calculates the hash code
	 * @return long: returns the hashcode of the given string
	 */
	public long myHashCode(String s) {
		if (s == "" || s == null) {
			throw new IllegalArgumentException("String cannot be empty");
		}
		
		long hash = 1;
		for(int i = 0; i < s.length(); i++) {
			hash *= letterTable.get(s.charAt(i));
		}
				
	    return hash;

	}

	/**
	 * 
	 * @param s: an inputted string that gets added to the anagramTable, throws
	 * 			a new exception if it is already in the anagramTable
	 */
	public void addWord(String s) {
		if (s == "") {
			throw new IllegalArgumentException("String cannot be empty");
		}
		
		long hashValue = myHashCode(s);
		
		if(anagramTable.containsKey(hashValue)) {
			ArrayList<String> words = anagramTable.get(hashValue);
			
			if (words.contains(s)) {
				throw new IllegalArgumentException("Cannot add duplicate word");
			}
			
			words.add(s);
			anagramTable.put(hashValue, words);
		} else {
			ArrayList<String> newWords = new ArrayList<String>();
			newWords.add(s);
			anagramTable.put(hashValue, newWords);
		}
		
	}
	
	/**
	 * 
	 * @param s: takes file as input and does addWord function with each strLine 
	 * @throws IOException if can't find the file
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	/**
	 * 
	 * @return the list with the max amount of entries
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
	    
		int listSize = 0;
		int maxSize = 0;
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxList = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		Set<Long> keys = anagramTable.keySet(); // obtains all the keys in the table
		
		for (Long key: keys) { 
			ArrayList<String> words = anagramTable.get(key);
			listSize = words.size();
			if (listSize > maxSize) {
				maxSize = listSize;
				SimpleEntry<Long, ArrayList<String>> chosenList = new AbstractMap.SimpleEntry<Long,ArrayList<String>>(key, words);
				maxList.clear();
				maxList.add(chosenList);
			}
		}
		
		return maxList;
		
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("/Users/joshuahector/Downloads/School Classes/Data Structures/Anagrams/src/anagrams/words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
